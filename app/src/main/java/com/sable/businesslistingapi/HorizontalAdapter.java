package com.sable.businesslistingapi;

import android.content.Context;
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


public class HorizontalAdapter extends RecyclerView.Adapter {

    private ArrayList<WooModel> dataset;
    private Context mContext;

    public HorizontalAdapter(ArrayList<WooModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRestaurantName, txtDesc, tvPrice;
        ImageView image;
        RatingBar simpleRatingBar;

        public MyViewHolder(View view) {
            super(view);
            this.txtRestaurantName = view.findViewById(R.id.txtRestaurantName);
            this.txtDesc = view.findViewById(R.id.txtDesc);
            this.tvPrice = view.findViewById(R.id.tvPrice);
            this.simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
           // this.tvRating = view.findViewById(R.id.tvRatingCount);
            this.image = view.findViewById(R.id.imgRestaurant);
        }
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.woo_product_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int position) {

        final WooModel object = dataset.get(position);

        Picasso.Builder builder = new Picasso.Builder(mContext);


        ((MyViewHolder) holder).txtRestaurantName.setText(object.name);
        ((MyViewHolder) holder).txtDesc.setText(object.description);
        ((MyViewHolder) holder).tvPrice.setText(object.price);
        ((MyViewHolder) holder).simpleRatingBar.setRating(object.averageRating);
//        ((MyViewHolder) holder).tvRating.setText((object.ratingCount));
        builder.build().load(dataset.get(position).image).into(((MyViewHolder) holder).image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}