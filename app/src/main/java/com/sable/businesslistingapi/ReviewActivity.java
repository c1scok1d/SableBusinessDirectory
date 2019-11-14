package com.sable.businesslistingapi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class ReviewActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ImageButton btnPic, btnPicUpload;
    TextView tvPost_title, tvPost_status, tvDefault_category, tvState,
            tvStreet, tvCity, tvZip, tvCountry, tvRating, tvEmail, tvWebsite, tvTwitter, tvFacebook,
            tvVideo, tvHours, tvIsOpen, tvContent, tvPhone, tvBldgno, tvLatitude, tvLongitude, tvTimestamp;

    ImageView ivImage0, ivImage1, ivImage02;
    RatingBar simpleRatingBar;
    private ProgressBar progressBar;
    String baseURL = "https://www.thesablebusinessdirectory.com", id = "12345", username = "android_app", password = "mroK zH6o wOW7 X094 MTKy fwmY", authToken, Document_img1 = "";
    private static final int GALLERY_REQUEST_CODE = 2;
    private static final int CAMERA_REQUEST_CODE = 1;
    Uri imageUri00, imageUri01, imageUri02;
    Uri picUri;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();

    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        permissions.add(CAMERA);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissionsToRequest = findUnAskedPermissions(permissions);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        Picasso.Builder builder = new Picasso.Builder(this);

        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        mFeedback = findViewById(R.id.etFeedback);
        mSendFeedback = findViewById(R.id.btnSubmit);
        simpleRatingBar = findViewById(R.id.simpleRatingBar);
        tvPost_title = findViewById(R.id.etName);
        tvBldgno = findViewById(R.id.tvBldgNo);
        tvState = findViewById(R.id.tvState);
        tvStreet = findViewById(R.id.tvStreet);
        tvCity = findViewById(R.id.tvCity);
        tvZip = findViewById(R.id.tvZip);
        tvCountry = findViewById(R.id.tvCountry);
        tvHours = findViewById(R.id.tvHours);
        tvIsOpen = findViewById(R.id.tvIsOpen);
        tvContent = findViewById(R.id.content);
        tvPhone = findViewById(R.id.etPhone);
        btnPic = findViewById(R.id.btnPic);


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        //Add image to review
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(selectImage(), IMAGE_RESULT);
            }
        });

        /**
         *
         * gets Extras if stored lat/lng equals current lat/lng (onLocationMatch)
         *
         */
        ArrayList<ListingsModel> locationMatch;
        ArrayList<ListingsAddModel> locationAdd;

        locationMatch = this.getIntent().getExtras().getParcelableArrayList("locationMatch");
        locationAdd = this.getIntent().getExtras().getParcelableArrayList("locationAdd");

        if (locationMatch == null) {

           /* tvTitle.setText(locationAdd.get(0).post_title);
            tvStatus.setText(locationAdd.get(0).post_status);
            tvTags.setText(locationAdd.get(0).post_tags);
            default_category.setText(locationAdd.get(0).default_category);
            post_category.setText(locationAdd.get(0).post_category);
            featured.setText(locationAdd.get(0).featured);
            //ivFeaturedImg.setText(locationAdd.get(0).featured_image);*/
            tvBldgno.setText(locationAdd.get(0).bldgNo);
            tvStreet.setText(locationAdd.get(0).street);
            tvCity.setText(locationAdd.get(0).city);
            tvState.setText(locationAdd.get(0).state);
            tvCountry.setText(locationAdd.get(0).country);
            tvZip.setText(locationAdd.get(0).zipcode);
            /*latitude = (locationAdd.get(0).latitude);
            longitude = (locationAdd.get(0).longitude);
            rating.setText(locationAdd.get(0).rating);
            phone.setText(locationAdd.get(0).phone);
            email.setText(locationAdd.get(0).email);
            website.setText(locationAdd.get(0).website);
            twitter.setText(locationAdd.get(0).twitter);
            facebook.setText(locationAdd.get(0).facebook);
            video.setText(locationAdd.get(0).video);
            hours.setText(locationAdd.get(0).hours);
            isOpen.setText(locationAdd.get(0).isOpen);
            //ivLogo.setText(locationAdd.get(0).logo);
            content.setText(locationAdd.get(0).content);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).featured_image)).into(ivFeaturedImg);
            //ivImage.setText(locationAdd.get(0).image);
            timestamp.setText(locationAdd.get(0).timestamp);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).image)).into(ivImage);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).logo)).into(ivLogo);
            //builder.build().load(getIntent().getStringExtra(locationAdd.get(0).img3)).into(image3);*/


        } else {

            tvPost_title.setText(locationMatch.get(0).post_title);
            tvPost_status.setText(locationMatch.get(0).post_status);
            // post_tags.setText(locationMatch.get(0).post_tags);
            tvDefault_category.setText(locationMatch.get(0).default_category);
            //post_category.setText(locationMatch.get(0).post_category);
            //featured.setValu(locationMatch.get(0).featured.isT);
            builder.build().load(getIntent().getStringExtra(locationMatch.get(0).featured_image)).into(ivImage0);
            // ivFeaturedImg.setText(locationMatch.get(0).featured_image);
            tvBldgno.setText(locationMatch.get(0).bldgno);
            tvStreet.setText(locationMatch.get(0).street);
            tvCity.setText(locationMatch.get(0).city);
            tvState.setText(locationMatch.get(0).state);
            tvCountry.setText(locationMatch.get(0).country);
            tvZip.setText(locationMatch.get(0).zip);
            tvLatitude.setText(locationMatch.get(0).latitude.toString());
            tvLongitude.setText(locationMatch.get(0).longitude.toString());
            tvRating.setText(locationMatch.get(0).rating);
            tvPhone.setText(locationMatch.get(0).phone);
            tvEmail.setText(locationMatch.get(0).email);
            tvWebsite.setText(locationMatch.get(0).website);
            tvTwitter.setText(locationMatch.get(0).twitter);
            tvFacebook.setText(locationMatch.get(0).facebook);
            tvVideo.setText(locationMatch.get(0).video);
            tvHours.setText(locationMatch.get(0).hours);
            tvIsOpen.setText(locationMatch.get(0).isOpen);
            builder.build().load(getIntent().getStringExtra(locationMatch.get(0).logo)).into(ivImage1);
            //tvLogo.setText(locationMatch.get(0).logo);
            tvContent.setText(locationMatch.get(0).content);
            builder.build().load(getIntent().getStringExtra(locationMatch.get(0).image)).into(ivImage02);
            //tvImage.setText(locationMatch.get(0).image);
            tvTimestamp.setText(locationMatch.get(0).timestamp);
            //builder.build().load(getIntent().getStringExtra(locationMatch.get(0).image)).into(image02);
        }

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {

                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(ReviewActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                    // submitData();
                }
            }
        });
    }

    /**
     * Chooser intent to select the source to get image from.<br />
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br />
     * All possible sources are added to the intent chooser.
     */
    public Intent selectImage() {

        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.sable.businesslistingapi")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalFilesDir("");
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //ImageView imageView = findViewById(R.id.imageView);
            LinearLayout linearLayout = findViewById(R.id.imagesLayout);
            for (int i = 0; i < 6; i++) {
                ImageView imageView = new ImageView(ReviewActivity.this);
                int dimens = 45;
                float density = getResources().getDisplayMetrics().density;
                int finalDimens = (int) (dimens * density);


                // SET SCALETYPE
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                // SET THE MARGIN
                int dimensMargin = 4;
                float densityMargin = getResources().getDisplayMetrics().density;
                int finalDimensMargin = (int) (dimensMargin * densityMargin);

                LinearLayout.LayoutParams imgvwDimens = new LinearLayout.LayoutParams(finalDimens, finalDimens);
                imageView.setLayoutParams(imgvwDimens);

                if (requestCode == IMAGE_RESULT) {
                    String filePath = getImageFilePath(data);
                    if (filePath != null) {
                        Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                        // SET THE IMAGEVIEW DIMENSIONS

                        LinearLayout.LayoutParams imgvwMargin = new LinearLayout.LayoutParams(finalDimens, finalDimens);
                        imgvwMargin.setMargins(finalDimensMargin, finalDimensMargin, finalDimensMargin, finalDimensMargin);

                        // SET YOUR IMAGER SOURCE TO YOUR NEW IMAGEVIEW HERE
                        // FORMAT AND ADD THE NEW IMAGEVIEW WITH TO THE linearLayout
                        imageView.setLayoutParams(imgvwMargin);
                        imageView.setImageBitmap(selectedImage);
                        linearLayout.addView(imageView);
                    }
                }
            }
        }
    }


    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;

        if (isCamera) return getCaptureImageOutputUri().getPath();
        else return getPathFromURI(data.getData());

    }

    public String getImageFilePath(Intent data) {
        return getImageFromFilePath(data);
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("pic_uri", picUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        picUri = savedInstanceState.getParcelable("pic_uri");
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    /**
     *
     * submit to api
     *
     *
     */

  /*  private void submitData(){

        String post_title = tvPost_title.getText().toString();
        String post_status = tvPost_status.getText().toString();
        String default_category = tvDefault_category.getText().toString();
        String state = tvState.getText().toString();
        String street = tvStreet.getText().toString();
        String city = tvCity.getText().toString();
        String zip = tvZip.getText().toString();
        String country = tvCity.getText().toString();
        String rating = tvRating.getText().toString();
        String email = tvEmail.getText().toString();
        String website = tvWebsite.getText().toString();
        String twitter = tvTwitter.getText().toString();
        String facebook = tvFacebook.getText().toString();
        String video = tvVideo.getText().toString();
        String hours = tvHours.getText().toString();
        //String isOpen = tvIsOpen.getText().toString();
        String content = tvContent.getText().toString();
        String phone = tvPhone.getText().toString();
        String bldgno = tvBldgno.getText().toString();
        String latitude = tvLatitude.getText().toString();
        String longitude = tvLongitude.getText().toString();
        File image00 = getCaptureImageOutputUri();
        File image01 = getImageFilePath(data);
        File image02 = getImageFilePath();*/

      /* try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
            String submit_ip = s.toString();
            // System.out.println("My current IP address is " + s.next());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //Basic Auth
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            authToken = Credentials.basic(username, password);
        }

        //Create a new Interceptor.
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };

        //Add the interceptor to the client builder.
        clientBuilder.addInterceptor(headerAuthorizationInterceptor);

        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
       /* Call<List<BusinessListings>> call = service.postData(id, post_title, post_status,
                default_category, image00, bldgno, street, city, state, zip, country, latitude, longitude, rating,
                phone, website, email, twitter, facebook, hours, image01,
                image02, content);

        //calling the api
        call.enqueue(new Callback<List<BusinessListings>>() {
            @Override
            public void onResponse(Call<List<BusinessListings>> call, Response<List<BusinessListings>> response) {
                Log.e("add_listing", " response " + response.body());

  //              progressBar.setVisibility(View.GONE); //hide progressBar
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),
                            "Post Updated Title: "+response.body().get(0).getTitle()+
                                    " Body: "+response.body().get(0).getContent()+
                                    " PostId: "+response.body().get(0).getId(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<BusinessListings>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE); //hide progressBar
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }*/
}