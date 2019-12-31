package com.sable.businesslistingapi;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private static final int toValue = 20;
    private static final int fromValue = 0;
    private static final int FRAME_TIME_MS = 12000;
    List<String> words = new ArrayList<>();
    //int images = new ArrayList<>();
    private ImageSwitcher imageSwitcher;
    private boolean firstImage;

    private Handler imageSwitchHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        mTextSwitcher =  findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        mTextSwitcher.setInAnimation(this, R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        imageSwitcher =  findViewById(R.id.imageSwitcher);

                ImageView imageView = new ImageView(AboutUs.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                imageView.setLayoutParams(params);


        firstImage = true;
        //imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);

        Animation imgAnimationIn =  AnimationUtils.
                loadAnimation(this,   R.anim.fade_in);
        imageSwitcher.setInAnimation(imgAnimationIn);

        Animation imgAnimationOut =  AnimationUtils.
                loadAnimation(this,   R.anim.fade_out);
        imageSwitcher.setOutAnimation(imgAnimationOut);

        imageSwitchHandler = new Handler();
        imageSwitchHandler.post(runnableCode);


        //  onSwitch(null);
    }

    private Runnable runnableCode = new Runnable() {
        int count = 0;
       // String image;
        @Override
        public void run() {

            String [] text = {
                    "Hello, and welcome to The Sable Business Directory.  The Sable Business Directory is designed to help those wanting to support " +
                            "and frequent black owned businesses and service providers find black owned " +
                            "businesses and service providers.",

                    "We provide a one of a kind online platform that combines " +
                            "a searchable geographical based geo-directory, social media and e-commerce platforms " +
                            "catered specifically to black owned businesses and service providers. ",

                    "We provide a one of a kind online platform that combines " +
                            "a searchable geographical based geo-directory, social media and e-commerce platforms " +
                            "catered specifically to black owned businesses and service providers. ",

                    "Tap 'Continue' to learn more about our geo-directory, social media and e-commerce platforms or " +
                            "tap exit to begin using the director to find black owned bussinesses and service providers near you."};

            int [] images = { R.mipmap.spokesman_hello_foreground, R.mipmap.spokesman1_foreground,
                    R.mipmap.spokesman2_foreground, R.mipmap.spokesman3_foreground};


            if (count != images.length) {
                imageSwitcher.setImageResource(images[count]);
                mTextSwitcher.setText(text[count]);
                count++;
                imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
            }else {
                imageSwitchHandler.removeCallbacksAndMessages(null);
                }
            }
    };
    @Override
    protected void onStop() {
        imageSwitchHandler.removeCallbacks(runnableCode);
        super.onStop();
    }
}
