package com.sable.businesslistingapi;

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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class RecentReviewListingsAdapter extends RecyclerView.Adapter {

    private ArrayList<RecentReviewListingsModel> dataset;
    private Context mContext;

    public RecentReviewListingsAdapter(ArrayList<RecentReviewListingsModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRestaurantName, txtDesc, tvRatingCount, tvId;
        ImageView image;
        RatingBar simpleRatingBar;

        public MyViewHolder(View view) {
            super(view);
            this.txtRestaurantName = view.findViewById(R.id.txtRestaurantName);
            this.txtDesc = view.findViewById(R.id.txtDesc);
            //this.tvPrice = view.findViewById(R.id.tvPrice);
            this.simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            this.tvRatingCount = view.findViewById(R.id.tvRatingCount);
            this.image = view.findViewById(R.id.imgRestaurant);
            this.tvId = view.findViewById(R.id.tvId);
            ArrayList<RecentReviewListingsModel> locationReviewShow = new ArrayList<>();

            image.setOnClickListener(v -> {
                Intent showReviews = new Intent(v.getContext(), ListReviewActivity.class);


                for (int i = 0; i < dataset.size(); i++) {

                    if ((dataset.get(i).id == Integer.parseInt(tvId.getText().toString()))) {

                        locationReviewShow.add((new RecentReviewListingsModel(RecentReviewListingsModel.IMAGE_TYPE,
                                dataset.get(i).id,
                                dataset.get(i).link,
                                dataset.get(i).authorName,
                                dataset.get(i).rating,
                                dataset.get(i).timestamp,
                                dataset.get(i).featured_image)));

                        Bundle locationReviewBundle = new Bundle();
                        locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReviewShow);
                        showReviews.putExtra("locationReview", locationReviewShow);
                        itemView.getContext().startActivity(showReviews);
                        break;
                    }
                }
            });
        }


    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int position) {

        //final WooModel object = dataset.get(position);

        Picasso.Builder builder = new Picasso.Builder(mContext);


        ((MyViewHolder) holder).txtRestaurantName.setText(dataset.get(position).title);
        ((MyViewHolder) holder).txtDesc.setText(dataset.get(position).content);
        ((MyViewHolder) holder).tvId.setText(String.valueOf(dataset.get(position).id));
        ((MyViewHolder) holder).simpleRatingBar.setRating(Float.valueOf(dataset.get(position).rating));
        ((MyViewHolder) holder).tvRatingCount.setText(String.valueOf(dataset.get(position).ratingCount));
        builder.build().load(dataset.get(position).featured_image).into(((MyViewHolder) holder).image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}