package com.macinternetservices.sablebusinessdirectory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HorizontalImageAdapter extends RecyclerView.Adapter<HorizontalImageAdapter.ViewHolder> {
        private static final String TAG = "RecyclerViewAdapter";

        private ArrayList<String> mImages = new ArrayList<>();
        private ArrayList<String> mNames = new ArrayList<>();
        private Context mcontext;

    public HorizontalImageAdapter(ArrayList<String> mImages, ArrayList<String> mNames, Context mcontext) {
            this.mImages = mImages;
            this.mNames = mNames;
            this.mcontext = mcontext;
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
            //RecyclerView.ViewHolder holder = new ViewHolder(view);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            //Log.d(TAG, "onBindViewHolder: Images Set");
            Glide.with(mcontext)
                    .asBitmap()
                    .load(mImages.get(position))
                    .into(holder.img);

            //holder.txt.setText(mNames.get(position));

            holder.parent_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d(TAG, "onClick: Clicked");
                    Toast.makeText(mcontext, mNames.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mImages.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            //TextView txt;
            CardView parent_layout;


            public ViewHolder( View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.image);
                //txt = itemView.findViewById(R.id.text);
                parent_layout = itemView.findViewById(R.id.parent_layout);
            }
        }
    }
