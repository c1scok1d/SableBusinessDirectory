package com.sable.businesslistingapi;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AboutUs extends AppCompatActivity {
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher, mTextSwitcher2;
    private static final int toValue = 20;
    private static final int fromValue = 0;
    private static final int FRAME_TIME_MS = 12000;
    List<String> words = new ArrayList<>();
    //int images = new ArrayList<>();
    private ImageSwitcher imageSwitcher;
    private boolean firstImage;
    Button button2;

    private Handler imageSwitchHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        button2 = findViewById(R.id.button2);
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



        mTextSwitcher2 =  findViewById(R.id.textSwitcher2);
        mTextSwitcher2.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        mTextSwitcher2.setInAnimation(this, R.anim.fade_in);
        mTextSwitcher2.setOutAnimation(this, android.R.anim.fade_out);



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

        Animation flip =  AnimationUtils.
                loadAnimation(this,   R.anim.flip);
        imageSwitcher.setOutAnimation(imgAnimationOut);

        imageSwitchHandler = new Handler();
        imageSwitchHandler.post(runnableCode);

        button2.setVisibility(View.GONE);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageSwitcher.startAnimation(anim);

            }
        });

        int amountToMoveRight = 100;
        int  amountToMoveDown = 0;
        TranslateAnimation anim = new TranslateAnimation(0, amountToMoveRight, 0, amountToMoveDown);
        anim.setDuration(1000);

        anim.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)imageSwitcher.getLayoutParams();
                params.topMargin += amountToMoveDown;
                params.leftMargin += amountToMoveRight;
                imageSwitcher.setLayoutParams(params);
            }
        });
        imageSwitcher.startAnimation(anim);
        leftCenterLayout  = findViewById(R.id.leftCenterLayout);

    }
    LinearLayout leftCenterLayout;

    int count = 0, i;
    private Runnable runnableCode = new Runnable() {
        Random randomGenerator = new Random();


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
                            "tap exit to begin using the director to find black owned bussinesses and service providers near you.",

                    "From mobile phones to dentist services, it’s rare to blindly make a purchase decision without reading " +
                    "through several online reviews. In 2016, 90% of shoppers read at least one online review before deciding " +
                    "to visiting a business."};

            int [] images = { R.mipmap.spokesman_hello_foreground, R.mipmap.spokesman1_foreground,
                    R.mipmap.spokesman2_foreground, R.mipmap.spokesman3_foreground};


            boolean isEven = i % 2 == 0;
            if(count >= 3) {
                count = 0;
            }

            if (isEven) {
                imageSwitcher.setImageResource(images[count]);
                mTextSwitcher2.setText(text[count]);
                i = randomGenerator.nextInt(100);
                mTextSwitcher.setVisibility(View.GONE);
                leftCenterLayout.setVisibility(View.VISIBLE);
                imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                count ++;
            }else {
                imageSwitcher.setImageResource(images[count]);
                mTextSwitcher.setText(text[count]);
                i = randomGenerator.nextInt(100);
               leftCenterLayout.setVisibility(View.GONE);
                mTextSwitcher.setVisibility(View.VISIBLE);
                imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                count ++;
            }
        }
    };



    @Override
    protected void onStop() {
        imageSwitchHandler.removeCallbacks(runnableCode);
        super.onStop();
    }
}
