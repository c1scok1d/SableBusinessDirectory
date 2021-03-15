/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.macinternetservices.sablebusinessdirectory;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Random;

/**
 * Displays rationale for allowing the activity recognition permission and allows user to accept
 * the permission. After permission is accepted, finishes the activity so main activity can
 * show transitions.
 */
public class PermissionRationaleActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = "PermissionRational";

    /* Id to identify Activity Recognition permission request. */
    //private static final int PERMISSION_BACKGROUD_LOCATION = 45;
    private static final int REQUEST_READ_PHONE_STATE = 110 ,
            REQUEST_ACCESS_FINE_LOCATION = 111,
            REQUEST_WRITE_STORAGE = 112,
            REQUEST_INTERNET_STATE =113,
            REQUEST_ACCESS_COARSE_LOCATION = 114,
            REQUEST_READ_STORAGE = 115,
            REQUEST_CAMERA =116,
            REQUEST_NETWORK = 117,
            REQUEST_CALL_PHONE = 118,
            REQUEST_BACKGROUND_LOCATION = 119,
            REQUEST_FOREGROUND_SERVICE = 120;


    TextSwitcher textSwitcher, textSwitcher2, textSwitcher3;
    ImageView imageView, imageView2, imageView3;
    private static final int FRAME_TIME_MS = 10000;

    private Handler imageSwitchHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_rationale);
        Animation imgAnimationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        /**
         * ABOUT US
         */
       imageView = findViewById(R.id.imageView);
       //imageView.setVisibility(View.GONE);
       imageView2 = findViewById(R.id.imageView2);
       //imageView2.setVisibility(View.GONE);
       //imageView3 = findViewById(R.id.imageView3);
       //imageView3.setVisibility(View.GONE);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);



        ImageView imageView2 = new ImageView(getApplicationContext());
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams imageView2params = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView2.setLayoutParams(imageView2params);


        ImageView imageView3 = new ImageView(getApplicationContext());
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams imageView3params = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView3.setLayoutParams(imageView3params);

        imageSwitchHandler = new Handler();
        imageSwitchHandler.post(runnableCode);

        /**
         *  txt switchers for animations
         */
        //textSwitcherLayout = findViewById(R.id.textSwitcherLayout);
        LinearLayout textSwitcherLayout = new LinearLayout(getApplicationContext());
        ViewGroup.LayoutParams textSwitcherLayoutParams = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textSwitcherLayout.setLayoutParams(textSwitcherLayoutParams);
        textSwitcherLayout.setAnimation(imgAnimationIn);
        textSwitcherLayout.setAnimation(imgAnimationOut);
        textSwitcherLayout.post(runnableCode);

        textSwitcher = findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(() -> {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent2));
            return textView;
        });

        /*
        LinearLayout textSwitcher2Layout = new LinearLayout(getApplicationContext());
        ViewGroup.LayoutParams textSwitcher2LayoutParams = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textSwitcher2Layout.setLayoutParams(textSwitcher2LayoutParams);
        textSwitcher2Layout.setAnimation(imgAnimationIn);
        textSwitcher2Layout.setAnimation(imgAnimationOut);
        textSwitcher2Layout.post(runnableCode);

        textSwitcher2 = findViewById(R.id.textSwitcher2);
        textSwitcher2.setFactory(() -> {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            return textView;
        });

        //textSwitcher3Layout = findViewById(R.id.textSwitcher3Layout);
        LinearLayout textSwitcher3Layout = new LinearLayout(getApplicationContext());
        ViewGroup.LayoutParams textSwitcher3LayoutParams = new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textSwitcher3Layout.setLayoutParams(textSwitcher3LayoutParams);
        textSwitcher3Layout.setAnimation(imgAnimationIn);
        textSwitcher3Layout.setAnimation(imgAnimationOut);
        textSwitcher3Layout.post(runnableCode);

        textSwitcher3 = findViewById(R.id.textSwitcher3);
        textSwitcher3.setFactory(() -> {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent2));
            return textView;
        }); */
    }

    //@RequiresApi(api = Build.VERSION_CODES.Q)
    public void onClickApprovePermissionRequest(View view) {
        Log.e(TAG, "onClickApprovePermissionRequest()");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            checkPermissionsQ();
        } else {
            checkPermissions();
        }


    }
    //@RequiresApi(api = Build.VERSION_CODES.Q)
    private  boolean checkPermissions() {

         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_STATE);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_ACCESS_COARSE_LOCATION);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_STORAGE);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_NETWORK);
        } else  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
         }  else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(this,
                     new String[]{Manifest.permission.FOREGROUND_SERVICE},
                     REQUEST_FOREGROUND_SERVICE);
         } else {
            //Ask se to geo to settings and manually allow permissions
            showDialog("", "You have denied some permissions.  Allow all permissions at [Settings] > [Permissions]",
                    "Go to Settings",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            //Go to app settings
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", getPackageName(), null));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    },
                    "No, Exit app", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            ;
                            fileList();
                        }
                    }, false);
        }
        return true;
    }
    private void showExplanation(String title,
                                 String message,
                                 final String[] permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(PermissionRationaleActivity.this,
                                permission,
                                permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private  boolean checkPermissionsQ() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_STATE);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_ACCESS_COARSE_LOCATION);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_PHONE_STATE);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_NETWORK);
        } else  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        } else  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    REQUEST_BACKGROUND_LOCATION);
        }  else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.FOREGROUND_SERVICE},
                    REQUEST_FOREGROUND_SERVICE);
        } else/* if  (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) */{
            //Ask se to geo to settings and manually allow permissions
            showDialog("", "You have denied some permissions.  Allow all permissions at [Settings] > [Permissions]",
                    "Go to Settings",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            //Go to app settings
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", getPackageName(), null));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    },
                    "No, Exit app", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            ;
                            fileList();
                        }
                    }, false);
        }
        return true;
    }

    public AlertDialog showDialog(String title, String msg, String positiveLabel, DialogInterface.OnClickListener positiveOnClick,
                                  String negativeLabel, DialogInterface.OnClickListener negativeOnClick, boolean isCancelAble){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(isCancelAble);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveLabel, positiveOnClick);
        builder.setNegativeButton(negativeLabel, negativeOnClick);

        AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }

    public void onClickDenyPermissionRequest(View view) {
        Log.e(TAG, "onClickDenyPermissionRequest()");
        showDialog("", "You have denied some permissions.  Allow all permissions at [Settings] > [Permissions]",
                "Go to Settings",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //Go to app settings
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                },
                "No, Exit app", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        ;
                        fileList();
                    }
                }, false);
        //finish();
    }

    /*
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        String permissionResult = "Request code: " + requestCode + ", Permissions: " +
                Arrays.toString(permissions) + ", Results: " + Arrays.toString(grantResults);

        Log.e(TAG, "onRequestPermissionsResult(): " + permissionResult);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    checkPermissionsQ();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();            //h.postDelayed(r, 1500);
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    checkPermissions();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }


    /**
     * ANIMATIONS
     */


    private Runnable runnableCode = new Runnable() {

       // Random r = new Random();
        //int random = (int)(Math.random()*8);

        // String image;
        @Override
        public void run() {
            Animation imgAnimationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            Animation imgAnimationOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);


            String[] text = {
                    "Welcome to The Sable Business Directory!\n",


                    "The Sable Business Directory is designed to help those wanting to support " +
                            "and frequent black owned businesses and service providers find black owned" +
                            "businesses and service providers.",

                    "We provide a one of a kind online platform that makes it easier to find, rate " +
                            "and review black owned businesses and service providers.",

                    "We have combined geo-search, social media and e-commerce technologies to create an online " +
                            "platform dedicated to the continued growth and support of black owned businesses.",

                    "To insure high quality services, customers maintain the directory by adding and " +
                            "reviewing the black owned businesses and service providers they frequent.",

                    "Our combined technologies then compile those listings, ratings and reviews to " +
                            "provide a directory listing of black owned business and service providers " +
                            "near your current location.",

                    "88% of people trust online reviews. Online reviews are an important way you can increase " +
                            "sales for your business. This is especially important for local businesses and service providers.",

                    "Adding and reviewing listings is easy. To protect the privacy of our users and insure high quality feedback " +
                            "we require users to login before adding or reviewing a listing.",

                    "Tap below to begin adding and reviewing black owned businesses using your Facebook account."

            };

            int[] images = {R.mipmap.hello_foreground, R.mipmap.showing_right_foreground,
                    R.mipmap.one_of_akind_foreground, R.mipmap.showing_tablet_foreground, R.mipmap.holding_phone_foreground, R.mipmap.making_thumbs_up_foreground,
                    R.mipmap.online_reviews_foreground, R.mipmap.showing_with_left_hand_foreground, R.mipmap.smiling_peace_foreground};

                int random = new Random().nextInt(text.length);

                switch (random){
                    case 0:
                    case 1:
                    case 5:
                        imageView2.setAnimation(imgAnimationOut);
                        //imageView2.setVisibility(View.GONE);
                        imageView.setAnimation(imgAnimationOut);
                        //imageView.setVisibility(View.GONE);

                        textSwitcher.setText(text[random]);
                        textSwitcher.setAnimation(imgAnimationIn);
                        //textSwitcher.setVisibility(View.VISIBLE);

                        imageView2.setImageResource(images[random]);
                        imageView2.setAnimation(imgAnimationIn);
                        //imageView2.setVisibility(View.VISIBLE);

                        imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                    break;
                    case 2:
                    case 6:
                    case 8:
                        imageView2.setAnimation(imgAnimationOut);
                        //imageView2.setVisibility(View.GONE);
                        imageView.setAnimation(imgAnimationOut);
                        //imageView.setVisibility(View.GONE);


                        textSwitcher.setText(text[random]);
                        textSwitcher.setAnimation(imgAnimationIn);
                        //textSwitcher.setVisibility(View.VISIBLE);

                        imageView.setImageResource(images[random]);
                        imageView.setAnimation(imgAnimationIn);
                        //imageView.setVisibility(View.VISIBLE);

                        imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                    break;
                    case 3:
                    case 4:
                    case 7:
                        imageView.setAnimation(imgAnimationOut);
                        //imageView.setVisibility(View.GONE);
                        imageView2.setAnimation(imgAnimationOut);
                        //imageView2.setVisibility(View.GONE);

                        textSwitcher.setText(text[random]);
                        textSwitcher.setAnimation(imgAnimationIn);
                        //textSwitcher.setVisibility(View.VISIBLE);

                        imageView.setImageResource(images[random]);
                        imageView.setAnimation(imgAnimationIn);
                        //imageView.setVisibility(View.VISIBLE);

                        imageSwitchHandler.postDelayed(this, FRAME_TIME_MS);
                   break;
                }
        }
    };
}
