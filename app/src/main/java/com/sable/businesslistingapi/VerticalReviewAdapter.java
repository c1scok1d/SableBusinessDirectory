package com.sable.businesslistingapi;


import android.content.Context;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class VerticalReviewAdapter extends RecyclerView.Adapter {

    ArrayList<ListReviewModel> dataset;
    Context mContext;


    public VerticalReviewAdapter(ArrayList<ListReviewModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView  tvContent, tvRatingTitle, tvCity, tvState, tvCountry, tvAuthor, tvDate;
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
            this.tvDate = view.findViewById(R.id.tvDate);
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

        ((MyViewHolder) holder).tvContent.setText(dataset.get(position).content);
        ((MyViewHolder) holder).ratingBar.setRating(dataset.get(position).ratingNumber);
        ((MyViewHolder) holder).tvRatingTitle.setText(dataset.get(position).ratingTitle);
        ((MyViewHolder) holder).tvCity.setText(dataset.get(position).city);
        ((MyViewHolder) holder).tvState.setText(dataset.get(position).state);
        ((MyViewHolder) holder).tvCountry.setText(dataset.get(position).country);
        ((MyViewHolder) holder).tvAuthor.setText(dataset.get(position).author);
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String currDate = simpleDateFormat.format(new Date());

            Date date1 = simpleDateFormat.parse((dataset.get(position).date));
            Date date2 = simpleDateFormat.parse(currDate);

            long different = date2.getTime() - date1.getTime();

            System.out.println("startDate : " + date1);
            System.out.println("endDate : "+ date2);
            System.out.println("different : " + different);

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            String Time = "PostDate : " + date1 +
                    "\nCurrDate : "+ date2 +
                    "\nPosted: "+ elapsedDays + " days " + elapsedHours + " hours " + elapsedMinutes + " minutes " + elapsedSeconds + " seconds ago";
            ((MyViewHolder) holder).tvDate.setText(Time);


        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}