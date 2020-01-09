package com.sable.businesslistingapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class RecentReviewListingsAdapter extends BaseAdapter {

    private ArrayList<RecentReviewListingsModel> dataset;
    private Context mContext;

    public RecentReviewListingsAdapter(ArrayList<RecentReviewListingsModel> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RecentReviewListingsModel data = dataset.get(position);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_book, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);
            final RatingBar ratingBar = convertView.findViewById(R.id.ratingBar2);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, authorTextView, imageViewCoverArt, imageViewFavorite, ratingBar);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
//    viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        viewHolder.nameTextView.setText(dataset.get(position).title);
        viewHolder.authorTextView.setText(dataset.get(position).authorName);
        viewHolder.ratingBar.setRating(dataset.get(position).rating);
        //viewHolder.imageViewFavorite.setImageResource(book.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(dataset.get(position).image).into(viewHolder.imageViewCoverArt);

        return convertView;
    }

    private class ViewHolder {
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;
        private final RatingBar ratingBar;

        public ViewHolder(TextView nameTextView, TextView authorTextView, ImageView imageViewCoverArt, ImageView imageViewFavorite, RatingBar ratingBar) {
            this.nameTextView = nameTextView;
            this.authorTextView = authorTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
            this.ratingBar = ratingBar;
        }
    }

}