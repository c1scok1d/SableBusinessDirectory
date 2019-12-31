package com.sable.businesslistingapi;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AboutUs extends AppCompatActivity {
    private static final String[] TEXTS = { "Image #1", "Image #2", "Image #3" };
    private static final int[] IMAGES = { R.mipmap.ic_launcher, R.mipmap.spokesman_foreground, R.mipmap.spokesman_round };
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;
    private static final int toValue = 20;
    private static final int fromValue = 0;
    private static final int FRAME_TIME_MS = 15000;
    List<String> words = new ArrayList<>();
    Thread updateMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        mTextSwitcher =  findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        mImageSwitcher =  findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(AboutUs.this);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);



        words.add("We provide a one of a kind online platform that combines " +
                "a searchable geographical based geo-directory, social media and e-commerce platforms " +
                "catered specifically to black owned businesses and service providers. ");
        words.add("The Sable Business Directory is designed to help those wanting to support " +
                "and frequent black owned businesses and service providers find black owned " +
                "businesses and service providers.");
        words.add("Tap our spokesman to the right for an introduction and tutorial on what " +
                "The Sable Business Directory is and how it works!!!");

        onSwitch(null);
    }

    public void onSwitch(View view) {
        mTextSwitcher.setText(words.get(mPosition));
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % words.size();
    }


}
