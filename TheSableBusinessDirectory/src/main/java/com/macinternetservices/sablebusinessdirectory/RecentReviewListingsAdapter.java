package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.Geofence;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.macinternetservices.sablebusinessdirectory.MainActivity.GEOFENCE_EXPIRATION_IN_MILLISECONDS;


public class RecentReviewListingsAdapter extends RecyclerView.Adapter {

    private ArrayList<RecentReviewListingsModel> dataset;
    private Context mContext;

    public RecentReviewListingsAdapter(ArrayList<RecentReviewListingsModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRestaurantName, tvDesc, tvRatingCount, tvId, tvPrice;
        ImageView image;
        RatingBar simpleRatingBar;

        public MyViewHolder(View view) {
            super(view);
            this.txtRestaurantName = view.findViewById(R.id.txtRestaurantName);
            this.tvDesc = view.findViewById(R.id.tvDescription);
            //this.tvPrice = view.findViewById(R.id.tvPrice);
            this.simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            this.tvRatingCount = view.findViewById(R.id.tvRatingCount);
            this.image = view.findViewById(R.id.imgRestaurant);
            this.tvId = view.findViewById(R.id.tvId);

            image.setOnClickListener(v -> {
               // Intent showReviews = new Intent(v.getContext(), ListReviewActivity.class);
                for (int i = 0; i < MainActivity.verticalList.size(); i++) {
                    //String fooId = tvId.getText().toString();
                    //Integer fooId2 = MainActivity.verticalList.get(i).id;

                    if (tvId.getText().toString().equals(MainActivity.verticalList.get(i).id)) {
                        ArrayList<ListingsModel> locationReviewShow = new ArrayList<>();

                        locationReviewShow.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                                MainActivity.verticalList.get(i).id,
                                MainActivity.verticalList.get(i).title,
                                MainActivity.verticalList.get(i).link,
                                MainActivity.verticalList.get(i).status,
                                MainActivity.verticalList.get(i).category,
                                MainActivity.verticalList.get(i).featured,
                                MainActivity.verticalList.get(i).featured_image,
                                MainActivity.verticalList.get(i).bldgno,
                                MainActivity.verticalList.get(i).street,
                                MainActivity.verticalList.get(i).city,
                                MainActivity.verticalList.get(i).state,
                                MainActivity.verticalList.get(i).country,
                                MainActivity.verticalList.get(i).zipcode,
                                MainActivity.verticalList.get(i).latitude,
                                MainActivity.verticalList.get(i).longitude,
                                MainActivity.verticalList.get(i).rating,
                                MainActivity.verticalList.get(i).ratingCount,
                                MainActivity.verticalList.get(i).phone,
                                MainActivity.verticalList.get(i).email,
                                MainActivity.verticalList.get(i).website,
                                MainActivity.verticalList.get(i).twitter,
                                MainActivity.verticalList.get(i).facebook,
                                MainActivity.verticalList.get(i).video,
                                MainActivity.verticalList.get(i).hours,
                                MainActivity.verticalList.get(i).isOpen,
                                MainActivity.verticalList.get(i).logo,
                                MainActivity.verticalList.get(i).content,
                                MainActivity.verticalList.get(i).featured_image,
                                MainActivity.verticalList.get(i).content,
                                new SimpleGeofence(MainActivity.verticalList.get(i).title, MainActivity.verticalList.get(i).latitude, MainActivity.verticalList.get(i).longitude,
                                        100, GEOFENCE_EXPIRATION_IN_MILLISECONDS, MainActivity.verticalList.get(i).featured_image,
                                        Geofence.GEOFENCE_TRANSITION_ENTER
                                                | Geofence.GEOFENCE_TRANSITION_DWELL
                                                | Geofence.GEOFENCE_TRANSITION_EXIT))));

                        Intent showReviews = new Intent(v.getContext(), ListReviewActivity.class);

                        Bundle locationReviewBundle = new Bundle();
                        locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReviewShow);
                        showReviews.putExtra("locationReview", locationReviewShow);
                        itemView.getContext().startActivity(showReviews);
                        //startActivity(showReviews);
                        break;
                    }
                }
            });
        }


    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_review_listings, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int position) {

        //final WooModel object = dataset.get(position);

        Picasso.Builder builder = new Picasso.Builder(mContext);


        ((MyViewHolder) holder).txtRestaurantName.setText(dataset.get(position).authorName);
        ((MyViewHolder) holder).tvDesc.setText(dataset.get(position).description);
        ((MyViewHolder) holder).tvId.setText(String.valueOf(dataset.get(position).parent));
        ((MyViewHolder) holder).simpleRatingBar.setRating(Float.valueOf(dataset.get(position).rating));
        ((MyViewHolder) holder).tvRatingCount.setText(String.valueOf(dataset.get(position).ratingCount));
        builder.build().load(dataset.get(position).image).into(((MyViewHolder) holder).image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}