package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.content.Intent;
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

        TextView txtRestaurantName, txtDesc, tvPrice, tvLink;
        ImageView image;
        RatingBar simpleRatingBar;

        public MyViewHolder(View view) {
            super(view);
            this.txtRestaurantName = view.findViewById(R.id.txtRestaurantName);
            this.txtDesc = view.findViewById(R.id.txtDesc);
            this.tvPrice = view.findViewById(R.id.tvPrice);
            this.simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            this.tvLink = view.findViewById(R.id.tvLink);
            this.image = view.findViewById(R.id.imgRestaurant);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), WooProductDetail.class);
                    intent.putExtra("url", tvLink.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
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

        //final WooModel object = dataset.get(position);

        Picasso.Builder builder = new Picasso.Builder(mContext);


        ((MyViewHolder) holder).txtRestaurantName.setText(dataset.get(position).name);
        ((MyViewHolder) holder).txtDesc.setText(dataset.get(position).description);
        ((MyViewHolder) holder).tvPrice.setText(dataset.get(position).price);
        ((MyViewHolder) holder).simpleRatingBar.setRating(Float.valueOf(dataset.get(position).averageRating));
        ((MyViewHolder) holder).tvLink.setText((dataset.get(position).link));
        builder.build().load(dataset.get(position).image).into(((MyViewHolder) holder).image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}