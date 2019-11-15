package com.sable.businesslistingapi;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {


    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    Double latitude, longitude;

    TextView tvAddress, tvStreet, tvZip, tvState, tvCity, tvBldgNo;
    RecyclerView verticalRecyclerView, horizontalRecyclervView, verticalRecyclerView2;
    private ProgressBar progressBar;
    LinearLayoutManager mLayoutManager, hLayoutManager, mLayoutManager2;
    VerticalAdapter verticalAdapter, verticalAdapter2;
    HorizontalAdapter horizontalAdapter;
    public static List<BusinessListings> mListPost;
    public static List<WooProducts> hListPost;
    String baseURL = "https://www.thesablebusinessdirectory.com", radius, address, state, country, zipcode, city, street, bldgno, submit_ip;

    ArrayList<ListingsModel> verticalList;
    ArrayList<ListingsModel> locationMatch = new ArrayList<>();
    ArrayList<String> category;
    ArrayList<WooModel> horizontalList;


    Button btnAdd;
    Spinner spnRadius, spnCategory;
    SearchView searchView;

    private GoogleMap mMap;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


        /* Display current location address */
        tvAddress = findViewById(R.id.tvAddress);

        /* Set Horizontal LinearLayout to RecyclerView */
        horizontalRecyclervView = findViewById(R.id.horizontalRecyclerView);
        horizontalRecyclervView.setHasFixedSize(true);
        hLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclervView.setLayoutManager(hLayoutManager);
        horizontalList = new ArrayList<>();

        /* Set HorizontalAdapter to RecyclerView */
        horizontalAdapter = new HorizontalAdapter(horizontalList, MainActivity.this);
        horizontalRecyclervView.setAdapter(horizontalAdapter);
        /*
            BEGIN vertical Recycler View
         */
        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);
       // verticalRecyclerView2 = findViewById(R.id.verticalRecyclerView2);

        progressBar = findViewById(R.id.progressbar);

        /* Set Vertical LinearLayout to RecyclerView */
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLayoutManager2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        verticalRecyclerView.setLayoutManager(mLayoutManager);
        //verticalRecyclerView2.setLayoutManager(mLayoutManager2);

        verticalList = new ArrayList<>();
        locationMatch = new ArrayList<>();


        verticalAdapter = new VerticalAdapter(verticalList, MainActivity.this);
        //verticalAdapter2 = new VerticalAdapter(verticalList, MainActivity.this);

        verticalRecyclerView.setAdapter(verticalAdapter);
        //verticalRecyclerView2.setAdapter(verticalAdapter2);


        btnAdd = findViewById(R.id.btnAdd);
        spnCategory = findViewById(R.id.spnCategory);
        spnRadius = findViewById(R.id.spnRadius);

        category = new ArrayList<>();

        final TextView texty = findViewById(R.id.texty);

        final Spinner spnRadius = findViewById(R.id.spnRadius);
        List<String> spinnerArrayRad = new ArrayList<>();


        /**
         *  radius spinner
         */

        spinnerArrayRad.add("Search Radius");
        //spinnerArrayRad.add("5");
        spinnerArrayRad.add("10");
        spinnerArrayRad.add("15");
        spinnerArrayRad.add("20");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapterRad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayRad);
        // Specify the layout to use when the list of choices appears
        adapterRad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnRadius.setAdapter(adapterRad);
        //Action to perform on functions - onItemSelected and onNothing selected

        spnRadius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Map<String, String> query = new HashMap<>();
                if (parent != null) {
                    switch(position){
                        case 10:
                            radius = "Within' 10 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", Integer.toString(position));
                            getRetrofit(query);
                            texty.setText(radius);
                            break;
                        case 15:
                            radius = "Within' 15 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", Integer.toString(position));
                            getRetrofit(query);
                            texty.setText(radius);
                            break;
                        case 20:
                            radius = "Within' 20 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", Integer.toString(position));
                            getRetrofit(query);
                            texty.setText(radius);
                            break;
                        default:
                            radius = "Within' 5 miles of your current location";

                            texty.setText(radius);
                            break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });


        /**
         *  location add button
         */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                } else {
                    Intent intent = new Intent(MainActivity.this, AddListingActivity.class);
                    startActivity(intent);
                }

            }
        });

        /**
         *  directory search
         */

        searchView = findViewById(R.id.search);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search The Directory");
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        Intent search = getIntent();
        if (Intent.ACTION_SEARCH.equals(search.getAction())) {
            Map<String, String> query = new HashMap<>();

            query.put("distance", "5");
            query.put("search", search.getStringExtra(SearchManager.QUERY));
            getRetrofit(query);
        }

        /**
         *  location manager to get current location
         */

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        enableMyLocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                400, LocationListener);

        /**
         *  api calls to get listings and marketplace products
         */

        getRetrofitWoo(); //call to woocommerce products api
    }

    /**
     * Location listener to get device current lat/lng
     */

    LocationListener LocationListener = new LocationListener() {

        /**
         * @param location
         */
        @Override
        public void onLocationChanged(Location location) {
            Map<String, String> query = new HashMap<>();

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            query.put("latitude", Double.toString(latitude));
            query.put("longitude", Double.toString(longitude));
            query.put("distance", "5");

            // zoom to current location on map
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            getRetrofit(query); //api call; pass current lat/lng to check if current location in database
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
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
       // enableMyLocation();
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
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
        Map<String, String> query = new HashMap<>();

        query.put("latitude", Double.toString(latitude));
        query.put("longitude", Double.toString(longitude));
        query.put("distance", "5");

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
        getRetrofit(query);
    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    /**
     *
     */
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }



    /**
     * geocode location address using lat/lng
     * @param latitude
     * @param longitude
     */
    public void setAddress(Double latitude, Double longitude){
       this.latitude = latitude;
       this.longitude = longitude;

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 3); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses.size() > 0) {
            Log.d("max", " " + addresses.get(0).getMaxAddressLineIndex());

            String maddress = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()/*String city = addresses.get(0).getLocality();
            String mbldgno = addresses.get(0).getSubThoroughfare(); // get bulding number
            String mstreet = addresses.get(0).getThoroughfare(); //get street name
            String mcity = addresses.get(0).getLocality(); //get city name
            String mstate = addresses.get(0).getAdminArea(); //get state name
            String mzipcode = addresses.get(0).getPostalCode(); //get zip code
            String mcountry = addresses.get(0).getCountryName(); //get country
            /* String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String lat = Double.toString(latitude);
            String lng = Double.toString(longitude); */


            tvAddress.setText(maddress);
            /* tvZip.setText(mzipcode);
            tvState.setText(mstate);
            tvCity.setText(mcity);
            tvStreet.setText(mstreet);
            tvBldgNo.setText(mbldgno); */


            addresses.get(0).getAdminArea();
            address = maddress;
            bldgno = mbldgno;
            street = mstreet;
            state = mstate;
            city = mcity;
            zipcode = mzipcode;
            country = mcountry;
        }

    }
    /**
     * Query API for listings data
     * set URL and make call to API
     */

    private static Retrofit retrofit = null;
    public void getRetrofit(Map<String, String> query) {



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
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
        Call<List<BusinessListings>> call = service.getPostInfo(query);



        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<List<BusinessListings>>() {
            @Override
            public void onResponse(Call<List<BusinessListings>> call, Response<List<BusinessListings>> response) {
                Log.e("main_activity", " response " + response.body());
                if (response.isSuccessful()) {

                    // mListPost = response.body();
                    progressBar.setVisibility(View.GONE); //hide progressBar
                    category.add("Select Category"); //add heading to category spinner


                    // loop through JSON response get parse and output to log

                    for (int i = 0; i < response.body().size(); i++) {


                        /**
                         * onLocationMatch
                         * if device lat/lng equals stored listing lat/lng locationMatch = true
                         */
                        if (response.body().get(i).getLatitude().equals(latitude) && response.body().get(i).getLongitude().equals(longitude)) {

                            Log.e("LocationMatch ", " id: " + response.body().get(i).getId());
                            Log.e("LocationMatch ", " post_title: " + response.body().get(i).getTitle().getRaw());
                            Log.e("LocationMatch", " post_status: " + response.body().get(i).getStatus());
                            //Log.e("LocationMatch ", " post_tags: " + response.body().get(i).getPostTags().get(i));
                            Log.e("LocationMatch ", " default_category " + response.body().get(i).getDefaultCategory());
                            Log.e("LocationMatch ", " post_category" + response.body().get(i).getPostCategory());
                            Log.e("LocationMatch ", " featured: " + response.body().get(i).getFeatured());
                            Log.e("LocationMatch ", " featured_image " + response.body().get(i).getFeaturedImage().getSrc());
                            Log.e("LocationMatch ", " bldgno: " +response.body().get(i).getBldgNo());
                            Log.e("LocationMatch ", " Street: " + response.body().get(i).getStreet());
                            Log.e("LocationMatch ", " City: " + response.body().get(i).getCity());
                            Log.e("LocationMatch ", " State: " + response.body().get(i).getRegion());
                            Log.e("LocationMatch ", " Country: " + response.body().get(i).getCountry());
                            Log.e("LocationMatch ", " Zip: " + response.body().get(i).getZip());
                            Log.e("LocationMatch ", " Latitude: " +response.body().get(i).getLatitude());
                            Log.e("LocationMatch ", " Longitude: " + response.body().get(i).getLongitude());
                            Log.e("LocationMatch ", " RatingBar: " + response.body().get(i).getRating());
                            Log.e("LocationMatch ", " Telephone: " + response.body().get(i).getPhone());
                            Log.e("LocationMatch ", " Email: " + response.body().get(i).getEmail());
                            Log.e("LocationMatch ", " webstie: " + response.body().get(i).getWebsite());
                            Log.e("LocationMatch ", " twitter: " + response.body().get(i).getTwitter());
                            Log.e("LocationMatch ", " facebook: " + response.body().get(i).getFacebook());
                            Log.e("LocationMatch ", " video: " + response.body().get(i).getVideo());
                            Log.e("LocationMatch ", " Hours: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getTodayRange());
                            Log.e("LocationMatch ", " IsOpen: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getCurrentLabel());
                            Log.e("LocationMatch ", " logo: " + response.body().get(i).getLogo());
                            Log.e("LocationMatch ", " Content: " + response.body().get(i).getContent().getRaw());
                            Log.e("LocationMatch ", " Image: " + response.body().get(i).getImages().get(0).getThumbnail());
                            Log.e("LocationMatch ", " Timestamp: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getFullDateFormat());


                            locationMatch.add(new ListingsModel(ListingsModel.IMAGE_TYPE,
                                    response.body().get(i).getId(),
                                    response.body().get(i).getTitle().getRaw(),
                                    response.body().get(i).getStatus(),
                                  //  response.body().get(i).getPostTags().get(i),
                                    response.body().get(i).getDefaultCategory(),
                                    response.body().get(i).getPostCategory(),
                                    response.body().get(i).getFeatured(),
                                    response.body().get(i).getFeaturedImage().getSrc(),
                                    response.body().get(i).getBldgNo(),
                                    response.body().get(i).getStreet(),
                                    response.body().get(i).getCity(),
                                    response.body().get(i).getRegion(),
                                    response.body().get(i).getCountry(),
                                    response.body().get(i).getZip(),
                                    response.body().get(i).getLatitude(),
                                    response.body().get(i).getLongitude(),
                                    response.body().get(i).getRating(),
                                    response.body().get(i).getPhone(),
                                    response.body().get(i).getEmail(),
                                    response.body().get(i).getWebsite(),
                                    response.body().get(i).getTwitter(),
                                    response.body().get(i).getFacebook(),
                                    response.body().get(i).getVideo(),
                                    response.body().get(i).getBusinessHours(),
                                    response.body().get(i).getCommentStatus(),
                                    response.body().get(i).getLogo(),
                                    response.body().get(i).getContent().getRaw(),
                                    response.body().get(i).getFeaturedImage(),
                                    response.body().get(i).getImages().get(0).getSrc(),
                                    response.body().get(i).getImages().get(1).getSrc()));

                            Intent LocationMatch = new Intent(MainActivity.this, ReviewActivity.class);
                            //Intent matchAnimation = new Intent(MainActivity.this, WPPostDetails.class);

                            Bundle locationMatchBundle = new Bundle();
                            locationMatchBundle.putParcelableArrayList("locationMatchBundle", locationMatch);
                            LocationMatch.putExtra("locationMatch", locationMatch);
                            //LocationMatch.getSerializableExtra("locationMatchBundle", locationMatchBundle);
                            //startActivity(matchAnimation);
                            startActivity(LocationMatch);
                            break;
                        } else {
                            //parse response based on ListingsModel class and add to list array ( get category name, description and image)

                            /**
                             * if no location match continue to pars JSON data
                             */

                            Log.e("Location ", " id: " + response.body().get(i).getId());
                            Log.e("Location ", " post_title: " + response.body().get(i).getTitle().getRaw());
                            Log.e("Location ", " post_status: " + response.body().get(i).getStatus());
//                            Log.e("Location ", " post_tags: " + response.body().get(i).getPostTags().get(i));
                            Log.e("Location ", " default_category " + response.body().get(i).getDefaultCategory());
                            Log.e("Location ", " post_category" + response.body().get(i).getPostCategory());
                            Log.e("Location ", " featured: " + response.body().get(i).getFeatured());
                            Log.e("Location ", " featured_image " + response.body().get(i).getFeaturedImage());
                            Log.e("Location ", " bldgno: " +response.body().get(i).getBldgNo());
                            Log.e("Location ", " Street: " + response.body().get(i).getStreet());
                            Log.e("Location ", " City: " + response.body().get(i).getCity());
                            Log.e("Location ", " State: " + response.body().get(i).getRegion());
                            Log.e("Location ", " Country: " + response.body().get(i).getCountry());
                            Log.e("Location ", " Zip: " + response.body().get(i).getZip());
                            Log.e("Location ", " Latitude: " +response.body().get(i).getLatitude());
                            Log.e("Location ", " Longitude: " + response.body().get(i).getLongitude());
                            Log.e("Location ", " RatingBar: " + response.body().get(i).getRating());
                            Log.e("Location ", " Telephone: " + response.body().get(i).getPhone());
                            Log.e("Location ", " Email: " + response.body().get(i).getEmail());
                            Log.e("Location ", " Website: " + response.body().get(i).getWebsite());
                            Log.e("Location ", " twitter: " + response.body().get(i).getTwitter());
                            Log.e("Location ", " facebook: " + response.body().get(i).getFacebook());
                            Log.e("Location ", " video: " + response.body().get(i).getVideo());
                            Log.e("Location ", " Hours: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getTodayRange());
                            Log.e("Location ", " IsOpen: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getCurrentLabel());
                            Log.e("Location ", " logo: " + response.body().get(i).getLogo());
                            Log.e("Location ", " Content: " + response.body().get(i).getContent().getRaw());
                            Log.e("Location ", " Image: " + response.body().get(i).getImages().get(0).getThumbnail());
                            Log.e("Location ", " Timestamp: " + response.body().get(i).getBusinessHours().getRendered().getExtra().getFullDateFormat());


                            verticalList.add(new ListingsModel(ListingsModel.IMAGE_TYPE,
                                    response.body().get(i).getId(),
                                    response.body().get(i).getTitle().getRaw(),
                                    response.body().get(i).getStatus(),
                                   // response.body().get(i).getPostTags().get(i),
                                    response.body().get(i).getDefaultCategory(),
                                    response.body().get(i).getPostCategory(),
                                    response.body().get(i).getFeatured(),
                                    response.body().get(i).getFeaturedImage().getSrc(),
                                    response.body().get(i).getBldgNo(),
                                    response.body().get(i).getStreet(),
                                    response.body().get(i).getCity(),
                                    response.body().get(i).getRegion(),
                                    response.body().get(i).getCountry(),
                                    response.body().get(i).getZip(),
                                    response.body().get(i).getLatitude(),
                                    response.body().get(i).getLongitude(),
                                    response.body().get(i).getRating(),
                                    response.body().get(i).getPhone(),
                                    response.body().get(i).getEmail(),
                                    response.body().get(i).getWebsite(),
                                    response.body().get(i).getTwitter(),
                                    response.body().get(i).getFacebook(),
                                    response.body().get(i).getVideo(),
                                    response.body().get(i).getBusinessHours(),
                                    response.body().get(i).getCommentStatus(),
                                    response.body().get(i).getLogo(),
                                    response.body().get(i).getContent().getRaw(),
                                    response.body().get(i).getFeaturedImage(),
                                    response.body().get(i).getImages().get(0).getSrc(),
                                    response.body().get(i).getImages().get(1).getSrc()));

                            // add category name from array to spinner
                            category.add(response.body().get(i).getPostCategory().get(0).getName());
                            // display category array list in spinner
                            spnCategory.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, category));
                            Log.e("main ", " Category: " + response.body().get(i).getPostCategory().get(0).getName());
                        }
                        verticalAdapter.notifyDataSetChanged();

                    }
                }
                else {
                    Log.e("SNAFU ", " SOMETHING'S FUBAR'd!!! :)");
                }
            }

            @Override
            public void onFailure(Call<List<BusinessListings>> call, Throwable t) {

            }
        });

    }

    /**
     * Query API for WooStore data
     */
    public void getRetrofitWoo() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!
       // httpClient.addInterceptor(logging);  // <-- this is the important line!


       // String baseURL = "https://www.thesablebusinessdirectory.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);

        // pass JSON data to BusinessListings class for filtering
        Call<List<WooProducts>> call = service.getPostWooInfo();

        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<List<WooProducts>>() {
            @Override
            public void onResponse(@NotNull Call<List<WooProducts>> call, Response<List<WooProducts>> response) {
                Log.e("WooCommerce", " response " + response.body());

                //mListPost = response.body();
                progressBar.setVisibility(View.GONE); //hide progressBar


                // loop through JSON response get parse and output to log

                for (int i = 0; i < response.body().size(); i++) {

                    //ifStatement to skip json object from array if value is empty/null


                    Log.e("Product ", "id: " + response.body().get(i).getId());
                    Log.e("Product ", "Title: " + response.body().get(i).getName());
                    Log.e("Product ", "Description" + response.body().get(i).getName());
                    Log.e("Product ", "Rating: " + response.body().get(i).getAverageRating());
                    Log.e("Product ", "Rating Count: " + response.body().get(i).getRatingCount());
                    Log.e("Product ", "Price: " + response.body().get(i).getPrice());
                    Log.e("Product ", "Image: " + response.body().get(i).getImages().get(0).getSrc());
//                    Log.e("main ", " Image1: " + response.body().get(i).getImages().get(1).getSrc());

                    //parse response based on WooModel class and add to list array ( get category name, description and image)
                    horizontalList.add(new WooModel(WooModel.IMAGE_TYPE,
                            response.body().get(i).getName(),
                            response.body().get(i).getName(),
                            response.body().get(i).getAverageRating(),
                            response.body().get(i).getRatingCount(),
                            response.body().get(i).getPrice(),
                            response.body().get(i).getImages().get(0).getSrc()));

                }
                horizontalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WooProducts>> call, Throwable t) {
            }
        });

    }
}