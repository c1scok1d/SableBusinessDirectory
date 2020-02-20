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

package com.sable.businesslistingapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.sable.businesslistingapi.clustering.Cluster;
import com.sable.businesslistingapi.clustering.ClusterManager;
import com.sable.businesslistingapi.clustering.view.DefaultClusterRenderer;
import com.sable.businesslistingapi.model.Person;
import com.sable.businesslistingapi.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates heavy customisation of the look of rendered clusters.
 */
public class MarkerClusteringActivity extends MainActivity implements ClusterManager.OnClusterClickListener<Person>,
        ClusterManager.OnClusterInfoWindowClickListener<Person>,
        ClusterManager.OnClusterItemClickListener<Person>,
        ClusterManager.OnClusterItemInfoWindowClickListener<Person> {

    ClusterManager<Person> mClusterManager;
    private Person clickedVenueMarker;
    ArrayList<ListingsModel> locationReviewShow = new ArrayList<>();


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
            Glide.with(getApplicationContext()).asBitmap()
                    .load(person.profilePhoto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .placeholder(R.drawable.screen_splash).dontAnimate().into(mImageView);

           // mImageView.setImageBitmap(person.profilePhoto);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(person.name);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Person> cluster, MarkerOptions markerOptions) {
            // Draw multiple locations.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> profilePhotos;
            profilePhotos = new ArrayList<>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;
            Bitmap dummyBitmap = null;
           // profilePhotos = null;
            for (Person p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;
                try {
                    dummyBitmap =  Glide.with(getApplicationContext()).asBitmap()
                            .load(p.profilePhoto)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .fitCenter()
                            .placeholder(R.drawable.logo)
                            .submit(70, 70).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Drawable drawable = new BitmapDrawable(getResources(), dummyBitmap);
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

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

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
       TextView venueCity = view.findViewById(R.id.venue_city);
       TextView venueState = view.findViewById(R.id.venue_state);
              TextView venueSnippetTextView = view.findViewById(R.id.venue_snippet);
       TextView ratingCount = view.findViewById(R.id.tvRatingCount);
       TextView firstReview = view.findViewById(R.id.tvReviewFirst);
       firstReview.setVisibility(View.GONE);
       RatingBar ratingBar = view.findViewById(R.id.ratingBar3);
       venueNameTextView.setText(clickedVenueMarker.getTitle());
       venueCity.setText(String.valueOf(clickedVenueMarker.getCity()));
       venueState.setText(String.valueOf(clickedVenueMarker.getState()));
       venueSnippetTextView.setText(clickedVenueMarker.getSnippet());
       ratingBar.setRating(clickedVenueMarker.getRating());
       if(clickedVenueMarker.getRating() == 0){
           firstReview.setText("BE THE FIRST TO REVIEW "+clickedVenueMarker.getTitle());
           firstReview.setVisibility(View.VISIBLE);
       }
       ratingCount.setText(String.valueOf(clickedVenueMarker.getRatingCount()));
                return view;
            }
        });

       radioGroup.setVisibility(View.GONE);
       category_radioButton_scroller.setVisibility(View.GONE);


       return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Person item) {
        // Does nothing, but you could go into the user's profile page, for example.
        //Toast.makeText(this, item + " (including " + item + ")", Toast.LENGTH_SHORT).show();
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
                        verticalList.get(i).featured_image)));

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
    protected void setMarkers(boolean isRestore) {
        if (!isRestore) {
            getMap().setOnMapLoadedCallback(() -> getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),200)));
        }

        mClusterManager = new ClusterManager<>(this, getMap());
        mClusterManager.setRenderer(new PersonRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);
        mClusterManager.clearItems();

        if(mapLocations.size() == 0){
            // if no locations near user zoom to current location and display no listing message and spokesman
            showOtherStuff();
            getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } else {
        mClusterManager.addItems(mapLocations);
        mClusterManager.cluster();
        LatLngBounds bounds = MainActivity.latLngBoundsBuilder.build();
        getMap().setOnMapLoadedCallback(() -> getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200)));
        showStuff();
        }

    }
    private void showStuff() {
        Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        progressBar.setVisibility(View.GONE); //hide progressBar
        tvQuerying.setAnimation(imgAnimationOut);
        tvQuerying.setVisibility(View.GONE);
        btnShowListings.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.VISIBLE);
        tvMore.setVisibility(View.VISIBLE);
        category_radioButton_scroller.setVisibility(View.VISIBLE);
        tvCategories.setVisibility(View.VISIBLE);
        dragView.setVisibility(View.VISIBLE);

    }

    private void showOtherStuff() {
        Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        progressBar.setVisibility(View.GONE); //hide progressBar
        tvQuerying.setAnimation(imgAnimationOut);
        tvQuerying.setVisibility(View.GONE);
        noListingsAnimationLayout.setVisibility(View.VISIBLE);
        noListingsAnimationFLayout.setVisibility(View.VISIBLE);
        login_button2.setVisibility(View.VISIBLE);
        LinearLayout noListingsLayout = findViewById(R.id.noListingsLayout);
        noListingsLayout.setVisibility(View.VISIBLE);
        TextView noListingsTextView = findViewById(R.id.noListingsTextView);
        noListingsTextView.setVisibility(View.VISIBLE);
        ImageView noListingsImageView = findViewById(R.id.noListingsImageView);
        noListingsImageView.setVisibility(View.VISIBLE);
    }
}
