package com.macinternetservices.sablebusinessdirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.macinternetservices.sablebusinessdirectory.MainActivity.firstName;


public class ReviewActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String PHOTOS_KEY = "easy_image_photos_list";
    private static final int CHOOSER_PERMISSIONS_REQUEST_CODE = 7459;
    private static final int CAMERA_REQUEST_CODE = 7500;
    private static final int CAMERA_VIDEO_REQUEST_CODE = 7501;
    private static final int GALLERY_REQUEST_CODE = 7502;
    private static final int DOCUMENTS_REQUEST_CODE = 7503;
    protected RecyclerView recyclerView;
    protected View galleryButton;
    RatingBar ratingBar;
    TextView mRatingScale;
    EditText etFeedBack;
    Button mSendFeedback;
    TextView tvFeatured, tvStatus, tvState, tvStreet, tvCity, tvZip, tvCountry, tvUserName,
            tvId, tvEmail, tvWebsite, tvTwitter, tvFacebook, tvHours, tvIsOpen, tvLink,
            tvContent, tvPhone, tvBldgno, tvLatitude, tvLongitude, tvRatingCount, tvCategory,
            tvName, tvFirstRate, tvDistance;
    ImageView ivUserImage, ivFeaturedImage;
    RatingBar simpleRatingBar;
    String title, content, city, country, link, baseURL = "https://www.thesablebusinessdirectory.com", username = "android_app",
            password = "mroK zH6o wOW7 X094 MTKy fwmY", status = "approved";
    Double latitude, longitude;
    Integer category, id, rating;
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


    private ProgressBar pDialog;

    private ImagesAdapter imagesAdapter;
    private ArrayList<MediaFile> photos = new ArrayList<>();
    ArrayList<ListingsModel> locationReview = new ArrayList<>();
    ArrayList<ListingsModel> locationMatch = new ArrayList<>();
    ArrayList<ListingsAddModel> locationAdd = new ArrayList<>();
    ArrayList<String> userActivityArray = new ArrayList<>();


    private EasyImage easyImage;
    private TextSwitcher textSwitcher;
    private int count =0;
    Thread updateMsg;
    private static final int FRAME_TIME_MS = 12000;
    public static String reviewDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Picasso.Builder builder = new Picasso.Builder(this);
        pDialog = new ProgressBar(this);

        ratingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        etFeedBack = findViewById(R.id.etFeedBack);
        mSendFeedback = findViewById(R.id.btnSubmit);
        simpleRatingBar = findViewById(R.id.simpleRatingBar);
        tvId = findViewById(R.id.tvId);
        tvBldgno = findViewById(R.id.tvBldgNo);
        tvState = findViewById(R.id.tvState);
        tvStreet = findViewById(R.id.tvStreet);
        tvCity = findViewById(R.id.tvCity);
        tvZip = findViewById(R.id.tvZip);
        tvCountry = findViewById(R.id.tvCountry);
        tvHours = findViewById(R.id.tvHours);
        tvIsOpen = findViewById(R.id.tvIsOpen);
        tvContent = findViewById(R.id.tvContent);
        tvPhone = findViewById(R.id.tvPhone);
        tvFirstRate = findViewById(R.id.tvFirstRate);
        tvCategory = findViewById(R.id.tvCategory);
        tvEmail = findViewById(R.id.tvEmail);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvTwitter = findViewById(R.id.tvTwitter);
        tvFacebook = findViewById(R.id.tvFacebook);
        tvName = findViewById(R.id.tvName);
        ivFeaturedImage = findViewById(R.id.ivFeaturedImage);
        tvRatingCount = findViewById(R.id.tvRatingCount);
        tvIsOpen = findViewById(R.id.tvIsOpen);
        tvDistance = findViewById(R.id.tvDistance);
        tvFeatured = findViewById(R.id.tvFeatured);
        tvLink = findViewById(R.id.tvLink);
        tvStatus = findViewById(R.id.tvStatus);
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        pDialog = findViewById(R.id.progressBar1);
        tvProgressStatus = findViewById(R.id.tvProgressStatus);
        tvUserName = findViewById(R.id.tvUserName);
        ivUserImage = findViewById(R.id.ivUserImage);
        textSwitcher =  findViewById(R.id.textSwitcher1);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);

        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);


        textSwitcher.setCurrentText("Thank you for using The Sable Business Directory! " +
                "This is where users rate and review listings in our directory.");



        pDialog.setVisibility(View.GONE);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
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
                rating = ((int) ratingBar.getRating());
            }
        });


        /**
         *
         * gets Extras if stored lat/lng equals current lat/lng (onLocationMatch)
         *
         */
        locationMatch = this.getIntent().getExtras().getParcelableArrayList("locationMatch");
        locationAdd = this.getIntent().getExtras().getParcelableArrayList("locationAdd");
        locationReview = this.getIntent().getExtras().getParcelableArrayList("locationReview");
        //locationReviewFoo = this.getIntent().getExtras().getParcelable("locationReviewFoo");

        if (locationMatch != null) {

            tvName.setText(locationMatch.get(0).title);
            tvCategory.setText(locationMatch.get(0).category);
            builder.build().load(locationMatch.get(0).featured_image).into(ivFeaturedImage);
            tvBldgno.setText(locationMatch.get(0).bldgno);
            tvStreet.setText(locationMatch.get(0).street);
            tvCity.setText(locationMatch.get(0).city);
            tvState.setText(locationMatch.get(0).state);
            tvCountry.setText(locationMatch.get(0).country);
            tvZip.setText(locationMatch.get(0).zipcode);
            simpleRatingBar.setRating(locationMatch.get(0).rating);
            tvRatingCount.setText(String.valueOf(locationMatch.get(0).ratingCount));
            tvPhone.setText(locationMatch.get(0).phone);
            tvEmail.setText(locationMatch.get(0).email);
            tvWebsite.setText(locationMatch.get(0).website);
            tvTwitter.setText(locationMatch.get(0).twitter);
            tvFacebook.setText(locationMatch.get(0).facebook);
            tvHours.setText(locationMatch.get(0).hours);
            tvIsOpen.setText(locationMatch.get(0).isOpen);
            /**
             *  THIS IS WRONG!!!! MUST FIX LIKE YESTERDAY!!!
             */
            tvContent.setText(locationMatch.get(0).logo);
            /**
             *  DID YOU FIX THAT WRONG SHIT YET???
             */
            tvLink.setText(locationMatch.get(0).link);
            tvLatitude.setText(String.valueOf(locationMatch.get(0).latitude));
            tvLongitude.setText(String.valueOf(locationMatch.get(0).longitude));
            tvId.setText(String.valueOf(locationMatch.get(0).id));
            tvStatus.setText(locationMatch.get(0).status);
            tvUserName.setText(firstName);
            builder.build().load(MainActivity.userImage).into(ivUserImage);


            Location locationA = new Location("point A");
            locationA.setLatitude(locationMatch.get(0).latitude); //listing lat
            locationA.setLongitude(locationMatch.get(0).longitude); //listing lng


            Location locationB = new Location("point B");
            locationB.setLatitude(MainActivity.latitude); //device lat
            locationB.setLongitude(MainActivity.longitude); //device lng

            double distance = (locationA.distanceTo(locationB) * 0.000621371192); //convert meters to miles
            tvDistance.setText(String.format(Locale.US, "%10.2f", distance));
            if(locationMatch.get(0).isOpen == "Closed now"){
                tvIsOpen.setTextColor(Color.rgb(255, 0, 0 )); //red
            }
            if(locationMatch.get(0).featured.equals(true)){
                String featured = "FEATURED";
                tvFeatured.setText(featured);
                tvFeatured.setTextColor(Color.rgb(255, 128, 0 )); //red
            }

            if (locationMatch.get(0).ratingCount == 0){
                String FirstRate = "Be the first to rate";
                tvFirstRate.setText(FirstRate);
                //tvFirstRate.setTextColor(Color.rgb(0, 255, 0)); //orange
            }

        } else if (locationAdd!= null) {
            tvName.setText(locationAdd.get(0).name);
            tvCategory.setText(locationAdd.get(0).category);
            tvBldgno.setText(locationAdd.get(0).bldgNo);
            tvStreet.setText(locationAdd.get(0).street);
            tvCity.setText(locationAdd.get(0).city);
            tvState.setText(locationAdd.get(0).state);
            tvCountry.setText(locationAdd.get(0).country);
            tvZip.setText(locationAdd.get(0).zipcode);
            tvPhone.setText(locationAdd.get(0).phone);
            tvEmail.setText(locationAdd.get(0).email);
            tvWebsite.setText(locationAdd.get(0).website);
            tvTwitter.setText(locationAdd.get(0).twitter);
            tvFacebook.setText(locationAdd.get(0).facebook);
            tvContent.setText(locationAdd.get(0).description);
            category = locationAdd.get(0).addCategory;
            link = locationAdd.get(0).link;
            latitude = locationAdd.get(0).latitude;
            longitude = locationAdd.get(0).longitude;
            tvUserName.setText(firstName);
            builder.build().load(MainActivity.userImage).into(ivUserImage);
        } else {

            tvName.setText(locationReview.get(0).title);
            tvCategory.setText(locationReview.get(0).category);
            builder.build().load(locationReview.get(0).featured_image).into(ivFeaturedImage);
            tvBldgno.setText(locationReview.get(0).bldgno);
            tvStreet.setText(locationReview.get(0).street);
            tvCity.setText(locationReview.get(0).city);
            tvState.setText(locationReview.get(0).state);
            tvCountry.setText(locationReview.get(0).country);
            tvZip.setText(locationReview.get(0).zipcode);
            simpleRatingBar.setRating(locationReview.get(0).rating);
            tvRatingCount.setText(String.valueOf(locationReview.get(0).ratingCount));
            tvPhone.setText(locationReview.get(0).phone);
            tvEmail.setText(locationReview.get(0).email);
            tvWebsite.setText(locationReview.get(0).website);
            tvTwitter.setText(locationReview.get(0).twitter);
            tvFacebook.setText(locationReview.get(0).facebook);
            tvHours.setText(locationReview.get(0).hours);
            tvIsOpen.setText(locationReview.get(0).isOpen);
            /**
             *  THIS IS WRONG!!!! MUST FIX LIKE YESTERDAY!!!
             */
            tvContent.setText(locationReview.get(0).logo);
            /**
             *  DID YOU FIX THAT WRONG SHIT YET???
             */
            tvLink.setText(locationReview.get(0).link);
            tvLatitude.setText(String.valueOf(locationReview.get(0).latitude));
            tvLongitude.setText(String.valueOf(locationReview.get(0).longitude));
            tvId.setText(String.valueOf(locationReview.get(0).id));
            tvStatus.setText(locationReview.get(0).status);
            tvUserName.setText(firstName);
//            tvUserEmail.setText(MainActivity.userEmail);
            builder.build().load(MainActivity.userImage).into(ivUserImage);
//            tvUserId.setText(MainActivity.userId);

            Location locationA = new Location("point A");
            locationA.setLatitude(locationReview.get(0).latitude); //listing lat
            locationA.setLongitude(locationReview.get(0).longitude); //listing lng


            Location locationB = new Location("point B");
            locationB.setLatitude(MainActivity.latitude); //device lat
            locationB.setLongitude(MainActivity.longitude); //device lng

            double distance = (locationA.distanceTo(locationB) * 0.000621371192); //convert meters to miles
            tvDistance.setText(String.format(Locale.US, "%10.2f", distance));
            if(locationReview.get(0).isOpen.equals("Closed now")){
                tvIsOpen.setTextColor(Color.rgb(255, 0, 0 )); //red
            }
           /* if(locationReview.get(0).featured.equals("null")){
                locationReview.get(0).featured = false;
            }
            if(locationReview.get(0).featured.equals(true)){
                String featured = "FEATURED";
                tvFeatured.setText(featured);
                tvFeatured.setTextColor(Color.rgb(255, 128, 0 )); //orange
            } */

            if (locationReview.get(0).ratingCount == 0){
                String FirstRate = "Be the first to rate";
                tvFirstRate.setText(FirstRate);
                //tvFirstRate.setTextColor(Color.rgb(22, 53, 64)); //green
            }
            if (locationReview.get(0).hours.equals("null") || locationReview.get(0).isOpen.equals("null") || locationReview.get(0).hours == null || locationReview.get(0).isOpen == null) {
                tvIsOpen.setVisibility(View.GONE);
                tvHours.setVisibility(View.GONE);
            } else if (locationReview.get(0).hours.equals("Closed") || locationReview.get(0).isOpen.equals("Closed now")) {
                String closed = "Closed";
                tvHours.setText(closed);
                tvHours.setTextColor(Color.rgb(255, 0, 0)); //red
                tvIsOpen.setText(closed);
                tvIsOpen.setTextColor(Color.rgb(255, 0, 0)); //red
            } else {
                String open = "Open";
                tvIsOpen.setText(open);
                tvIsOpen.setTextColor(Color.rgb(51, 165, 50)); //green
            }
        }

        findViewById(R.id.tvName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvName.setCursorVisible(true);
                tvName.setFocusableInTouchMode(true);
                tvName.setInputType(InputType.TYPE_CLASS_TEXT);
                tvName.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPhone.setCursorVisible(true);
                tvPhone.setFocusableInTouchMode(true);
                tvPhone.setInputType(InputType.TYPE_CLASS_TEXT);
                tvPhone.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvWebsite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvWebsite.setCursorVisible(true);
                tvWebsite.setFocusableInTouchMode(true);
                tvWebsite.setInputType(InputType.TYPE_CLASS_TEXT);
                tvWebsite.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvEmail.setCursorVisible(true);
                tvEmail.setFocusableInTouchMode(true);
                tvEmail.setInputType(InputType.TYPE_CLASS_TEXT);
                tvEmail.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvTwitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTwitter.setCursorVisible(true);
                tvTwitter.setFocusableInTouchMode(true);
                tvTwitter.setInputType(InputType.TYPE_CLASS_TEXT);
                tvTwitter.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFacebook.setCursorVisible(true);
                tvFacebook.setFocusableInTouchMode(true);
                tvFacebook.setInputType(InputType.TYPE_CLASS_TEXT);
                tvFacebook.requestFocus(); //to trigger the soft input
            }
        });

        findViewById(R.id.tvContent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvContent.setCursorVisible(true);
                tvContent.setFocusableInTouchMode(true);
                tvContent.setInputType(InputType.TYPE_CLASS_TEXT);
                tvContent.requestFocus(); //to trigger the soft input
            }
        });


        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFeedBack.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {

                    submitData();
                    Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
                    mainActivity.putExtra("userActivityArray", userActivityArray);
                    startActivity(mainActivity);
                }
            }
        });

        recyclerView = findViewById(R.id.imageAddRecycler);

        if (savedInstanceState != null) {
            photos = savedInstanceState.getParcelableArrayList(PHOTOS_KEY);
        }

        imagesAdapter = new ImagesAdapter(this, photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imagesAdapter);

        easyImage = new EasyImage.Builder(this)
                .setChooserTitle("Pick media")
                .setCopyImagesToPublicGalleryFolder(false)
//                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setFolderName("sableTemp")
                .allowMultiple(true)
                .build();

        checkGalleryAppAvailability();


        findViewById(R.id.gallery_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Some devices such as Samsungs which have their own gallery app require write permission. Testing is advised! */
                String[] necessaryPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (arePermissionsGranted(necessaryPermissions)) {
                    easyImage.openGallery(ReviewActivity.this);
                } else {
                    requestPermissionsCompat(necessaryPermissions, GALLERY_REQUEST_CODE);
                }
            }
        });



        updateMsg = new Thread (){
            @Override
            public void run() {
                try {
                    while (!updateMsg.isInterrupted()) {
                        updateMsg.sleep(FRAME_TIME_MS);
                        runOnUiThread(() -> {
                            Random randomGenerator = new Random();
                            int randomInt = randomGenerator.nextInt(3);
                            switch (randomInt) {

                                case 1:
                                    textSwitcher.setText("Online reviews are an important reference point for customers. " +
                                            "89% of consumers trust online reviews as much as personal recommendations.");
                                    break;

                                case 2:
                                    textSwitcher.setText("Nearly 9 out of 10 consumers check for reviews before making purchasing decisions.");
                                    break;

                                default:
                                    textSwitcher.setText("To leave a review for "+tvName.getText().toString()+", fill in the information " +
                                            "below to provide details and photos of your experience.");
                                    break;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        //isRunning = true;
        updateMsg.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PHOTOS_KEY, photos);
    }

    private void checkGalleryAppAvailability() {
        if (!easyImage.canDeviceHandleGallery()) {
            //Device has no app that handles gallery intent
            galleryButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CHOOSER_PERMISSIONS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openChooser(ReviewActivity.this);
        } else if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForImage(ReviewActivity.this);
        } else if (requestCode == CAMERA_VIDEO_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForVideo(ReviewActivity.this);
        } else if (requestCode == GALLERY_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openGallery(ReviewActivity.this);
        } else if (requestCode == DOCUMENTS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openDocuments(ReviewActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
                    @Override
                    public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                        for (MediaFile imageFile : imageFiles) {
                            ////Log.e("EasyImage", "Image file returned: " + imageFile.getFile().toString());
                        }
                        onPhotosReturned(imageFiles);
                    }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                //Some error handling
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                //Not necessary to remove any files manually anymore
            }
        });
    }

    private void onPhotosReturned(@NonNull MediaFile[] returnedPhotos) {
      // photos.addAll(Arrays.asList(returnedPhotos));
        imagesAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(photos.size() - 1);
        uploadFiles(returnedPhotos);


    }

    File[] filesToUpload;

    public void uploadFiles(@NonNull MediaFile[] returnedPhotos){
       photos.addAll(Arrays.asList(returnedPhotos));

        filesToUpload = new File[photos.size()];

        for(int i=0; i< photos.size(); i++){
            filesToUpload[i] = new File(photos.get(i).getFile().toString());
        }
        pDialog.setVisibility(View.VISIBLE);
        FileUploader fileUploader = new FileUploader();
        fileUploader.uploadFiles("wp-json/wp/v2/media", "file", filesToUpload, new FileUploader.FileUploaderCallback() {
            @Override
            public void onError() {

                pDialog.setVisibility(View.GONE);
            }
            String foo;

            @Override
            public void onFinish(String[] responses) {
                pDialog.setVisibility(View.GONE);
                tvProgressStatus.setText("Media upload complete!");
                for(int i=0; i< responses.length; i++){
                    try {
                        final JSONObject obj = new JSONObject(responses[i]);
                        final JSONObject geodata = obj.getJSONObject("guid");
                        foo = geodata.getString("rendered");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    filesToUploadfoo.add(foo);
                }
            }

            @Override
            public void onProgressUpdate(int currentpercent, int totalpercent, int filenumber) {
                updateProgress(totalpercent);
            }
        });
    }

    public void updateProgress(int val){
        pDialog.setProgress(val);
        tvProgressStatus.setText("Uploading media ..."+ val+"%");
    }

    private TextView tvProgressStatus;


    private boolean arePermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;

        }
        return true;
    }

    private void requestPermissionsCompat(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(ReviewActivity.this, permissions, requestCode);
    }

    public void onBackPressed() {
        Intent onBack = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(onBack);
    }

    StringBuilder sb = new StringBuilder();
    String main;
   

    ArrayList<String> filesToUploadfoo = new ArrayList<>();
    private void submitData() {

        //int nestingDepth = 0;

        for (int i = 0; i < filesToUploadfoo.size(); i++) {
            sb.append(filesToUploadfoo.get(i)).append("::");
        }
        main = sb.toString();



        //Add the interceptor to the client builder.
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .addInterceptor(logging)
                .build();

        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<ListReviewPOJO> call = service.postReview(
                Integer.valueOf(tvId.getText().toString()),
               rating,
                etFeedBack.getText().toString(),
                main,
                Integer.valueOf(MainActivity.userId),
                MainActivity.userEmail);

        call.enqueue(new Callback<ListReviewPOJO>() {
            @Override
            public void onResponse(Call<ListReviewPOJO> call, Response<ListReviewPOJO> response) {
                ////Log.e("Response Successful", " response: " + response.body());
                if (response.isSuccessful()) {
                    userActivityArray.add(response.body().getDateGmt()); // date of review
                    userActivityArray.add(String.valueOf(response.body().getPost())); // listing id for review
                    userActivityArray.add(response.body().getType());  //post type 'comment'
                        }
                }
            @Override
            public void onFailure(Call<ListReviewPOJO> call, Throwable t) {
//                progressBar.setVisibility(View.GONE); //hide progressBar
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        if(MainActivity.isLoggedIn) {
            Toast.makeText(ReviewActivity.this, "Thank you for sharing your feedback " + firstName, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ReviewActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_LONG).show();
        }
    }

    public class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }

    }
}