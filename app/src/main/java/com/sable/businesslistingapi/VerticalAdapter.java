package com.sable.businesslistingapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class VerticalAdapter extends RecyclerView.Adapter {

    private ArrayList<ListingsModel> dataset;
    private ArrayList<ListingsModel> locationReview = new ArrayList<>();
    //ArrayList<ListingsModel> locationReview = new ArrayList<>();
    String link, bldgno, street, city, state, zipcode, hours, isOpen, content, phone,
            website, email, twitter, facebook, name, category, country, status,
            featuredImage, video, logo;
    Integer id, rating, ratingCount;
    Boolean featured;
    Double latitude, longitude;


    private Context mContext;

    public VerticalAdapter(ArrayList<ListingsModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }


    public class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStreet, tvCity, tvState, tvHours, tvisOpen, tvContent, tvPhone,
                tvRating, tvRatingCount, tvLatitude, tvLongitude, tvBldNo, tvWebsite, tvEmail, tvTwitter,
                tvFacebook, tvFeatured, tvDistance, tvZipcode;


        ImageView image;
        RatingBar simpleRatingBar;
        ImageButton btnCall, btnDirections, btnEmail, btnTwitter, btnFacebook, btnReview;
       // ArrayList<ListingsModel> locationReview = new ArrayList<>();



        public ImageTypeViewHolder(final View itemView ) {
            super(itemView);
            this.simpleRatingBar = itemView.findViewById(R.id.simpleRatingBar);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.ratingBar);
            tvRatingCount = itemView.findViewById(R.id.tvRatingCount);
            tvBldNo = itemView.findViewById(R.id.tvBldgNo);
            tvStreet = itemView.findViewById(R.id.tvStreet);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvState = itemView.findViewById(R.id.tvState);
            tvZipcode = itemView.findViewById(R.id.tvZipcode);
            tvHours = itemView.findViewById(R.id.tvHours);
            tvisOpen = itemView.findViewById(R.id.tvIsOpen);
            tvContent = itemView.findViewById(R.id.tvDescription);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            image = itemView.findViewById(R.id.ivImage);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnDirections = itemView.findViewById(R.id.btnDirections);
            tvLatitude = itemView.findViewById(R.id.tvLat);
            tvLongitude = itemView.findViewById(R.id.tvLng);
            image = itemView.findViewById(R.id.ivImage);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvTwitter = itemView.findViewById(R.id.tvTwitter);
            tvFacebook = itemView.findViewById(R.id.tvFacebook);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnTwitter = itemView.findViewById(R.id.btnTwitter);
            btnFacebook = itemView.findViewById(R.id.btnFacebook);
            tvFeatured = itemView.findViewById(R.id.tvFeatured);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            btnReview = itemView.findViewById(R.id.btnReview);

            btnReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent LocationReview = new Intent(v.getContext(), ReviewActivity.class);
                    Bundle locationReviewBundle = new Bundle();
                    locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReview);
                    LocationReview.putExtra("locationReview", locationReview);
                    itemView.getContext().startActivity(LocationReview);
                }
            });

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String phone = tvPhone.toString();

                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phone));
                    itemView.getContext().startActivity(callIntent);
                }
            });
            btnDirections.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude +"," + longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    itemView.getContext().startActivity(mapIntent);
                }
            });

            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String siteURL = tvWebsite.getText().toString();
                    Intent email = new Intent(Intent.ACTION_VIEW, Uri.parse(siteURL));
                    itemView.getContext().startActivity(email);
                    }
            });

            btnTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String siteURL = tvTwitter.getText().toString();
                    Intent twitter = new Intent(Intent.ACTION_VIEW, Uri.parse(siteURL));
                    itemView.getContext().startActivity(twitter);
                }
            });

            btnFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String siteURL = tvFacebook.getText().toString();
                    Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse(siteURL));
                    itemView.getContext().startActivity(facebook);
                }
            });
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




        name = (object.title);
        id = (object.id);
        link = (object.link);
        status = (object.status);
        category = (object.category);
        featured = (object.featured);
        featuredImage = (object.featured_image);
        bldgno = (object.bldgno);
        street = (object.street);
        city = (object.city); state = (object.state); country = (object.country);
        zipcode = (object.zipcode); latitude = (object.latitude); longitude = (object.longitude);
        rating = (object.rating); ratingCount = (object.ratingCount); phone = (object.phone);
        email = (object.email); website = (object.website); twitter = (object.twitter);
        facebook = (object.facebook); video = (object.video); hours = (object.hours);
        isOpen = (object.isOpen); logo = (object.featured_image); content = (object.content);

        locationReview.add(new ListingsModel(ListingsModel.IMAGE_TYPE, id, name, link, status,
                category, featured, featuredImage, bldgno, street, city, state, country,
                zipcode, latitude, longitude, rating, ratingCount, phone, email, website, twitter,
                facebook, video, hours, isOpen, logo, content, featuredImage));

        Location locationA = new Location("point A");
        locationA.setLatitude(object.latitude); //listing lat
        locationA.setLongitude(object.longitude); //listing lng

        Location locationB = new Location("point B");
        locationB.setLatitude(latitude); //device lat
        locationB.setLongitude(longitude); //device lng

        double distance = (locationA.distanceTo(locationB) * 0.000621371192); //convert meters to miles

        ((ImageTypeViewHolder) holder).tvTitle.setText(object.title);
        ((ImageTypeViewHolder) holder).tvHours.setText(object.hours);
        ((ImageTypeViewHolder) holder).tvCity.setText(object.city);
        ((ImageTypeViewHolder) holder).tvState.setText(object.state);
        ((ImageTypeViewHolder) holder).tvZipcode.setText(object.zipcode);
        ((ImageTypeViewHolder) holder).tvisOpen.setText(object.isOpen);
        ((ImageTypeViewHolder) holder).tvContent.setText(object.content);
        ((ImageTypeViewHolder) holder).simpleRatingBar.setRating(object.rating);
        ((ImageTypeViewHolder) holder).tvDistance.setText(String.format( Locale.US, "%10.2f", distance));
        ((ImageTypeViewHolder) holder).tvWebsite.setText(object.website);
        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
        ((ImageTypeViewHolder) holder).simpleRatingBar.setNumStars(object.rating);
        ((ImageTypeViewHolder) holder).tvRatingCount.setText(String.valueOf(object.ratingCount));
        builder.build().load(dataset.get(position).featured_image).into(((ImageTypeViewHolder) holder).image);

        if ( object.facebook.equals("null") || object.facebook.isEmpty()) {
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
        }
        if (object.featured.equals("true")){
            String isFeatured = "Featured";
            ((ImageTypeViewHolder) holder).tvFeatured.setText(isFeatured);
            ((ImageTypeViewHolder) holder).tvFeatured.setTextColor(Color.rgb(255, 128, 0)); //orange
        //} else {
        //    ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);

        }
        if(object.isOpen.equals("Closed now")){
            ((ImageTypeViewHolder) holder).tvisOpen.setTextColor(Color.rgb(255, 0, 0 )); //red
        }
    }
    @Override
    public int getItemCount () {
        return dataset.size();
    }
}
