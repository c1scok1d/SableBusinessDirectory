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


public class RecentListingsAdapter extends RecyclerView.Adapter {

    ArrayList<RecentListingsModel> dataset;

    private Context mContext;


    public RecentListingsAdapter(ArrayList<RecentListingsModel> mlist, Context context) {
        dataset = mlist;
        this.mContext = context;

    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvContent, tvRatingCount, tvId;
        ImageView ivFeaturedImage;
        RatingBar simpleRatingBar;


        public MyViewHolder(View view, ArrayList<RecentListingsModel> mlist) {
            super(view);
            this.tvName = view.findViewById(R.id.tvName);
            this.tvContent = view.findViewById(R.id.tvContent);
            this.simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            this.tvRatingCount = view.findViewById(R.id.tvRatingCount);
            this.ivFeaturedImage = view.findViewById(R.id.ivFeaturedImage);
            this.tvId = view.findViewById(R.id.tvId);
            ArrayList<ListingsModel> locationReviewShow = new ArrayList<>();


            ivFeaturedImage.setOnClickListener(v -> {
                Intent showReviews = new Intent(v.getContext(), ListReviewActivity.class);


                for (int i = 0; i < dataset.size(); i++) {

                    if ((dataset.get(i).id == Integer.parseInt(tvId.getText().toString()))) {

                        locationReviewShow.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                                dataset.get(i).id,
                                dataset.get(i).title,
                                dataset.get(i).link,
                                dataset.get(i).status,
                                dataset.get(i).category,
                                dataset.get(i).featured,
                                dataset.get(i).featured_image,
                                dataset.get(i).bldgno,
                                dataset.get(i).street,
                                dataset.get(i).city,
                                dataset.get(i).state,
                                dataset.get(i).country,
                                dataset.get(i).zipcode,
                                dataset.get(i).latitude,
                                dataset.get(i).longitude,
                                dataset.get(i).rating,
                                dataset.get(i).ratingCount,
                                dataset.get(i).phone,
                                dataset.get(i).email,
                                dataset.get(i).website,
                                dataset.get(i).twitter,
                                dataset.get(i).facebook,
                                dataset.get(i).video,
                                dataset.get(i).hours,
                                dataset.get(i).isOpen,
                                dataset.get(i).logo,
                                dataset.get(i).content,
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_listings, parent, false);
        return new MyViewHolder(view, dataset);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int position) {

        //final WooModel object = dataset.get(position);

        Picasso.Builder builder = new Picasso.Builder(mContext);


        ((MyViewHolder) holder).tvName.setText(dataset.get(position).title);
        ((MyViewHolder) holder).tvId.setText(String.valueOf(dataset.get(position).id));
        ((MyViewHolder) holder).tvContent.setText(dataset.get(position).content);
        ((MyViewHolder) holder).simpleRatingBar.setRating(dataset.get(position).rating);
        ((MyViewHolder) holder).tvRatingCount.setText(String.valueOf(dataset.get(position).ratingCount));
        builder.build().load(dataset.get(position).featured_image).into(((MyViewHolder) holder).ivFeaturedImage);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}