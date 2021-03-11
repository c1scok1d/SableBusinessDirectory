/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.macinternetservices.sablebusinessdirectory.clustering.Cluster;
import com.macinternetservices.sablebusinessdirectory.clustering.ClusterManager;
import com.macinternetservices.sablebusinessdirectory.clustering.view.DefaultClusterRenderer;
import com.macinternetservices.sablebusinessdirectory.model.Person;
import com.macinternetservices.sablebusinessdirectory.clustering.ClusterItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Demonstrates heavy customisation of the look of rendered clusters.
 */
public class MarkerClusteringActivity extends MainActivity implements ClusterManager.OnClusterClickListener<Person>,
        ClusterManager.OnClusterInfoWindowClickListener<Person>,
        ClusterManager.OnClusterItemClickListener<Person>,
        ClusterManager.OnClusterItemInfoWindowClickListener<Person> {

    ClusterManager<Person> mClusterManager;
   // private Random random = new Random(1984);
    private Person clickedVenueMarker;
    ArrayList<ListingsModel> locationReviewShow = new ArrayList<>();
    //boolean isLoggedIn = accessToken != null && !accessToken.isExpired();



    /**
     * Draws profile photos inside markers (using IconGenerator).
     * When there are multiple locations in the cluster, draw multiple photos (using MultiDrawable).
     */
    private class PersonRenderer extends DefaultClusterRenderer<Person> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public PersonRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(Person person, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            Picasso.get().load(person.profilePhoto).into(mImageView);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(person.name);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Person> cluster, MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> profilePhotos = new ArrayList<>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;
            Bitmap dummyBitmap = null;
            Drawable drawable;

            for (Person p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;
                //dummyBitmap = Picasso.get().load(p.profilePhoto);

                try {
                    dummyBitmap = Glide.with(getApplicationContext())
                            .asBitmap()
                            .load(p.profilePhoto)
                            .into(70, 70).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                drawable = new BitmapDrawable(getResources(), dummyBitmap);
                drawable.setBounds(0, 0, width, height);
                profilePhotos.add(drawable);
            }



            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
            multiDrawable.setBounds(0, 0, width, height);

            mClusterImageView.setImageDrawable(multiDrawable);
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<Person> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String firstName = cluster.getItems().iterator().next().name;
        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public void onClusterInfoWindowClick(Cluster<Person> cluster) {
        // Does nothing, but you could go to a list of the users.
        Toast.makeText(this, cluster + " (including " + cluster + ")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onClusterItemClick(Person item) {

        getMap().setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // shows brief listing summary onclick
                clickedVenueMarker = item;

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.custom_info_window, null);

                TextView venueNameTextView = view.findViewById(R.id.venue_name);
                venueNameTextView.setText(clickedVenueMarker.getTitle());

                TextView venueCity = view.findViewById(R.id.venue_city);
                venueCity.setText(String.valueOf(clickedVenueMarker.getCity()));

                TextView venueState = view.findViewById(R.id.venue_state);
                venueState.setText(String.valueOf(clickedVenueMarker.getState()));

                TextView venueSnippetTextView = view.findViewById(R.id.venue_snippet);
                venueSnippetTextView.setText(clickedVenueMarker.getSnippet());

                TextView ratingCount = view.findViewById(R.id.tvRatingCount);
                ratingCount.setText(String.valueOf(clickedVenueMarker.getRatingCount()));

                TextView firstReview = view.findViewById(R.id.tvReviewFirst);
                firstReview.setVisibility(View.GONE);

                RatingBar ratingBar = view.findViewById(R.id.ratingBar3);
                ratingBar.setRating(clickedVenueMarker.getRating());

                ImageView featuredImage = view.findViewById(R.id.featuredImage);
                String foo = clickedVenueMarker.getFeaturedImage();
                Picasso.get().load(clickedVenueMarker.getFeaturedImage()).into(featuredImage);

                if(clickedVenueMarker.getRating() == 0){
                    firstReview.setText("BE THE FIRST TO REVIEW "+clickedVenueMarker.getTitle());
                    firstReview.setVisibility(View.VISIBLE);
                }
                return view;
            }
        });
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Person item) {

       for(int i = 0; i< verticalList.size(); i++) {
            if (item.name.equals(verticalList.get(i).title)){
                locationReviewShow.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                        verticalList.get(i).id,
                        verticalList.get(i).title,
                        verticalList.get(i).link,
                        verticalList.get(i).status,
                        verticalList.get(i).category,
                        verticalList.get(i).featured,
                        verticalList.get(i).featured_image,
                        verticalList.get(i).bldgno,
                        verticalList.get(i).street,
                        verticalList.get(i).city,
                        verticalList.get(i).state,
                        verticalList.get(i).country,
                        verticalList.get(i).zipcode,
                        verticalList.get(i).latitude,
                        verticalList.get(i).longitude,
                        verticalList.get(i).rating,
                        verticalList.get(i).ratingCount,
                        verticalList.get(i).phone,
                        verticalList.get(i).email,
                        verticalList.get(i).website,
                        verticalList.get(i).twitter,
                        verticalList.get(i).facebook,
                        verticalList.get(i).video,
                        verticalList.get(i).hours,
                        verticalList.get(i).isOpen,
                        verticalList.get(i).logo,
                        verticalList.get(i).content,
                        verticalList.get(i).featured_image,
                        verticalList.get(i).content,
                        new SimpleGeofence(verticalList.get(i).title, verticalList.get(i).latitude, verticalList.get(i).longitude,
                                100, GEOFENCE_EXPIRATION_IN_MILLISECONDS, verticalList.get(i).featured_image,
                                Geofence.GEOFENCE_TRANSITION_ENTER
                                        | Geofence.GEOFENCE_TRANSITION_DWELL
                                        | Geofence.GEOFENCE_TRANSITION_EXIT))));

                Intent showReviews = new Intent(getApplicationContext(), ListReviewActivity.class);

                Bundle locationReviewBundle = new Bundle();
                locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReviewShow);
                showReviews.putExtra("locationReview", locationReviewShow);
                startActivity(showReviews);
                break;

            }
        }
    }

    @Override
    public void setMarkers() {
       // getMap().clear();
        //mClusterManager.clearItems();
        //mClusterManager.addItems(items);
        //mClusterManager.cluster();
        //Log.e("setMarkers", " Executing setMarkers " );
        mClusterManager = new ClusterManager<>(this, getMap());
        mClusterManager.setRenderer(new PersonRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);


        if(mapLocations.size() == 0){
            // if no locations near user zoom to current location and display no listing message and spokesman
            showOtherStuff();
            getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 200));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } else {
            getMap().clear();
            mClusterManager.clearItems();
            mClusterManager.addItems(mapLocations);
            mClusterManager.cluster();
        LatLngBounds bounds = MainActivity.latLngBoundsBuilder.build();
        getMap().setOnMapLoadedCallback(() -> getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200)));
        showStuff();
        }
        //Log.e("setMarkers", " Ending setMarkers " );
    }
    private void showStuff() {
        //Log.e("showStuff", " Executing showStuff ");
        Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        Animation imgAnimationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        // Button login_button3 = findViewById(R.id.login_button3);
        if (isLoggedIn) {

        }
        progressBar.setVisibility(View.GONE); //hide progressBar
        login_button3.setVisibility(View.VISIBLE);
        loadingLayout.setAnimation(imgAnimationOut);
        loadingLayout.setVisibility(View.GONE);
        searchView.setAnimation(imgAnimationIn);
        searchView.setVisibility(View.VISIBLE);
        btnShowListings.setAnimation(imgAnimationIn);
        btnShowListings.setVisibility(View.VISIBLE);
        btnAdd.setAnimation(imgAnimationIn);
        btnAdd.setVisibility(View.VISIBLE);
        tvMore.setAnimation(imgAnimationIn);
        tvMore.setVisibility(View.VISIBLE);
        sliderLayout.setAnimation(imgAnimationIn);
        sliderLayout.setVisibility(View.VISIBLE);
        //Log.e("showStuff", " Ending showStuff ");

    }
    private void showOtherStuff() {
        //Log.e("showOtherStuff", " Executing showOtherStuff " );
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {

        }
        Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        Animation imgAnimationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        //Animation imgZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        //Animation imgZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        progressBar.setVisibility(View.GONE); //hide progressBar
        login_button3.setVisibility(View.VISIBLE);
        loadingLayout.setAnimation(imgAnimationOut);
        loadingLayout.setVisibility(View.GONE);
        //searchView.setAnimation(imgAnimationIn);
        //searchView.setVisibility(View.VISIBLE);
        noListingsImageView.setAnimation(imgAnimationIn);
        noListingsImageView.setVisibility(View.VISIBLE);
        noListingsTextView.setAnimation(imgAnimationIn);
        noListingsTextView.setVisibility(View.VISIBLE);
        //noListingsTextView.setTextSize(16);

        if(isLoggedIn && firstName != null) {
            noListingsTextView.setText("This is terrible " + firstName +"!!!!\n\nLooks like there aren't any black owned businesses near you in our directory.\n" +
                    "Tap ADD to add any black owned business you visit to our directory.");
        } else {
            noListingsTextView.setText("This is terrible!!!!\n\nLooks like there aren't any black owned businesses near you in our directory.\n" +
                    "Tap ADD to add any black owned business you visit to our directory.");
        }

        btnAdd.setAnimation(imgAnimationIn);
        btnAdd.setVisibility(View.VISIBLE);
        //Log.e("showOtherStuff", " Ending showOtherStuff " );
    }
}
