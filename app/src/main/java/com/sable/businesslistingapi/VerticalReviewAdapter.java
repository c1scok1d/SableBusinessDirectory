package com.sable.businesslistingapi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VerticalReviewAdapter extends RecyclerView.Adapter {

    ArrayList<ListReviewModel> dataset;
    Context mContext;


    public VerticalReviewAdapter(ArrayList<ListReviewModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView  tvContent, tvRatingTitle, tvCity, tvState, tvCountry, tvAuthor;
        RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            this.tvCountry = view.findViewById(R.id.tvCountry);
            this.tvContent = view.findViewById(R.id.tvContent);
            this.tvRatingTitle = view.findViewById(R.id.tvRatingTitle);
            this.tvCity = view.findViewById(R.id.tvCity);
            this.tvState = view.findViewById(R.id.tvState);
            this.ratingBar = view.findViewById(R.id.ratingBar);
            this.tvAuthor = view.findViewById(R.id.tvAuthor);
        }
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public VerticalReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // String rating = String.valueOf(dataset.get(position).ratingNumber);

        ((MyViewHolder) holder).tvContent.setText(dataset.get(position).content);
        ((MyViewHolder) holder).ratingBar.setRating(dataset.get(position).ratingNumber);
        ((MyViewHolder) holder).tvRatingTitle.setText(dataset.get(position).ratingTitle);
        ((MyViewHolder) holder).tvCity.setText(dataset.get(position).city);
        ((MyViewHolder) holder).tvState.setText(dataset.get(position).state);
        ((MyViewHolder) holder).tvCountry.setText(dataset.get(position).country);
        ((MyViewHolder) holder).tvAuthor.setText(dataset.get(position).author);

        // holder.textView.setText(mDataset.get(0).city);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}