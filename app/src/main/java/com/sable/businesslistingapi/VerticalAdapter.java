package com.sable.businesslistingapi;

import android.content.Context;
import android.content.Intent;
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

        TextView title, tvStreet, tvCity, tvRegion, tvZip, tvHours, tvisOpen, tvContent, tvPhone, tvRating, tvRatingCount, tvLat, tvLng;
        ImageView image;
        RatingBar simpleRatingBar;
        ImageButton btnCall, btnDirections;

        public ImageTypeViewHolder(final View itemView) {
            super(itemView);
            this.simpleRatingBar = itemView.findViewById(R.id.simpleRatingBar);

            this.title = itemView.findViewById(R.id.title);
            this.tvRating = itemView.findViewById(R.id.ratingBar);
            this.tvRatingCount = itemView.findViewById(R.id.tvRatingCount);
            this.tvStreet = itemView.findViewById(R.id.tvBldgNo);
            this.tvCity = itemView.findViewById(R.id.tvBldgNo);
            this.tvRegion = itemView.findViewById(R.id.tvBldgNo);
            this.tvZip = itemView.findViewById(R.id.tvZip);
            this.tvHours = itemView.findViewById(R.id.tvHours);
            this.tvisOpen = itemView.findViewById(R.id.tvIsOpen);
            this.tvContent = itemView.findViewById(R.id.tvContent);
            this.tvPhone = itemView.findViewById(R.id.tvPhone);
            this.image = itemView.findViewById(R.id.ivImage0);
            this.btnCall = itemView.findViewById(R.id.btnCall);
            this.btnDirections = itemView.findViewById(R.id.btnDirections);
            this.tvLat = itemView.findViewById(R.id.tvLat);
            this.tvLng = itemView.findViewById(R.id.tvLng);
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
        ((ImageTypeViewHolder) holder).tvStreet.setText(object.street);
        ((ImageTypeViewHolder) holder).tvCity.setText(object.city);
        ((ImageTypeViewHolder) holder).tvRegion.setText(object.state);
        ((ImageTypeViewHolder) holder).tvZip.setText(object.zip);
        ((ImageTypeViewHolder) holder).tvHours.setText(object.hours);
        ((ImageTypeViewHolder) holder).tvisOpen.setText(object.isOpen);
        ((ImageTypeViewHolder) holder).tvContent.setText(object.content);
        ((ImageTypeViewHolder) holder).simpleRatingBar.setRating(object.rating);
        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
        ((ImageTypeViewHolder) holder).tvLat.setText(latitude);
        ((ImageTypeViewHolder) holder).tvLng.setText(longitude);
        ((ImageTypeViewHolder) holder).tvRatingCount.setText(String.valueOf(object.rating));
        builder.build().load(dataset.get(position).image).into(((ImageTypeViewHolder) holder).image);
//        ((ImageTypeViewHolder) holder).tvPhone.setText(object.phone);
       // ((ImageTypeViewHolder) holder).image.setText(object.image);



    }
    @Override
    public int getItemCount () {
        return dataset.size();
    }
}
