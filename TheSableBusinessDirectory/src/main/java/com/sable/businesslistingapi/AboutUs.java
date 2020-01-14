package com.sable.businesslistingapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AboutUs extends AppCompatActivity {
    private int mPosition = 0;
    private TextSwitcher textSwitcher, textSwitcher2, textSwitcher3;
    private static final int toValue = 20;
    private static final int fromValue = 0;
    private static final int FRAME_TIME_MS = 8000;
    List<String> words = new ArrayList<>();
    //int images = new ArrayList<>();
    private ImageSwitcher imageSwitcher, imageSwitcher2, imageSwitcher3;
    private boolean firstImage;
    Button btnLearnMore, btnDirectory;
    LinearLayout textSwitcherLayout, textSwitcher2Layout, textSwitcher3Layout;

    private Handler imageSwitchHandler;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

       /* if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }*/

        textSwitcherLayout = findViewById(R.id.textSwitcherLayout);
        textSwitcher2Layout = findViewById(R.id.textSwitcher2Layout);
        textSwitcher3Layout = findViewById(R.id.textSwitcher3Layout);

        Animation imgAnimationIn =  AnimationUtils.loadAnimation(this,   R.anim.fade_in);
        Animation imgAnimationOut =  AnimationUtils.loadAnimation(this,   R.anim.fade_out);
        Animation imgAnimationflip =  AnimationUtils.loadAnimation(this,   R.anim.flip);

        btnLearnMore = findViewById(R.id.btnLearnMore);
        btnDirectory = findViewById(R.id.btnDirectory);
        textSwitcher =  findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        textSwitcher.setInAnimation(imgAnimationIn);
        textSwitcher.setOutAnimation(imgAnimationOut);

        textSwitcher2 =  findViewById(R.id.textSwitcher2);
        textSwitcher2.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        textSwitcher2.setInAnimation(imgAnimationIn);
        textSwitcher2.setOutAnimation(imgAnimationOut);

        textSwitcher3 =  findViewById(R.id.textSwitcher3);
        textSwitcher3.setFactory(() -> {
            TextView textView = new TextView(AboutUs.this);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        textSwitcher3.setInAnimation(imgAnimationIn);
        textSwitcher3.setOutAnimation(imgAnimationOut);



        imageSwitcher =  findViewById(R.id.imageSwitcher);

                ImageView imageView = new ImageView(AboutUs.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                imageView.setLayoutParams(params);

        imageSwitcher2 =  findViewById(R.id.imageSwitcher2);

        ImageView imageView2 = new ImageView(AboutUs.this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ViewGroup.LayoutParams imageView2params = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        imageView2.setLayoutParams(imageView2params);

        imageSwitcher3 =  findViewById(R.id.imageSwitcher3);

        ImageView imageView3 = new ImageView(AboutUs.this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ViewGroup.LayoutParams imageView3params = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        imageView3.setLayoutParams(imageView3params);


        firstImage = true;

        imageSwitchHandler = new Handler();
        imageSwitchHandler.post(runnableCode);

        /**
         *  strt fuckin' around with getting linearLayouts to fade in and out
         */
        textSwitcherLayout =  findViewById(R.id.textSwitcherLayout);

        LinearLayout textSwitcherLayout = new LinearLayout(AboutUs.this);

        ViewGroup.LayoutParams textSwitcherLayoutParams = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textSwitcherLayout.setLayoutParams(textSwitcherLayoutParams);


        textSwitcherLayout.setAnimation(imgAnimationIn);
        textSwitcherLayout.setAnimation(imgAnimationOut);
        textSwitcherLayout.post(runnableCode);

        textSwitcher2Layout =  findViewById(R.id.textSwitcher2Layout);

        LinearLayout textSwitcher2Layout = new LinearLayout(AboutUs.this);


        textSwitcher2Layout.setLayoutParams(textSwitcherLayoutParams);

        textSwitcher2Layout.setAnimation(imgAnimationIn);
        textSwitcher2Layout.setAnimation(imgAnimationOut);
        textSwitcher2Layout.post(runnableCode);

        textSwitcher3Layout =  findViewById(R.id.textSwitcher3Layout);

        LinearLayout textSwitcher3Layout = new LinearLayout(AboutUs.this);


        textSwitcher3Layout.setLayoutParams(textSwitcherLayoutParams);

        textSwitcher3Layout.setAnimation(imgAnimationIn);
        textSwitcher3Layout.setAnimation(imgAnimationOut);
        textSwitcher3Layout.post(runnableCode);

        /**
         * end fuckin' around with getting lienarlayouts to fade in and out
         */

        btnDirectory.setVisibility(View.GONE);
        btnLearnMore.setVisibility(View.GONE);


        btnDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(AboutUs.this, MainActivity.class);
                startActivity(MainActivity);
            }
        });

        btnLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(AboutUs.this, AboutUsVideo.class);
                startActivity(MainActivity);

            }
        });

        imageSwitcher.setVisibility(View.GONE);
        imageSwitcher2.setVisibility(View.GONE);
        imageSwitcher3.setVisibility(View.GONE);

        textSwitcherLayout.setVisibility(View.GONE);
        textSwitcher2Layout.setVisibility(View.GONE);
        textSwitcher3Layout.setVisibility(View.GONE);
    }

    int count = 0, i;
    private Runnable runnableCode = new Runnable() {
        Random randomGenerator = new Random();


       // String image;
        @Override
        public void run() {
            Animation imgAnimationIn =  AnimationUtils.loadAnimation( getApplicationContext(),  R.anim.fade_in);
            Animation imgAnimationOut =  AnimationUtils.loadAnimation(getApplicationContext(),   R.anim.fade_out);
            Animation imgAnimationflip =  AnimationUtils.loadAnimation(getApplicationContext(),   R.anim.flip);


            /*imgAnimationOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    imageSwitcher.setVisibility(View.GONE);
                    imageSwitcher2.setVisibility(View.GONE);
                    imageSwitcher3.setVisibility(View.GONE);

                    textSwitcherLayout.setVisibility(View.GONE);
                    textSwitcher2Layout.setVisibility(View.GONE);
                    textSwitcher3Layout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }

                @Override
                public void onAnimationStart(Animation animation) {
                    imageSwitcher.setVisibility(View.VISIBLE);
                    imageSwitcher2.setVisibility(View.VISIBLE);
                    imageSwitcher3.setVisibility(View.VISIBLE);

                    textSwitcherLayout.setVisibility(View.VISIBLE);
                    textSwitcher2Layout.setVisibility(View.VISIBLE);
                    textSwitcher3Layout.setVisibility(View.VISIBLE);
                }
            });

            imgAnimationIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    imageSwitcher.setVisibility(View.VISIBLE);
                    imageSwitcher2.setVisibility(View.VISIBLE);
                    imageSwitcher3.setVisibility(View.VISIBLE);

                    textSwitcherLayout.setVisibility(View.VISIBLE);
                    textSwitcher2Layout.setVisibility(View.VISIBLE);
                    textSwitcher3Layout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }

                @Override
                public void onAnimationStart(Animation animation) {
                    imageSwitcher.setVisibility(View.GONE);
                    imageSwitcher2.setVisibility(View.GONE);
                    imageSwitcher3.setVisibility(View.GONE);

                    textSwitcherLayout.setVisibility(View.GONE);
                    textSwitcher2Layout.setVisibility(View.GONE);
                    textSwitcher3Layout.setVisibility(View.GONE);
                }
            });*/

            String [] text = {
                    "Hello, and welcome to The Sable Business Directory.  The Sable Business Directory is designed to help those wanting to support " +
                            "and frequent black owned businesses and service providers find black owned businesses and service providers.",

                    "We provide a one of a kind online platform that combines a searchable geographical based geo-directory, social media and e-commerce " +
                            "platforms catered specifically to black owned businesses and service providers. ",

                    "From mobile phones to dentist services, it’s rare to blindly make a purchase decision without reading " +
                            "through several online reviews. In 2016, 90% of shoppers read at least one online review before deciding " +
                            "to visiting a business.",

                    "Tap below to learn more about our geo-directory, social media and e-commerce platforms or " +
                            "tap exit to begin using the directory to find black owned businesses and service providers near you.",

                    };

            int [] images = { R.mipmap.spokesman_hello_foreground, R.mipmap.spokesman1_foreground,
                    R.mipmap.spokesman2_foreground, R.mipmap.spokesman3_foreground};
            switch (count)
            {
                case 1:
                    imageSwitcher.setImageResource(images[count]);
                    textSwitcher2.setText(text[count]);
                    imageSwitcher.setVisibility(View.VISIBLE);
                    textSwitcher2Layout.setVisibility(View.VISIBLE);
                    imageSwitcher.setAnimation(imgAnimationIn);
                    textSwitcher2Layout.setAnimation(imgAnimationIn);
                    imageSwitcher2.setAnimation(imgAnimationOut);
                    textSwitcherLayout.setAnimation(imgAnimationOut);
                    textSwitcherLayout.setVisibility(View.GONE);
                    imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                    i = randomGenerator.nextInt(100);
                    count ++;
                    break;
                case 2:
                    imageSwitcher3.setImageResource(images[count]);
                    textSwitcher3.setText(text[count]);
                    imageSwitcher3.setVisibility(View.VISIBLE);
                    imageSwitcher3.setAnimation(imgAnimationIn);
                    textSwitcher3Layout.setVisibility(View.VISIBLE);
                    textSwitcher3Layout.setAnimation(imgAnimationIn);
                    imageSwitcher.setAnimation(imgAnimationOut);
                    imageSwitcher.setVisibility(View.GONE);
                    textSwitcher2Layout.setAnimation(imgAnimationOut);
                    textSwitcher2Layout.setVisibility(View.GONE);
                    imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                    i = randomGenerator.nextInt(100);
                    count ++;
                    break;

                case 3:
                    imageSwitcher2.setImageResource(images[count]);
                    textSwitcher.setText(text[count]);
                    imageSwitcher2.setVisibility(View.VISIBLE);
                    imageSwitcher2.setAnimation(imgAnimationIn);
                    textSwitcherLayout.setVisibility(View.VISIBLE);
                    textSwitcherLayout.setAnimation(imgAnimationIn);
                    imageSwitcher3.setAnimation(imgAnimationOut);
                    textSwitcher3Layout.setAnimation(imgAnimationOut);
                    btnDirectory.setVisibility(View.VISIBLE);
                    btnDirectory.setAnimation(imgAnimationIn);
                    btnLearnMore.setVisibility(View.VISIBLE);
                    btnLearnMore.setAnimation(imgAnimationIn);
                    imageSwitchHandler.removeCallbacks(runnableCode);
                    i = randomGenerator.nextInt(100);
                    count ++;
                    break;
                default:
                    imageSwitcher2.setVisibility(View.VISIBLE);
                    textSwitcherLayout.setVisibility(View.VISIBLE);
                    imageSwitcher2.setImageResource(images[count]);
                    textSwitcher.setText(text[count]);
                    imageSwitcher2.setAnimation(imgAnimationIn);
                    textSwitcherLayout.setAnimation(imgAnimationIn);

                    imageSwitcher.setVisibility(View.GONE);
                    //imageSwitcher2.setVisibility(View.GONE);
                    imageSwitcher3.setVisibility(View.GONE);

                    //textSwitcherLayout.setVisibility(View.GONE);
                    textSwitcher2Layout.setVisibility(View.GONE);
                    textSwitcher3Layout.setVisibility(View.GONE);

                    imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                    i = randomGenerator.nextInt(100);
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
