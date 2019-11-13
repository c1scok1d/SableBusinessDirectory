package com.sable.businesslistingapi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class AddListingActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    /*private static final int MY_CAMERA_PERMISSION_CODE = 1;
    private static final int CAMERA_REQUEST = 1;*/


    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;

    // private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    // private long FASTEST_INTERVAL = 2000; /* 2 sec */
    // private LocationManager locationManager;


    String address, state, country, zipcode, city, street, img1, img2, img3, bldgNo;

    private Double latitude = 0.00;
    private Double longitude = 0.00;
    /*
    objects of text view and button widgets.
     */
    TextView tvAddress, tvStreet, tvZip, tvState, tvCity, tvBldgNo;
    EditText etName, etDescription, etPhone;
    Button btnNext;
    //ImageButton btnPic;
    Spinner spnCategory;
    ArrayList<String> category = new ArrayList<>();
    ArrayList<ListingsAddModel> locationAdd = new ArrayList<>();


    //ImageView uploadImage1, uploadImage2, uploadImage3;


    /**
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listing);


        tvAddress = findViewById(R.id.tvAddress);
        tvZip = findViewById(R.id.tvZip);
        tvState = findViewById(R.id.tvState);
        tvCity = findViewById(R.id.tvStreet);
        tvStreet = findViewById(R.id.tvStreet);
        tvBldgNo = findViewById(R.id.tvBldgNo);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPhone = findViewById(R.id.etPhone);
        btnNext = findViewById(R.id.btnNext);
        spnCategory = findViewById(R.id.spnCategory);
        //btnPic = findViewById(R.id.btnPic);
        // uploadImage1 = findViewById(R.id.uploadImage1);
        // uploadImage2 = findViewById(R.id.uploadImage2);
        // uploadImage3 = findViewById(R.id.uploadImage3);
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

                if (etName.getText().toString().isEmpty() || spnCategory == null && spnCategory.getSelectedItem() == null || etDescription.getText().toString().isEmpty()) {
                    Toast.makeText(AddListingActivity.this, "Please fill all sections...", Toast.LENGTH_LONG).show();
                } else {
                    final String name = etName.getText().toString();
                    final String description = etDescription.getText().toString();
                    final String category = spnCategory.getSelectedItem().toString();
                    final String phone = etPhone.getText().toString();

                    locationAdd.add(new ListingsAddModel(ListingsAddModel.IMAGE_TYPE, name, category, description,
                            longitude, latitude, address, state, country, zipcode, city, bldgNo, street, img1, img2, img3, phone));

                    Intent LocationAdd = new Intent(AddListingActivity.this, ReviewActivity.class);
                    Bundle locationAddBundle = new Bundle();
                    locationAddBundle.putParcelableArrayList("locationAddBundle", locationAdd);
                    LocationAdd.putExtra("locationAdd", locationAdd);
                    startActivity(LocationAdd);
                }
            }
        });
    }



    /**
     * @param map
     */
    public void onMapReady(GoogleMap map) {
        mMap = map;

        enableMyLocation();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            enableMyLocation();
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
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
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    /**
     *
     * Display user current location on map
     * @return
     */

    public boolean onMyLocationButtonClick() {
        setAddress(latitude, longitude);
        Toast.makeText(this, "Retrieving Current Location", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
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
            Log.d("max", " " + addresses.get(0).getMaxAddressLineIndex());

            String maddress = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()/*String city = addresses.get(0).getLocality();
            String mbldgNo = addresses.get(0).getSubThoroughfare();
            String mstreet = addresses.get(0).getThoroughfare();
            String mcity = addresses.get(0).getLocality();
            String mstate = addresses.get(0).getAdminArea();
            String mzipcode = addresses.get(0).getPostalCode();
            String mcountry = addresses.get(0).getCountryName();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String lat = Double.toString(latitude);
            String lng = Double.toString(longitude);


//        tvAddress.setText(address);
            tvZip.setText(mzipcode);
            tvState.setText(mstate);
            tvCity.setText(mcity);
            tvStreet.setText(mstreet);
            tvBldgNo.setText(mbldgNo);


            addresses.get(0).getAdminArea();
            address = maddress;
            bldgNo = mbldgNo;
            street = mstreet;
            state = mstate;
            city = mcity;
            zipcode = mzipcode;
            country = mcountry;
//            tvLng.setText(lng);
        }

    }

    /**
     * Query API for listings data
     * set URL and make call to API
     *
     */
    private String baseURL = "https://www.thesablebusinessdirectory.com";
    public void getRetrofitCategories() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        // pass JSON data to BusinessListings class for filtering
        Call<List<ListingsCategories>> call = service.getCategory();

        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<List<ListingsCategories>>() {
            @Override
            public void onResponse(Call<List<ListingsCategories>> call, Response<List<ListingsCategories>> response) {
                Log.e("main_activity", " response " + response.body());
                // mListPost = response.body();
                //progressBar.setVisibility(View.GONE); //hide progressBar
              category.add("Category"); //add heading to category spinner
                // loop through JSON response get parse and output to log
                for (int i = 0; i < response.body().size(); i++) {
                    //ifStatement to skip json object from array if value is empty/null
                    //parse response based on ListingsModel class and add to list array ( get category name, description and image)
                    // add category name from array to spinner
                    category.add(response.body().get(i).getName());
                    // display category array list in spinner
                    spnCategory.setAdapter(new ArrayAdapter<>(AddListingActivity.this, android.R.layout.simple_spinner_dropdown_item, category));
                    Log.e("main ", " Category: " + response.body().get(i).getName());
                }
               // adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<ListingsCategories>> call, Throwable t) {
            }
        });
    }
}