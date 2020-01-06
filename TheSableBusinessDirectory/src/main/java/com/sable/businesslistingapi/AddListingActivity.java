package com.sable.businesslistingapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bashizip.bhlib.BusinessHours;
import com.bashizip.bhlib.BusinessHoursWeekPicker;
import com.bashizip.bhlib.BusinessHoursWeekView;
import com.bashizip.bhlib.ValdationException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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



/**
 *
 */
public class AddListingActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {


    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;


    String address, state, country, zipcode, city, street, bldgNo, username = "android_app",
            password = "mroK zH6o wOW7 X094 MTKy fwmY";

    private Double latitude = 0.00;
    private Double longitude = 0.00;
    /*
    objects of text view and button widgets.
     */
    TextView tvAddress, tvStreet, tvZip, tvState, tvCity, tvBldgNo, tvCountry;
    EditText etName, etDescription, etPhone, etEmail, etWebsite, etHours, etTwitter, etFacebook;
    Button btnNext;
    Spinner spnCategory;
    ArrayList<String> category = new ArrayList<>();
    ArrayList<ListingsAddModel> locationAdd = new ArrayList<>();
    List<BusinessHours> bhs = new ArrayList<>();
    ArrayList<String> userActivityArray = new ArrayList<>();
    private ArrayList<MediaFile> photos = new ArrayList<>();
    Map<String, RequestBody> parts = new HashMap<>();
    JSONObject bhsJSON;

    String name, description, catName, phone, email, website, twitter, facebook, link, status = "publish", Document_img1 = "";
    Integer catNum;
    private static final String PHOTOS_KEY = "easy_image_photos_list";
    private static final int CHOOSER_PERMISSIONS_REQUEST_CODE = 7459;
    private static final int CAMERA_REQUEST_CODE = 7500;
    private static final int CAMERA_VIDEO_REQUEST_CODE = 7501;
    private static final int GALLERY_REQUEST_CODE = 7502;
    private static final int DOCUMENTS_REQUEST_CODE = 7503;
    protected ImageView ivLogo;
    private EasyImage easyImage;
    BusinessHoursWeekPicker bh_picker;
    JSONArray businessHours = new JSONArray();



    public static final String BH_LIST = "bh_list";

    /**
     * @param savedInstanceState
     */
    @SuppressLint("MissingPermission")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listing);

        BusinessHoursWeekPicker bh_picker = findViewById(R.id.bh_picker);
        BusinessHoursWeekView businessHoursWeekView = findViewById(R.id.bh_view);
        Button btn_apply = findViewById(R.id.btn_apply);
        LinearLayout businessHoursLayout = findViewById(R.id.businessHoursLayout);
        LinearLayout viewBusinessHoursLayout = findViewById(R.id.viewBusinessHoursLayout);
        viewBusinessHoursLayout.setVisibility(View.GONE);

        btn_apply.setOnClickListener(view -> {

            try {

                bhs = bh_picker.getBusinessHoursList();

                for(int i=0; i< bhs.size();i++){

                    businessHours.put(bhs.get(i).getShortDayOfWeek() + " " +bhs.get(i).getFrom24()+ "-" +bhs.get(i).getTo24());
                }

                //System.out.println(json);
                System.out.println("Hours String: " +bhs.toString());
                System.out.println("Hours String For data: " +businessHours.toString());

            } catch (ValdationException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                return;
            }
            businessHoursWeekView.setModel(bhs);
            viewBusinessHoursLayout.setVisibility(View.VISIBLE);
            businessHoursLayout.setVisibility(View.GONE);
        });

      //  tvAddress = findViewById(R.id.tvAddress);
        tvZip = findViewById(R.id.tvZip);
        tvState = findViewById(R.id.tvState);
        tvCity = findViewById(R.id.tvCity);
        tvStreet = findViewById(R.id.tvStreet);
        tvBldgNo = findViewById(R.id.tvBldgNo);
        tvCountry = findViewById(R.id.tvCountry);
        btnNext = findViewById(R.id.btnNext);
        spnCategory = findViewById(R.id.spnCategory);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etWebsite = findViewById(R.id.etWebsite);
        etTwitter  = findViewById(R.id.etTwitter);
        etFacebook = findViewById(R.id.etFacebook);
        // category = new ArrayList<>();
        // locationAdd = new ArrayList<>();


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        getRetrofitCategories();

        /**
         * OnClick take user to add location page
         */

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(AddListingActivity.this, "Please Enter The Business Name...", Toast.LENGTH_LONG).show();
                } else if (spnCategory.getSelectedItem().equals("Category")) {
                    Toast.makeText(AddListingActivity.this, "Please select a Category...", Toast.LENGTH_LONG).show();
                } else if (etDescription.getText().toString().isEmpty()) {
                    Toast.makeText(AddListingActivity.this, "Please enter a description...", Toast.LENGTH_LONG).show();
                } else {

                    getRetrofitCategories();

                    name = etName.getText().toString();
                    description = etDescription.getText().toString();
                    catName = spnCategory.getSelectedItem().toString();
                    email = etEmail.getText().toString();
                    website = "http://www."+etWebsite.getText().toString();
                    twitter = "http://www.twitter.com/"+etTwitter.getText().toString();
                    facebook = "http://www.facebook.com/"+etFacebook.getText().toString();
                    phone = etPhone.getText().toString();
                    //String formattedPhone = phone;


                    locationAdd.add(new ListingsAddModel(ListingsAddModel.IMAGE_TYPE,
                            name,
                            link,
                            catName,
                            catNum,
                            description,
                            MainActivity.longitude,
                            MainActivity.latitude,
                            address,
                            state,
                            country,
                            zipcode,
                            city,
                            bldgNo,
                            street,
                            phone,
                            email,
                            website,
                            twitter,
                            facebook,
                            businessHours.toString()));

                    submitData();

                    Intent home = new Intent(AddListingActivity.this, MainActivity.class);
                    //Bundle locationAddBundle = new Bundle();
                    //locationAddBundle.putParcelableArrayList("locationAddBundle", locationAdd);
                    home.putExtra("userActivityArray", userActivityArray);
                    startActivity(home);
                }
            }
        });

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        enableMyLocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                400, LocationListener);

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spnCategory.getSelectedItem().toString().equals("Category")) {

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(new BasicAuthInterceptor(username, password))
                            .addInterceptor(logging)
                            .build();


                    if(retrofit==null){
                        retrofit = new Retrofit.Builder()
                                .baseUrl(baseURL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client)
                                .build();
                    }
                    RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
                    // pass JSON data to BusinessListings class for filtering
                    Call<List<ListingsCategories>> call = service.getCategory();

                    // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
                    call.enqueue(new Callback<List<ListingsCategories>>() {
                        @Override
                        public void onResponse(Call<List<ListingsCategories>> call, Response<List<ListingsCategories>> response) {

                            // loop through JSON response get parse and output to log
                            for (int i = 0; i < response.body().size(); i++) {

                                if (spnCategory.getSelectedItem().toString().equals(response.body().get(i).getName())) {
                                    catNum = (response.body().get(i).getId());
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ListingsCategories>> call, Throwable t) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        ivLogo = findViewById(R.id.ivLogo);
        //galleryButton = findViewById(R.id.gallery_button);

        if (savedInstanceState != null) {
            photos = savedInstanceState.getParcelableArrayList(PHOTOS_KEY);
        }

        /*imagesAdapter = new ImagesAdapter(this, photos);
        ivLogo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ivLogo.setHasFixedSize(true);
        ivLogo.setAdapter(imagesAdapter);*/

        easyImage = new EasyImage.Builder(this)
                .setChooserTitle("Pick media")
                .setCopyImagesToPublicGalleryFolder(false)
//                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setFolderName("EasyImage sample")
                .allowMultiple(true)
                .build();

        //checkGalleryAppAvailability();


        findViewById(R.id.ivLogo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Some devices such as Samsungs which have their own gallery app require write permission. Testing is advised! */
                String[] necessaryPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (arePermissionsGranted(necessaryPermissions)) {
                    easyImage.openGallery(AddListingActivity.this);
                } else {
                    requestPermissionsCompat(necessaryPermissions, GALLERY_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PHOTOS_KEY, photos);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CHOOSER_PERMISSIONS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openChooser(AddListingActivity.this);
        } else if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForImage(AddListingActivity.this);
        } else if (requestCode == CAMERA_VIDEO_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForVideo(AddListingActivity.this);
        } else if (requestCode == GALLERY_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openGallery(AddListingActivity.this);
        } else if (requestCode == DOCUMENTS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openDocuments(AddListingActivity.this);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                for (MediaFile imageFile : imageFiles) {
                    Log.d("EasyImage", "Image file returned: " + imageFile.getFile().toString());
                }
                uploadFiles(imageFiles);

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

    //ArrayList<String> filesToUploadfoo = new ArrayList<>();
    File[] filesToUpload;
    ArrayList<String> filesToUploadfoo = new ArrayList<>();


    public void uploadFiles(@NonNull MediaFile[] returnedPhotos){
        photos.addAll(Arrays.asList(returnedPhotos));

        filesToUpload = new File[photos.size()];


        for(int i=0; i< photos.size(); i++){
            filesToUpload[i] = new File(photos.get(i).getFile().toString());
           /* String path = photos.get(i).getFile().toString();
            // it contains your image path...I'm using a temp string...
            String filename = path.substring(path.lastIndexOf("/")+1);
            filesToUploadfoo.add(photos.get(i).getFile().toString());*/
        }
        showProgress("Uploading media ...");
        FileUploader fileUploader = new FileUploader();
        fileUploader.uploadFiles("wp-json/wp/v2/media", "file", filesToUpload, new FileUploader.FileUploaderCallback() {
            @Override
            public void onError() {
                hideProgress();
            }
            String foo;

            @Override
            public void onFinish(String[] responses) {
                hideProgress();
                for(int i=0; i< responses.length; i++){
                    //String str = responses[i];
                    try {
                        final JSONObject obj = new JSONObject(responses[i]);
                        final JSONObject geodata = obj.getJSONObject("guid");
                        //String person = geodata.getJSONObject("rendered");
                        foo = geodata.getString("rendered");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    filesToUploadfoo.add(foo);
                    Log.e("RESPONSE "+i, responses[i]);
                }
            }

            @Override
            public void onProgressUpdate(int currentpercent, int totalpercent, int filenumber) {
                updateProgress(totalpercent,"Uploading file "+filenumber,"");
                Log.e("Progress Status", currentpercent+" "+totalpercent+" "+filenumber);
            }
        });
    }
    private ProgressBar pDialog;


    public void updateProgress(int val, String title, String msg){
        
    }


    public void showProgress(String str){
        try{
            
        }catch (Exception e){

        }
    }

    public void hideProgress() {
        try {
            
        } catch (Exception e) {

        }
    }



    private void onPhotosReturned(@NonNull MediaFile[] returnedPhotos) {
        photos.addAll(Arrays.asList(returnedPhotos));
        ivLogo.setImageBitmap(BitmapFactory.decodeFile(photos.get(0).getFile().toString()));
    }

    private boolean arePermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;

        }
        return true;
    }

    private void requestPermissionsCompat(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(AddListingActivity.this, permissions, requestCode);
    }

    android.location.LocationListener LocationListener = new LocationListener() {

        /**
         * @param location
         */
        @Override
        public void onLocationChanged(Location location) {

            // zoom to current location on map
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            setAddress(latitude, longitude);  // method to reverse geocode to physical address
        }

        /**
         * @param provider
         * @param status
         * @param extras
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        /**
         * @param provider
         */
        @Override
        public void onProviderEnabled(String provider) {}

        /**
         * @param provider
         */
        @Override
        public void onProviderDisabled(String provider) {}

    };



    /**
     * @param map
     */
    public void onMapReady(GoogleMap map) {
        mMap = map;

        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        enableMyLocation(); //permission check
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        setAddress(latitude, longitude);
    }

    /**
     *
     * Enables the My Location layer if the fine location permission has been granted.
     */
    public void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    /**
     * @return
     */
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Getting current location...", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    /**
     * @param location
     */
    @Override
    public void onMyLocationClick(Location location) {
        //get location address
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        // zoom to current location on map
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        setAddress(latitude, longitude);
    }

    /**
     * Returns users GPS lat/lng
     * @param location
     */

    /**
     *
     */
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     *
     */
    /*
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }


    /**
     * @param latitude
     * @param longitude
     */
    private void setAddress(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses.size() > 0) {
           // Log.d("max", " " + addresses.get(0).getMaxAddressLineIndex());

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()/*String city = addresses.get(0).getLocality();
            bldgNo = addresses.get(0).getSubThoroughfare();
            street = addresses.get(0).getThoroughfare();
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            zipcode = addresses.get(0).getPostalCode();
            country = addresses.get(0).getCountryName();
           /* String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String lat = Double.toString(latitude);
            String lng = Double.toString(longitude);*/


//        tvAddress.setText(address);
            tvZip.setText(zipcode);
            tvState.setText(state);
            tvCity.setText(city);
            tvStreet.setText(street);
            tvBldgNo.setText(bldgNo);
            tvCountry.setText(country);
        }

    }

    public void onBackPressed() {
        Intent onBack = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(onBack);
    }

    /**
     * Query API for listings data
     * set URL and make call to API
     *
     */
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
    private String baseURL = "https://www.thesablebusinessdirectory.com";
    //Integer addCategory;

    private static Retrofit retrofit = null;
    public void getRetrofitCategories() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .addInterceptor(logging)
                .build();

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        // pass JSON data to BusinessListings class for filtering
        Call<List<ListingsCategories>> call = service.getCategory();

        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<List<ListingsCategories>>() {
            @Override
            public void onResponse(Call<List<ListingsCategories>> call, Response<List<ListingsCategories>> response) {
              category.add("Category"); //add heading to category spinner
                for (int i = 0; i < response.body().size(); i++) {
                    category.add(response.body().get(i).getName());
                    spnCategory.setAdapter(new ArrayAdapter<>(AddListingActivity.this, android.R.layout.simple_spinner_dropdown_item, category));
                }
               
            }
            @Override
            public void onFailure(Call<List<ListingsCategories>> call, Throwable t) {
            }
        });
    }
String type = "gd_business";
    //private static Retrofit retrofit = null;
    private void submitData(){

        String streetFoo = bldgNo+" "+street;
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
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<List<BusinessListings>> call = service.postData(/*id,*/
                name,
                status,
                catNum,
                description,
                /*address,*/
                bldgNo,
                /*street,*/
                streetFoo,
                city,
                state,
                country,
                zipcode,
                MainActivity.latitude,
                MainActivity.longitude,
                phone,
                /* hours,*/
                email,
                website,
                twitter,
                facebook,
                type,
                Integer.valueOf(MainActivity.userId),
                filesToUploadfoo,
                businessHours.toString());

        //calling the api
        call.enqueue(new Callback<List<BusinessListings>>() {
            @Override
            public void onResponse(Call<List<BusinessListings>> call, Response<List<BusinessListings>> response) {
                Log.e("AddListingActivity", " response " + response.body());
                if(response.isSuccessful()){
                    userActivityArray.add(response.body().get(0).getDateGmt());
                    userActivityArray.add(String.valueOf(response.body().get(0).getId()));
                    userActivityArray.add(response.body().get(0).getType());
                }
            }

            @Override
            public void onFailure(Call<List<BusinessListings>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(AddListingActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();

    }
}