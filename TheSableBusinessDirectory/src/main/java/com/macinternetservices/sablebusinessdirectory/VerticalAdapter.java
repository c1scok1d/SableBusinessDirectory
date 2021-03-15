package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.AccessToken;
import com.google.android.gms.location.Geofence;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.macinternetservices.sablebusinessdirectory.MainActivity.GEOFENCE_EXPIRATION_IN_MILLISECONDS;

public class VerticalAdapter extends RecyclerView.Adapter {

    private ArrayList<ListingsModel> dataset;
    private ArrayList<ListingsModel> locationReview = new ArrayList<>();
    private  ArrayList<ListingsModel> locationFoo = new ArrayList<>();
    private ArrayList<ListingsModel> locationReviewShow = new ArrayList<>();
    String userName, userEmail, userImage, userId, baseURL = "https://www.thesablebusinessdirectory.com";
    AccessToken accessToken;


    private Context mContext;

    public VerticalAdapter(ArrayList<ListingsModel> mlist, String wpUserName, String wpUserEmail, String wpUserImage, String wpUserId, Context context) {
        this.dataset = mlist;
        this.mContext = context;
        this.userName = wpUserName;
        this.userEmail = wpUserEmail;
        this.userImage = wpUserImage;
        this.userId = wpUserId;
        accessToken = AccessToken.getCurrentAccessToken();

    }

    public class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStreet, tvCity, tvState, tvHours, tvisOpen, tvContent, tvPhone,
                tvRating, tvRatingCount, tvLatitude, tvLongitude, tvBldNo, tvWebsite, tvEmail, tvTwitter,
                tvFacebook, tvFeatured, tvDistance, tvZipcode, tvCall, tvId, tvLink, tvStatus, tvCategory,
                tvCountry, tvVideo, tvReviews;//, tvUserName, tvUserEmail;
//        boolean isLoggedIn = !accessToken.isExpired() || accessToken != null;

        ImageView image;
        RatingBar simpleRatingBar;
        ImageButton btnCall, btnDirections, btnEmail, btnTwitter, btnFacebook, btnReview;
       // private LoginButton loginButton;




        public ImageTypeViewHolder(final View itemView) {
            super(itemView);

            simpleRatingBar = itemView.findViewById(R.id.simpleRatingBar);
            //simpleRatingBar.setNumStars(5);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.ratingBar);
            tvRatingCount = itemView.findViewById(R.id.tvRatingCount);
            tvBldNo = itemView.findViewById(R.id.tvBldgNo);
            tvStreet = itemView.findViewById(R.id.tvStreet);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvState = itemView.findViewById(R.id.tvState);
            tvZipcode = itemView.findViewById(R.id.tvZipcode);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvHours = itemView.findViewById(R.id.tvHours);
            tvisOpen = itemView.findViewById(R.id.tvIsOpen);
            tvContent = itemView.findViewById(R.id.tvDescription);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            //image = itemView.findViewById(R.id.ivImage);
            btnCall = itemView.findViewById(R.id.btnCall);
            tvCall = itemView.findViewById(R.id.tvCall);
            btnDirections = itemView.findViewById(R.id.btnDirections);
            tvLatitude = itemView.findViewById(R.id.tvLat);
            tvLongitude = itemView.findViewById(R.id.tvLng);
            image = itemView.findViewById(R.id.ivImage);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvTwitter = itemView.findViewById(R.id.tvTwitter);
            tvFacebook = itemView.findViewById(R.id.tvFacebook);
           // btnEmail = itemView.findViewById(R.id.btnEmail);
            //btnTwitter = itemView.findViewById(R.id.btnTwitter);
            //btnFacebook = itemView.findViewById(R.id.btnFacebook);
            tvFeatured = itemView.findViewById(R.id.tvFeatured);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            btnReview = itemView.findViewById(R.id.btnReview);
            tvLongitude = itemView.findViewById(R.id.tvLng);
            tvLatitude = itemView.findViewById(R.id.tvLat);
            tvLink = itemView.findViewById(R.id.tvLink);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvVideo = itemView.findViewById(R.id.tvVideo);
            tvReviews = itemView.findViewById(R.id.tvReviews);


            image.setOnClickListener(v -> {

                Intent showReviews = new Intent(v.getContext(), ListReviewActivity.class);


                for (int i = 0; i < locationReview.size(); i++) {

                    if ((locationReview.get(i).id == Integer.parseInt(tvId.getText().toString()))) {

                        locationReviewShow.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                                locationReview.get(i).id,
                                locationReview.get(i).title,
                                locationReview.get(i).link,
                                locationReview.get(i).status,
                                locationReview.get(i).category,
                                locationReview.get(i).featured,
                                locationReview.get(i).featured_image,
                                locationReview.get(i).bldgno,
                                locationReview.get(i).street,
                                locationReview.get(i).city,
                                locationReview.get(i).state,
                                locationReview.get(i).country,
                                locationReview.get(i).zipcode,
                                locationReview.get(i).latitude,
                                locationReview.get(i).longitude,
                                locationReview.get(i).rating,
                                locationReview.get(i).ratingCount,
                                locationReview.get(i).phone,
                                locationReview.get(i).email,
                                locationReview.get(i).website,
                                locationReview.get(i).twitter,
                                locationReview.get(i).facebook,
                                locationReview.get(i).video,
                                locationReview.get(i).hours,
                                locationReview.get(i).isOpen,
                                locationReview.get(i).logo,
                                locationReview.get(i).content,
                                locationReview.get(i).featured_image,
                                locationReview.get(i).content,
                                new SimpleGeofence(locationReview.get(i).title, locationReview.get(i).latitude, locationReview.get(i).longitude,
                                100, GEOFENCE_EXPIRATION_IN_MILLISECONDS, locationReview.get(i).featured_image,
                                Geofence.GEOFENCE_TRANSITION_ENTER
                                        | Geofence.GEOFENCE_TRANSITION_DWELL
                                        | Geofence.GEOFENCE_TRANSITION_EXIT))));

                        Bundle locationReviewBundle = new Bundle();
                        locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReviewShow);
                        showReviews.putExtra("locationReview", locationReviewShow);
                        itemView.getContext().startActivity(showReviews);
                        break;
                    }
                }
            });


            btnReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /**
                     * check validity of facebook access token
                     */
                    //accessTokenTracker.startTracking();


                    //boolean isLoggedIn = accessToken != null;

                    if (!MainActivity.isLoggedIn) {
                        Intent loginIntent = new Intent(v.getContext(),LoginActivity.class);
                        itemView.getContext().startActivity(loginIntent);
                        //goto login activity get username and email via facebook create account, return here to check again and proceed

                        Toast.makeText(getApplicationContext(),"User must be logged in to leave a listing review.", Toast.LENGTH_SHORT).show();
                    } else {

                        /**
                         * for each array space if id != skip or else...
                         */

                        for (int i = 0; i < locationReview.size(); i++) {

                            if ((locationReview.get(i).id == Integer.parseInt(tvId.getText().toString()))) {

                                locationFoo.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                                        locationReview.get(i).id,
                                        locationReview.get(i).title,
                                        locationReview.get(i).link,
                                        locationReview.get(i).status,
                                        locationReview.get(i).category,
                                        locationReview.get(i).featured,
                                        locationReview.get(i).featured_image,
                                        locationReview.get(i).bldgno,
                                        locationReview.get(i).street,
                                        locationReview.get(i).city,
                                        locationReview.get(i).state,
                                        locationReview.get(i).country,
                                        locationReview.get(i).zipcode,
                                        locationReview.get(i).latitude,
                                        locationReview.get(i).longitude,
                                        locationReview.get(i).rating,
                                        locationReview.get(i).ratingCount,
                                        locationReview.get(i).phone,
                                        locationReview.get(i).email,
                                        locationReview.get(i).website,
                                        locationReview.get(i).twitter,
                                        locationReview.get(i).facebook,
                                        locationReview.get(i).video,
                                        locationReview.get(i).hours,
                                        locationReview.get(i).isOpen,
                                        locationReview.get(i).logo,
                                        locationReview.get(i).content,
                                        locationReview.get(i).featured_image,
                                        locationReview.get(i).content,
                                        new SimpleGeofence(locationReview.get(i).title, locationReview.get(i).latitude, locationReview.get(i).longitude,
                                                100, GEOFENCE_EXPIRATION_IN_MILLISECONDS, locationReview.get(i).featured_image,
                                                Geofence.GEOFENCE_TRANSITION_ENTER
                                                        | Geofence.GEOFENCE_TRANSITION_DWELL
                                                        | Geofence.GEOFENCE_TRANSITION_EXIT))));

                                Intent LocationReview = new Intent(v.getContext(), ReviewActivity.class);

                                Bundle locationReviewBundle = new Bundle();
                                locationReviewBundle.putParcelableArrayList("locationReview", locationFoo);
                                LocationReview.putParcelableArrayListExtra("locationReview", locationFoo);
                                itemView.getContext().startActivity(LocationReview);
                                break;
                            } else {
                                ////Log.e("VerticalAdapter", "no matcon on locationReview");
                            }
                        }
                    }
                }
            });

            if (!tvPhone.getText().toString().isEmpty() || tvPhone.getText().toString().equals("null")) {
                btnCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + tvPhone.getText().toString()));
                        itemView.getContext().startActivity(callIntent);
                    }
                });
            }
            if (!tvLatitude.getText().toString().isEmpty() || tvLongitude.getText().toString().isEmpty()) {
                btnDirections.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + tvLatitude.getText().toString() + "," + tvLongitude.getText().toString());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        itemView.getContext().startActivity(mapIntent);
                    }
                });
            }


            if (!tvWebsite.getText().toString().isEmpty() || tvWebsite.getText().toString().equals("null")) {
                tvWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(tvWebsite.getText().toString()));
                        itemView.getContext().startActivity(website);
                    }
                });
            }

            if (!tvEmail.getText().toString().isEmpty() || tvEmail.getText().toString().equals("null")) {
                btnEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent email = new Intent(Intent.ACTION_VIEW, Uri.parse(tvEmail.getText().toString()));
                        itemView.getContext().startActivity(email);
                    }
                });
            }

            if (!tvTwitter.getText().toString().isEmpty() || tvTwitter.getText().toString().equals("null")) {
                btnTwitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent twitter = new Intent(Intent.ACTION_VIEW, Uri.parse(tvTwitter.getText().toString()));
                        itemView.getContext().startActivity(twitter);
                    }
                });
            }


            if (!tvFacebook.getText().toString().isEmpty() || tvFacebook.getText().toString().equals("null")) {
                btnFacebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse(tvFacebook.getText().toString()));
                        itemView.getContext().startActivity(facebook);
                    }
                });
            }

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_listing_details, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        Picasso.Builder builder = new Picasso.Builder(mContext);

        final ListingsModel object = dataset.get(position);

        Location locationA = new Location("point A");
        locationA.setLatitude(object.latitude); //listing lat
        locationA.setLongitude(object.longitude); //listing lng


        Location locationB = new Location("point B");
        locationB.setLatitude(MainActivity.latitude); //device lat
        locationB.setLongitude(MainActivity.longitude); //device lng

        double distance = (locationA.distanceTo(locationB) * 0.000621371192); //convert meters to miles

        ((ImageTypeViewHolder) holder).tvTitle.setText(object.title);
        ((ImageTypeViewHolder) holder).tvHours.setText(object.hours);
        ((ImageTypeViewHolder) holder).tvCity.setText(object.city);
        ((ImageTypeViewHolder) holder).tvState.setText(object.state);
        ((ImageTypeViewHolder) holder).tvZipcode.setText(object.zipcode);
        ((ImageTypeViewHolder) holder).tvisOpen.setText(object.isOpen);
        ((ImageTypeViewHolder) holder).tvContent.setText(object.content);
        ((ImageTypeViewHolder) holder).simpleRatingBar.setRating(object.rating);
        ((ImageTypeViewHolder) holder).tvDistance.setText(String.format(Locale.US, "%.2f", distance));
        ((ImageTypeViewHolder) holder).tvWebsite.setText(object.website);
        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
        //((ImageTypeViewHolder) holder).simpleRatingBar.setNumStars(object.rating);
        ((ImageTypeViewHolder) holder).tvRatingCount.setText(String.valueOf(object.ratingCount));
        ((ImageTypeViewHolder) holder).tvId.setText(String.valueOf(object.id));
        ((ImageTypeViewHolder) holder).tvLatitude.setText(String.valueOf(object.latitude));
        ((ImageTypeViewHolder) holder).tvLongitude.setText(String.valueOf(object.longitude));
        ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);
        ((ImageTypeViewHolder) holder).tvTwitter.setText(object.twitter);
        ((ImageTypeViewHolder) holder).tvFacebook.setText(object.facebook);
        ((ImageTypeViewHolder) holder).tvId.setText(String.valueOf(object.id));
        ((ImageTypeViewHolder) holder).tvLink.setText(object.link);
        ((ImageTypeViewHolder) holder).tvStatus.setText(object.status);
        ((ImageTypeViewHolder) holder).tvCategory.setText(object.category);
        ((ImageTypeViewHolder) holder).tvFeatured.setText(String.valueOf(object.featured));
        ((ImageTypeViewHolder) holder).tvBldNo.setText(object.bldgno);
        ((ImageTypeViewHolder) holder).tvStreet.setText(object.street);
        ((ImageTypeViewHolder) holder).tvCountry.setText(object.country);
        ((ImageTypeViewHolder) holder).tvTwitter.setText(object.twitter);
        builder.build().load(dataset.get(position).featured_image).into(((ImageTypeViewHolder) holder).image);

        locationReview.add(new ListingsModel(ListingsModel.IMAGE_TYPE,
                object.id,
                object.title,
                object.link,
                object.status,
                object.category,
                object.featured,
                object.image,
                object.bldgno,
                object.street,
                object.city,
                object.state,
                object.country,
                object.zipcode,
                object.latitude,
                object.longitude,
                object.rating,
                object.ratingCount,
                object.phone,
                object.email,
                object.website,
                object.twitter,
                object.facebook,
                object.video,
                object.hours,
                object.isOpen,
                object.logo,
                object.content,
                object.featured_image,
                object.content,
                new SimpleGeofence(object.title, object.latitude, object.longitude,
                        100, GEOFENCE_EXPIRATION_IN_MILLISECONDS, object.logo,
                        Geofence.GEOFENCE_TRANSITION_ENTER
                                | Geofence.GEOFENCE_TRANSITION_DWELL
                                | Geofence.GEOFENCE_TRANSITION_EXIT)));

        /*if (object.facebook.equals("null") || object.facebook.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnFacebook.setColorFilter(Color.argb(211, 211, 211, 211)); //grey
            // } else {
            //     ((ImageTypeViewHolder) holder).tvFacebook.setText(object.facebook);
        }

        if (object.twitter.equals("null") || object.twitter.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnTwitter.setColorFilter(Color.argb(211, 211, 211, 211)); //grey
            //} else {
            //    ((ImageTypeViewHolder) holder).tvTwitter.setText(object.twitter);
        }
        if (object.email.equals("null") || object.email.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnEmail.setColorFilter(Color.argb(211, 211, 211, 211)); //grey
            //} else {
            //    ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);
        } */
        if (object.featured.equals(true)) {
            String isFeatured = "Featured";
            ((ImageTypeViewHolder) holder).tvFeatured.setText(isFeatured);
            ((ImageTypeViewHolder) holder).tvFeatured.setTextColor(Color.rgb(249, 166, 2)); //gold
        } else {
            ((ImageTypeViewHolder) holder).tvFeatured.setVisibility(View.GONE);
        }
        if (object.phone == null || object.phone.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnCall.setColorFilter(Color.argb(211, 211, 211, 211)); //grey
           // ((ImageTypeViewHolder) holder).tvCall.setTextColor(Color.rgb(211, 211, 211));
            // } else {
            //     ((ImageTypeViewHolder) holder).tvFacebook.setText(object.facebook);
        }

        if (object.latitude.toString().equals("null") || object.latitude.toString().isEmpty() || (object.longitude.toString().equals("null") || object.longitude.toString().isEmpty())) {
            ((ImageTypeViewHolder) holder).btnDirections.setColorFilter(Color.argb(211, 211, 211, 211)); //grey
            //} else {
            //    ((ImageTypeViewHolder) holder).tvTwitter.setText(object.twitter);
        }
        if (object.hours == "null" || object.isOpen == null || object.hours == null || object.isOpen == null) {
            ((ImageTypeViewHolder) holder).tvisOpen.setVisibility(View.GONE);
            ((ImageTypeViewHolder) holder).tvHours.setVisibility(View.GONE);
        } else if (object.hours == "Closed" || object.isOpen == "Closed now") {
            String closed = "Closed";
            ((ImageTypeViewHolder) holder).tvHours.setText(closed);
            ((ImageTypeViewHolder) holder).tvHours.setTextColor(Color.rgb(255, 0, 0)); //red
            ((ImageTypeViewHolder) holder).tvisOpen.setText(closed);
            ((ImageTypeViewHolder) holder).tvisOpen.setTextColor(Color.rgb(255, 0, 0)); //red
        } else {
            String open = "Open";
            ((ImageTypeViewHolder) holder).tvisOpen.setText(open);
            ((ImageTypeViewHolder) holder).tvisOpen.setTextColor(Color.rgb(51, 165, 50)); //green
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}