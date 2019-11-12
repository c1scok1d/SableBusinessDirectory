package com.sable.businesslistingapi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextThemeWrapper;
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
import androidx.core.content.FileProvider;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    String baseURL = "https://www.thesablebusinessdirectory.com", id = "12345", username="android_app", password="mroK zH6o wOW7 X094 MTKy fwmY", authToken, Document_img1 = "";
    private static final int GALLERY_REQUEST_CODE = 2;
    private static final int CAMERA_REQUEST_CODE = 1;
    Uri imageUri00, imageUri01, imageUri02;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Picasso.Builder builder = new Picasso.Builder(this);

        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        mFeedback = findViewById(R.id.etFeedback);
        mSendFeedback = findViewById(R.id.btnSubmit);
        simpleRatingBar = findViewById(R.id.simpleRatingBar);
        tvPost_title = findViewById(R.id.tvTitle);
        tvBldgno = findViewById(R.id.tvBldgNo);
        tvState = findViewById(R.id.tvState);
        tvStreet = findViewById(R.id.tvStreet);
        tvCity = findViewById(R.id.tvStreet);
        tvZip = findViewById(R.id.tvZip);
        tvCountry = findViewById(R.id.tvCountry);
        tvHours = findViewById(R.id.tvHours);
        tvIsOpen = findViewById(R.id.tvIsOpen);
        tvContent = findViewById(R.id.content);
        tvPhone = findViewById(R.id.tvPhone);


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
           /* zip.setText(locationAdd.get(0).zip);
            latitude = (locationAdd.get(0).latitude);
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

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ReviewActivity.this);

            }
        });
    }

    /**
     * Create a chooser intent to select the source to get image from.<br />
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br />
     * All possible sources are added to the intent chooser.
     */
    private void selectImage(final Context context) {
        //final Permissions permissions = new Permissions(MainActivity.this);
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    isReadStoragePermissionGranted();
                    isWriteStoragePermissionGranted();
                    isCameraPermissionGranted();
                    captureFromCamera();

                } else if (options[item].equals("Choose from Gallery")) {

                    isReadStoragePermissionGranted();
                    isWriteStoragePermissionGranted();
                    pickFromGallery();

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Read  Permission", "Permission is granted");
                //return true;
            } else {

                Log.e("Read  Permission", "Permission is revoked! Requesting Permission");
                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                //return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Read Storage Permission", "Permission is granted by default");
            //return true;
        }
    }

    public void isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Write Permission", "Permission is granted");
                //return true;
            } else {

                Log.e("Write Permission", "Permission is revoked! Requesting Permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                //return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Write Permission", "Permission is granted by default");
            //return true;
        }
    }

    public void isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Camera Permission", "Permission is granted");
                //     return true;
            } else {

                Log.e("Camera Permission", "Permission is revoked!  Requesting Permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 3);
                //   return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Camera Permission", "Permission is granted by default");
            //return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case 1:
                Log.d("Request permission", "External storage read ");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Request Result: ", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    //isReadStoragePermissionGranted();
                } else {
                    // progress.dismiss();
                }
                break;

            case 2:
                Log.d("Request permission", "External storage write ");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Request Result: ", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    //isWriteStoragePermissionGranted();
                } else {
                    // progress.dismiss();
                }
                break;

            case 3:
                Log.d("Request permission", "Camera ");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Request Result: ", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    //isCameraPermissionGranted();
                } else {
                    // progress.dismiss();
                }
                break;
        }
    }


    //@Override
    public void onClick(View v) {
        if (Document_img1.equals("") || Document_img1.equals(null)) {
            ContextThemeWrapper ctw = new ContextThemeWrapper(ReviewActivity.this, R.style.Theme_AlertDialog);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
            alertDialogBuilder.setTitle("Id Prof Can't Empty ");
            alertDialogBuilder.setMessage("Id Prof Can't empty please select any one document");
            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            alertDialogBuilder.show();
            return;
        }
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void captureFromCamera() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.myapplication.fileprovider", photoFile);
                //pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = new ImageView(ReviewActivity.this);
        LinearLayout linearLayout = findViewById(R.id.imagesLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        imageView.setLayoutParams(params);
        linearLayout.addView(imageView);

        // Result code is RESULT_OK only if the user selects an Image


        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    Uri imageUri = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);

                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

                case CAMERA_REQUEST_CODE:
                    //Bundle extras = data.getExtras();
                    Bitmap image = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(image);
            }

    }

    /**
     * For images captured from the camera, we need to create a File first to tell the camera
     * where to store the image.
     *
     * @return the File created for the image to be store under.
     */
    String currentPhotoPath;

    private File createImageFile() throws IOException {

        isWriteStoragePermissionGranted();
        isReadStoragePermissionGranted();
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     *
     * submit to api
     * 
     * 
     */

    /*private void submitData(){

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
        /*String image00 = ivImage0.toString();
        String image01 = ivImage1.toString();
        String image02 = ivImage02.toString();*/

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
        Call<List<BusinessListings>> call = service.postData(id, post_title, post_status,
                default_category, imageUri00, bldgno, street, city, state, zip, country, latitude, longitude, rating,
                phone, website, email, twitter, facebook, hours, imageUri01,
                imageUri02, content);

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

    } */
}