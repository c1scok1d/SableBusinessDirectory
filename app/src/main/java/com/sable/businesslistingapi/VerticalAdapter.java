package com.sable.businesslistingapi;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

public class VerticalAdapter extends RecyclerView.Adapter {


    private ArrayList<ListingsModel> dataset;
    private Context mContext;

    public VerticalAdapter(ArrayList<ListingsModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView title, tvStreet, tvCity, tvRegion, tvZip, tvHours, tvisOpen, tvContent, tvPhone, tvRating, tvRatingCount, tvLat, tvLng, tvBldNo, tvWebsite, tvEmail, tvTwitter, tvFacebook, tvFeatured;
        ImageView image;
        RatingBar simpleRatingBar;
        ImageButton btnCall, btnDirections, btnWebsite, btnEmail, btnTwitter, btnFacebook;

        public ImageTypeViewHolder(final View itemView) {
            super(itemView);
            this.simpleRatingBar = itemView.findViewById(R.id.simpleRatingBar);

            this.title = itemView.findViewById(R.id.title);
            this.tvRating = itemView.findViewById(R.id.ratingBar);
            this.tvRatingCount = itemView.findViewById(R.id.tvRatingCount);
            this.tvBldNo = itemView.findViewById(R.id.tvBldgNo);
            this.tvStreet = itemView.findViewById(R.id.tvStreet);
            this.tvCity = itemView.findViewById(R.id.tvCity);
            this.tvRegion = itemView.findViewById(R.id.tvState);
            this.tvZip = itemView.findViewById(R.id.tvZip);
            this.tvHours = itemView.findViewById(R.id.tvHours);
            this.tvisOpen = itemView.findViewById(R.id.tvIsOpen);
            this.tvContent = itemView.findViewById(R.id.tvDescription);
            this.tvPhone = itemView.findViewById(R.id.tvPhone);
            this.image = itemView.findViewById(R.id.ivImage);
            this.btnCall = itemView.findViewById(R.id.btnCall);
            this.btnDirections = itemView.findViewById(R.id.btnDirections);
            this.tvLat = itemView.findViewById(R.id.tvLat);
            this.tvLng = itemView.findViewById(R.id.tvLng);
            this.image = itemView.findViewById(R.id.ivImage);
            this.tvWebsite = itemView.findViewById(R.id.tvWebsite);
            this.tvEmail = itemView.findViewById(R.id.tvEmail);
            this.tvTwitter = itemView.findViewById(R.id.tvTwitter);
            this.tvFacebook = itemView.findViewById(R.id.tvFacebook);
            this.btnEmail = itemView.findViewById(R.id.btnEmail);
            this.btnTwitter = itemView.findViewById(R.id.btnTwitter);
            this.btnFacebook = itemView.findViewById(R.id.btnFacebook);
            this.tvFeatured = itemView.findViewById(R.id.tvFeatured);
            // this.tvContent = itemView.findViewById(R.id.etBusDesc);
            //      this.tvPhone = itemView.findViewById(R.id.btnCall);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = tvPhone.getText().toString();

                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phone));
                    itemView.getContext().startActivity(callIntent);
                }
            });
            btnDirections.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String lat = tvLat.getText().toString();
                    String lng = tvLng.getText().toString();

                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lat +"," + lng);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    itemView.getContext().startActivity(mapIntent);
                }
            });

            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String siteURL = tvWebsite.getText().toString();
                    Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(siteURL));
                    itemView.getContext().startActivity(website);
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
        final ListingsModel object = dataset.get(position);

        String latitude = Double.toString(object.latitude);

        String longitude = Double.toString(object.longitude);


        Picasso.Builder builder = new Picasso.Builder(mContext);

        ((ImageTypeViewHolder) holder).title.setText(object.post_title);
//        ((ImageTypeViewHolder) holder).tvBldNo.setText(object.bldgno);
//        ((ImageTypeViewHolder) holder).tvStreet.setText(object.street);
        ((ImageTypeViewHolder) holder).tvCity.setText(object.city);
        ((ImageTypeViewHolder) holder).tvRegion.setText(object.state);
        ((ImageTypeViewHolder) holder).tvZip.setText(object.zip);
//        ((ImageTypeViewHolder) holder).tvHours.setText(object.hours);
        ((ImageTypeViewHolder) holder).tvisOpen.setText(object.isOpen);
        ((ImageTypeViewHolder) holder).tvContent.setText(object.content);
        ((ImageTypeViewHolder) holder).simpleRatingBar.setRating(object.rating);

//        ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);
        ((ImageTypeViewHolder) holder).tvWebsite.setText(object.website);
        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
//        ((ImageTypeViewHolder) holder).tvLat.setText(latitude);
//        ((ImageTypeViewHolder) holder).tvLng.setText(longitude);
//        ((ImageTypeViewHolder) holder).tvContent.setText(object.content);
        ((ImageTypeViewHolder) holder).tvRatingCount.setText(String.valueOf(object.rating));
        builder.build().load(dataset.get(position).featured_image).into(((ImageTypeViewHolder) holder).image);
//        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
        // ((ImageTypeViewHolder) holder).image.setText(object.image);


        if ( dataset.get(0).facebook == null || dataset.get(0).facebook.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnFacebook.setColorFilter(Color.argb(211, 211, 211, 211));
//        } else {
//            ((ImageTypeViewHolder) holder).tvFacebook.setText(object.facebook);
       }

        if (dataset.get(0).twitter == null || dataset.get(0).twitter.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnTwitter.setColorFilter(Color.argb(211, 211, 211, 211));
//        } else {
//            ((ImageTypeViewHolder) holder).tvTwitter.setText(object.twitter);
        }
        if (dataset.get(0).email == null || dataset.get(0).email.isEmpty()) {
            ((ImageTypeViewHolder) holder).btnEmail.setColorFilter(Color.argb(211, 211, 211, 211));
//        } else {
//            ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);
        }
        if (dataset.get(0).featured){
            String featured = "Featured";
            ((ImageTypeViewHolder) holder).tvFeatured.setText(featured);
            ((ImageTypeViewHolder) holder).tvFeatured.setTextColor(Color.rgb(237, 189, 3));

//        } else {
//            ((ImageTypeViewHolder) holder).tvEmail.setText(object.email);

        }
    }
    @Override
    public int getItemCount () {
        return dataset.size();
    }
}
