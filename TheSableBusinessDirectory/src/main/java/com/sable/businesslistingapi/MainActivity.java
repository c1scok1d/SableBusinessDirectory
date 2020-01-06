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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.login.widget.LoginButton;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {


    /**
     * permissions request code
     */
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    public static Double latitude, longitude, lstKnownLat, lstKnownLng;

    public

    TextView tvAddress, tvUserName, tvUserEmail, tvWpStatus, tvWpMsg, tvWpUserId, tvCity, tvWpUserLogin;
    RecyclerView verticalRecyclerView, horizontalRecyclervView, horizontalRecyclervView2, verticalRecyclerView2;
    private ProgressBar progressBar;
    LinearLayoutManager mLayoutManager, hLayoutManager,  hLayoutManager2;
    VerticalAdapter verticalAdapter, verticalAdapter2;
    //RecyclerView horizontalRecyclervView;
    HorizontalAdapter horizontalAdapter;
    ArrayList<WooModel> horizontalList;
    //LinearLayoutManager hLayoutManager;

    public static String baseURL = "https://www.thesablebusinessdirectory.com", radius, address, state, country,
            zipcode, city, street, bldgno, todayRange, username = "android_app", isOpen, email,
            password = "mroK zH6o wOW7 X094 MTKy fwmY", userName, userEmail, userImage, userId, firstName, lastName;

    ArrayList<ListingsModel> verticalList;
    ArrayList<ListingsModel> locationMatch = new ArrayList<>();
    List<String> spinnerArrayRad = new ArrayList<>();
    List<String> category = new ArrayList<>();
    ArrayList<String> userActivityArray = new ArrayList<>();
    ArrayList<ListingsModel> locationReview = new ArrayList<>();
    Spinner spnCategory, spnRadius;
    ImageView ivUserImage, spokesperson;
    private static final int FRAME_TIME_MS = 15000;
    Thread updateMsg;

    ImageButton btnAdd, btnShop;
    WebView wvAdvert1;



    SearchView searchView;
    LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;

    LocationManager locationManager;

    CallbackManager fbLogincallbackManager;
    private AccessTokenTracker accessTokenTracker;


    AccessToken accessToken = AccessToken.getCurrentAccessToken();

    private TextSwitcher textSwitcher;
    private SlidingUpPanelLayout mLayout;


    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    //logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new BasicAuthInterceptor(username, password))
            .addInterceptor(logging)
            .build();

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        horizontalRecyclervView = findViewById(R.id.horizontalRecyclerView);
        horizontalRecyclervView.setHasFixedSize(true);

        horizontalRecyclervView2 = findViewById(R.id.horizontalRecyclerView2);
        horizontalRecyclervView2.setHasFixedSize(true);
        hLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        hLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclervView.setLayoutManager(hLayoutManager);
        horizontalRecyclervView2.setLayoutManager(hLayoutManager2);

        horizontalList = new ArrayList<>();
        wvAdvert1 = findViewById(R.id.wvAdvert1);

        wvAdvert1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/Ripl_Video-3a1f45a7-3fe3-4fcc-9420-aba9d079b397.mp4");
                return super.shouldOverrideUrlLoading(view, request);
            }
        });


        /* Set HorizontalAdapter to RecyclerView */
        horizontalAdapter = new HorizontalAdapter(horizontalList, getApplicationContext());
        horizontalRecyclervView.setAdapter(horizontalAdapter);
        horizontalRecyclervView2.setAdapter(horizontalAdapter);


        getRetrofitWoo(); //call to woocommerce products api

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


        /**
         * radio buttons on map
         */

        RadioGroup radioGroup = findViewById(R.id.radio_group_list_selector);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("Radio Button No: ", " response " + checkedId);
                Toast.makeText(getApplicationContext(), "This is Radio Button: " + checkedId, Toast.LENGTH_SHORT).show();
            }
        });

/**
 * login via facebook
 */
        fbLogincallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(fbLogincallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e("Facebook Login Successful ", " response " + loginResult);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("Facebook Login Error ", " response " + exception);
                    }
                });

        Boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {
            useLoginInformation(accessToken);
            Log.e("onCreate Login", " Access Token: " + accessToken.getToken());
        } else {
            // Intent goHome = new Intent(v.getContext(), ListReviewActivity.class);*/
            LinearLayout userImageLayout = findViewById(R.id.userImageLayout);
            LinearLayout userNameLayout = findViewById(R.id.userNameLayout);
            userImageLayout.setVisibility(View.GONE);
            userNameLayout.setVisibility(View.GONE);
        }

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken != null) {
                    accessToken = currentAccessToken;
                    useLoginInformation(currentAccessToken);
                    startActivity(getIntent());
                } else {
                    // Intent goHome = new Intent(v.getContext(), ListReviewActivity.class);
                    startActivity(getIntent());
                    LinearLayout userImageLayout = findViewById(R.id.userImageLayout);
                    LinearLayout userNameLayout = findViewById(R.id.userNameLayout);
                    userImageLayout.setVisibility(View.GONE);
                    userNameLayout.setVisibility(View.GONE);
                }
            }
        };

        spnRadius = findViewById(R.id.spnRadius);
        spnCategory = findViewById(R.id.spnCategory);

        //tvAddress = findViewById(R.id.tvAddress);
        tvUserName = findViewById(R.id.tvUserName);
        ivUserImage = findViewById(R.id.ivUserImage);
        tvWpUserId = findViewById(R.id.tvWpUserId);
        textSwitcher = findViewById(R.id.textSwitcher);

        Animation fadeIn = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);

        Animation fadeOut = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        textSwitcher.setInAnimation(fadeIn);
        textSwitcher.setOutAnimation(fadeOut);


        textSwitcher.setCurrentText("The Sable Business Directory is a perfect platform for supporting black owned businesses and services providers of any kind.");


        /*
            BEGIN vertical Recycler View
         */
        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);
        progressBar = findViewById(R.id.progressbar);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        verticalRecyclerView.setLayoutManager(mLayoutManager);
        verticalList = new ArrayList<>();
        locationMatch = new ArrayList<>();
        verticalAdapter = new VerticalAdapter(verticalList, userName, userEmail, userImage, userId, MainActivity.this);
        verticalRecyclerView.setAdapter(verticalAdapter);
        verticalRecyclerView.setNestedScrollingEnabled(false);
        btnAdd = findViewById(R.id.btnAdd);
        btnShop = findViewById(R.id.btnShop);
        spokesperson = findViewById(R.id.spokesperson);
        tvCity = findViewById(R.id.tvCity);

        //final TextView texty = findViewById(R.id.texty);
        /**
         *  radius spinner
         */

        spinnerArrayRad.add("Search Radius");
        //spinnerArrayRad.add("5");
        spinnerArrayRad.add("10");
        spinnerArrayRad.add("15");
        spinnerArrayRad.add("20");

        ArrayAdapter<String> adapterRad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayRad);
        adapterRad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRadius.setAdapter(adapterRad);

        spnRadius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Map<String, String> query = new HashMap<>();
                if (parent != null) {
                    switch (position) {
                        case 1:
                            radius = "Within' 10 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", "10");
                            query.put("order", "asc");
                            query.put("orderby", "distance");
                            // 10 mile distance query
                            getRetrofit(query);
                            //texty.setText(radius);
                            break;
                        case 2:
                            radius = "Within' 15 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", "15");
                            query.put("order", "asc");
                            query.put("orderby", "distance");
                            //15 mile distance query
                            getRetrofit(query);
                            //texty.setText(radius);
                            break;
                        case 3:
                            radius = "Within' 20 miles of your current location";

                            query.put("latitude", Double.toString(longitude));
                            query.put("longitude", Double.toString(latitude));
                            query.put("distance", "20");
                            query.put("order", "asc");
                            query.put("orderby", "distance");
                            // 20 mile distance query
                            getRetrofit(query);
                            // texty.setText(radius);
                            break;
                        default:
                            radius = "Within' 5 miles of your current location";
                            // texty.setText(radius);
                            break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        category.add("Category"); //add heading to category spinner
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, category);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapterCategory);

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spnCategory.getSelectedItem() != "Category") {

                    Map<String, String> query = new HashMap<>();
                    //spinner category query
                    query.put("category", spnCategory.getSelectedItem().toString());
                    getRetrofit(query);
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

                if (!isLoggedIn) {
                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginIntent);

                    //goto login activity get username and email via facebook create account, return here to check again and proceed

                    Toast.makeText(getApplicationContext(), "User must be logged in to add a business listing.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, AddListingActivity.class);
                    startActivity(intent);
                }
            }

            //}
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WooProductDetail.class);
                startActivity(intent);
            }
        });

        spokesperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        /**
         *  directory search
         */

        searchView = findViewById(R.id.search);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Tap To Search");
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        Intent search = getIntent();
        if (Intent.ACTION_SEARCH.equals(search.getAction())) {
            Map<String, String> query = new HashMap<>();

            //search bar query
            query.put("order", "asc");
            query.put("orderby", "distance");
            query.put("search", search.getStringExtra(SearchManager.QUERY));
            getRetrofit(query);
        }

        updateMsg = new Thread() {
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
                                    textSwitcher.setText("We provide a one of a kind online platform that combines " +
                                            "a searchable geographical based geo-directory, social media and e-commerce platforms " +
                                            "catered specifically to black owned businesses and service providers. ");
                                    break;

                                case 2:
                                    textSwitcher.setText("The Sable Business Directory is designed to help those wanting to support " +
                                            "and frequent black owned businesses and service providers find black owned " +
                                            "businesses and service providers.");
                                    break;

                                default:
                                    textSwitcher.setText("Tap our spokesman to the right for an introduction and tutorial on what " +
                                            "The Sable Business Directory is and how it works!!!");
                                    break;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        updateMsg.start();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        //userActivityArray = null;
        if (userActivityArray.size() > 0) {

            userActivityArray = this.getIntent().getExtras().getStringArrayList("userActivityArray");
        }

        /**
         *  location manager to get current location
         */
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkPermissions();
            return;
        }

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                400, LocationListener);


        /***
         *  BEGIN SLIDE UP
         */

        // setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

       /* ListView lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }
        });

        List<String> your_array_list = Arrays.asList(
                "This",
                "Is",
                "An",
                "Example",
                "ListView",
                "That",
                "You",
                "Can",
                "Scroll",
                ".",
                "It",
                "Shows",
                "How",
                "Any",
                "Scrollable",
                "View",
                "Can",
                "Be",
                "Included",
                "As",
                "A",
                "Child",
                "Of",
                "SlidingUpPanelLayout"
        );

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);*/

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("onPanelSlide", "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("onPanelStateChanged", "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
        new MyAsynTask().execute();


    } //END ON CREATE



    private class MyAsynTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(Void... voids) {

            org.jsoup.nodes.Document document = null;
            try {
                document= Jsoup.connect("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/Ripl_Video-3a1f45a7-3fe3-4fcc-9420-aba9d079b397.mp4").get();
                document.getElementsByClass("navbar").remove();
                document.getElementsByClass("woocommerce-products-header").remove();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;

        }

        @Override
        protected void onPostExecute(org.jsoup.nodes.Document document) {
            super.onPostExecute(document);

          //  wvAdvert1.loadDataWithBaseURL("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/Ripl_Video-3a1f45a7-3fe3-4fcc-9420-aba9d079b397.mp4",document.toString(),"text/html","utf-8","");
            wvAdvert1.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            wvAdvert1.getSettings().setLoadWithOverviewMode(true);
            wvAdvert1.getSettings().setUseWideViewPort(true);
            wvAdvert1.getSettings().setBuiltInZoomControls(true);

            wvAdvert1.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    String url = "https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/Ripl_Video-3a1f45a7-3fe3-4fcc-9420-aba9d079b397.mp4";
                    //view.loadUrl("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/Ripl_Video-3a1f45a7-3fe3-4fcc-9420-aba9d079b397.mp4");

                    if (url.endsWith(".mp3")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(url), "audio/*");
                        view.getContext().startActivity(intent);
                        return true;
                    } else if (url.endsWith(".mp4") || url.endsWith(".3gp")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(url), "video/*");
                        view.getContext().startActivity(intent);
                        return true;
                    } else {
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                }
            });
            progressBar.setVisibility(View.GONE); //hide progressBar


        }
    }
    /**
     * Checks the dynamically-controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);

            int [] foo = grantResults;
            //startActivity(getIntent());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }  else {
                        startActivity(getIntent());
                    }
                }
                // all permissions were granted
                break;
        }
    }

    public void onStart() {
        super.onStart();
        Map<String, String> query = new HashMap<>();
//This starts the access token tracking
        accessTokenTracker.startTracking();
        //latitude = location.getLatitude();
        //longitude = location.getLongitude();

        query.put("latitude", String.valueOf(latitude));
        query.put("longitude", String.valueOf(longitude));
        //query.put("distance", "5");
        query.put("order", "asc");
        query.put("orderby", "distance");
    }

    public void onResume() {
        super.onResume();
        Map<String, String> query = new HashMap<>();
//This starts the access token tracking
        accessTokenTracker.startTracking();
       // latitude = location.getLatitude();
       // longitude = location.getLongitude();

        query.put("latitude", String.valueOf(latitude));
        query.put("longitude", String.valueOf(longitude));
        //query.put("distance", "5");
        query.put("order", "asc");
        query.put("orderby", "distance");
    }



    public void onDestroy() {
        super.onDestroy();
        // We stop the tracking before destroying the activity
        accessTokenTracker.stopTracking();
        deleteCache(this);
    }

    public void useLoginInformation(final AccessToken accessToken) {


        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
                try {
                    AccessToken mAccessToken = accessToken;
                    userName = object.getString("name");
                    userEmail = object.getString("email");
                    userImage = object.getJSONObject("picture").getJSONObject("data").getString("url");

                    String[] parts = (object.getString("name").split(" "));
                    firstName = parts[0];
                    lastName = parts[1];
                    tvUserName.setText(firstName);
                    //    tvUserEmail.setText(object.getString("email"));
                    builder.build().load(object.getJSONObject("picture").getJSONObject("data").getString("url")).into(ivUserImage);

                    Map<String, String> query = new HashMap<>();
                    query.put("access_token", mAccessToken.getToken());
                    loginUser(query);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fbLogincallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     *  get last known location
     */
    private void fetchLastLocation() {

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                       /* Map<String, String> query = new HashMap<>();

                        query.put("latitude", String.valueOf(latitude));
                        query.put("longitude", String.valueOf(longitude));
                        //query.put("distance", "5");
                        query.put("order", "asc");
                        query.put("orderby", "distance");
                        getRetrofit(query); //api call; pass current lat/lng to check if current location in database
                        setAddress(latitude, longitude);*/
                    }
                });

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

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            query.put("latitude", String.valueOf(latitude));
            query.put("longitude", String.valueOf(longitude));
            //query.put("distance", "5");
            query.put("order", "asc");
            query.put("orderby", "distance");
            getRetrofit(query); //api call; pass current lat/lng to check if current location in database
            setAddress(latitude, longitude);
        }

        /**
         * @param provider
         * @param status
         * @param extras
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        /**
         * @param provider
         */
        @Override
        public void onProviderEnabled(String provider) {
        }

        /**
         * @param provider
         */
        @Override
        public void onProviderDisabled(String provider) {
        }

    };

   // LocationManager mLocationManager;

    /**
     * @param map
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        //checkPermissions();
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

    }




    /**
     * @return
     */
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Getting current location...", Toast.LENGTH_SHORT).show();
        Map<String, String> query = new HashMap<>();

        query.put("latitude", String.valueOf(latitude));
        query.put("longitude", String.valueOf(longitude));
        //query.put("distance", "5");
        query.put("order", "asc");
        query.put("orderby", "distance");
        getRetrofit(query); //api call; pass current lat/lng to check if current location in database
        setAddress(latitude, longitude);
        return false;
    }

    /**
     * @param location
     */
    @Override
    public void onMyLocationClick(Location location) {
        Toast.makeText(this, "Getting current location...", Toast.LENGTH_SHORT).show();
        Map<String, String> query = new HashMap<>();

        query.put("latitude", String.valueOf(latitude));
        query.put("longitude", String.valueOf(longitude));
        //query.put("distance", "5");
        query.put("order", "asc");
        query.put("orderby", "distance");
        getRetrofit(query); //api call; pass current lat/lng to check if current location in database
        setAddress(latitude, longitude);
    }

    /**
     * geocode location address using lat/lng
     * @param latitude
     * @param longitude
     */
    public void setAddress(Double latitude, Double longitude){
       //this.latitude = latitude;
       //this.longitude = longitude;

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 3); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses != null) {
            Log.d("max", " " + addresses.get(0).getMaxAddressLineIndex());

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()/*String city = addresses.get(0).getLocality();
            bldgno = addresses.get(0).getSubThoroughfare(); // get bulding number
            street = addresses.get(0).getThoroughfare(); //get street name
            city = addresses.get(0).getLocality(); //get city name
            state = addresses.get(0).getAdminArea(); //get state name
            zipcode = addresses.get(0).getPostalCode(); //get zip code
            country = addresses.get(0).getCountryName(); //get country
          //  tvAddress.setText(address);
         //   tvCity.setText(addresses.get(0).getLocality());
            addresses.get(0).getAdminArea();
        } else {

            Toast.makeText(this, "No GPS location available!  " +
                    "Please check your mobile device for possible service issues." , Toast.LENGTH_LONG).show();
        }

    }
    /**
     * Query API for listings data
     * set URL and make call to API
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
    private static Retrofit retrofit = null;
    public void getRetrofit(final Map<String, String> query) {


            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);

        // pass JSON data to BusinessListings class for filtering
        Call<List<BusinessListings>> call = service.getPostInfo(query);


        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<List<BusinessListings>>() {
            @Override
            public void onResponse(Call<List<BusinessListings>> call, Response<List<BusinessListings>> response) {
                Log.e("getRetrofit_METHOD_SUCCESS ", " response " + response.body());
                //Log.e("main_activity", " response " + response.body());
                if (response.isSuccessful()) {

                    // mListPost = response.body();
                    progressBar.setVisibility(View.GONE); //hide progressBar
                    // loop through JSON response get parse and output to log

                    for (int i = 0; i < response.body().size(); i++) {

                        BusinessListings.BusinessHours businessHours = response.body().get(i).getBusinessHours();

                        if(businessHours == null){
                            String today= "null";
                            //Log.e("Location ", " Today: " +today);
                            //Log.e("Location ", " IsOpen: " +today);
                        } else {
                            todayRange = response.body().get(i).getBusinessHours().getRendered().getExtra().getTodayRange();
                            isOpen =  response.body().get(i).getBusinessHours().getRendered().getExtra().getCurrentLabel();
                        }
                        /**
                         * onLocationMatch
                         * if device lat/lng equals stored listing lat/lng locationMatch = true
                         * add all matching data to array and launch Review Activity
                         *
                         */

                        if (String.valueOf(response.body().get(i).getLatitude()).equals(String.valueOf(latitude)) &&
                                String.valueOf(response.body().get(i).getLongitude()).equals(String.valueOf(longitude)) && userActivityArray.size() > 0 ) {

                            // Boolean timeDiff = response.body().get(i).getDateGmt().compareTo(currentTime) > 4;

                            locationMatch.add(new ListingsModel(ListingsModel.IMAGE_TYPE,
                                    response.body().get(i).getId(),
                                    response.body().get(i).getTitle().getRaw(),
                                    response.body().get(i).getLink(),
                                    response.body().get(i).getStatus(),
                                    response.body().get(i).getPostCategory().get(0).getName(),
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
                                    response.body().get(i).getRatingCount(),
                                    response.body().get(i).getPhone(),
                                    response.body().get(i).getEmail(),
                                    response.body().get(i).getWebsite(),
                                    response.body().get(i).getTwitter(),
                                    response.body().get(i).getFacebook(),
                                    response.body().get(i).getVideo(),
                                    todayRange,
                                    isOpen,
                                    response.body().get(i).getLogo(),
                                    response.body().get(i).getContent().getRaw(),
                                    response.body().get(i).getFeaturedImage().getSrc()));

                            Intent LocationMatch = new Intent(MainActivity.this, ReviewActivity.class);
                            Bundle locationMatchBundle = new Bundle();
                            locationMatchBundle.putParcelableArrayList("locationMatchBundle", locationMatch);
                            LocationMatch.putExtra("locationMatch", locationMatch);
                            startActivity(LocationMatch);
                            break;
                        } else {
                            /**
                             * populate vertical recycler in Main Activity
                             */
                            verticalList.add(new ListingsModel(ListingsModel.IMAGE_TYPE,
                                    response.body().get(i).getId(),
                                    response.body().get(i).getTitle().getRaw(),
                                    response.body().get(i).getLink(),
                                    response.body().get(i).getStatus(),
                                    response.body().get(i).getPostCategory().get(0).getName(),
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
                                    response.body().get(i).getRatingCount(),
                                    response.body().get(i).getPhone(),
                                    response.body().get(i).getEmail(),
                                    response.body().get(i).getWebsite(),
                                    response.body().get(i).getTwitter(),
                                    response.body().get(i).getFacebook(),
                                    response.body().get(i).getVideo(),
                                    todayRange,
                                    isOpen,
                                    response.body().get(i).getLogo(),
                                    response.body().get(i).getContent().getRaw(),
                                    response.body().get(i).getFeaturedImage().getSrc()));


                            Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(response.body().get(i).getLatitude(), response.body().get(i).getLongitude()))
                                    .title(response.body().get(i).getTitle().getRaw())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            marker.setTag(verticalList.get(i).id);

                            //System.out.println("Marker Tag is: " +(marker.getTag()));

                            // add category name from array to spinner
                            category.add(response.body().get(i).getPostCategory().get(0).getName());
                            latLngBoundsBuilder.include(new LatLng(response.body().get(i).getLatitude(), response.body().get(i).getLongitude()));
                        }

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(latitude, longitude))      // Sets the center of the map to location user
                                //.zoom(100)                   // Sets the zoom
                                .bearing(90)                // Sets the orientation of the camera to east
                                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                                .build();                   // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        LatLngBounds bounds = latLngBoundsBuilder.build();

                        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                            @Override
                            public void onMapLoaded() {
                                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
                            }
                        });
                    }
                    verticalAdapter.notifyDataSetChanged();
                }
                else {
                    Log.e("getRetrofit_METHOD_noResponse ", " SOMETHING'S FUBAR'd!!! :)");
                }
            }

            @Override
            public void onFailure(Call<List<BusinessListings>> call, Throwable t) {
                Log.e("getRetrofit_METHOD_FAILURE ", " Re-running method...");
                getRetrofit(query);
            }
        });

    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick (final Marker marker) {
        for (int i = 0; i < verticalList.size(); i++) {
            if (verticalList.get(i).id == Integer.parseInt(marker.getTag().toString())) {
                locationReview.add((new ListingsModel(ListingsModel.IMAGE_TYPE,
                        verticalList.get(i).id,
                        verticalList.get(i).title,
                        verticalList.get(i).link,
                        verticalList.get(i).status,
                        verticalList.get(i).category,
                        verticalList.get(i).featured,
                        verticalList.get(i).featured_image,
                        verticalList.get(i).bldgno,
                        verticalList.get(i).street,
                        verticalList.get(i).city,
                        verticalList.get(i).state,
                        verticalList.get(i).country,
                        verticalList.get(i).zipcode,
                        verticalList.get(i).latitude,
                        verticalList.get(i).longitude,
                        verticalList.get(i).rating,
                        verticalList.get(i).ratingCount,
                        verticalList.get(i).phone,
                        verticalList.get(i).email,
                        verticalList.get(i).website,
                        verticalList.get(i).twitter,
                        verticalList.get(i).facebook,
                        verticalList.get(i).video,
                        verticalList.get(i).hours,
                        verticalList.get(i).isOpen,
                        verticalList.get(i).logo,
                        verticalList.get(i).content,
                        verticalList.get(i).featured_image)));

                System.out.println("ID To match:  " +Integer.parseInt(marker.getTag().toString()));
                System.out.println("Matched:  " +verticalList.get(i).id);
                System.out.println("Stuff:"  +verticalList.get(i).title +verticalList.get(i).latitude + verticalList.get(i).longitude);
                System.out.println("Array is: " +locationReview);

                Intent showReviews = new Intent(getApplicationContext(), ListReviewActivity.class);

                Bundle locationReviewBundle = new Bundle();
                locationReviewBundle.putParcelableArrayList("locationReviewBundle", locationReview);

                showReviews.putExtra("locationReview", locationReview);
                startActivity(showReviews);
                break;
            } else { }
        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    //Retrofit retrofit = null;
    public void loginUser(final Map<String, String> query) {
        Retrofit retrofit = null;

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);

        // pass JSON data to BusinessListings class for filtering
        Call<UserAuthPOJO> call = service.getUserInfo(query);


        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<UserAuthPOJO>() {
            @Override
            public void onResponse(Call<UserAuthPOJO> call, Response<UserAuthPOJO> response) {
                Log.e("loginUser_METHOD_SUCCESS", " response " + response.body());
                if (response.isSuccessful()) {
                        userId = String.valueOf(response.body().getWpUserId());
                        tvWpUserId.setText(String.valueOf(response.body().getWpUserId()));
                } else {
                    Log.e("loginUser_METHOD_noResponse ", " SOMETHING'S FUBAR'd!!! :)");
                }
            }

            @Override
            public void onFailure(Call<UserAuthPOJO> call, Throwable t) {
                Log.e("loginUser_METHOD_FAILURE ", " Re-running method...");
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

                // loop through JSON response get parse and output to log

                for (int i = 0; i < response.body().size(); i++) {

                    //parse response based on WooModel class and add to list array ( get category name, description and image)
                    horizontalList.add(new WooModel(WooModel.IMAGE_TYPE,
                            response.body().get(i).getName(),
                            response.body().get(i).getPermalink(),
                            response.body().get(i).getAverageRating(),
                            response.body().get(i).getRatingCount(),
                            response.body().get(i).getName(),
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


    /**
     * more slider shit
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.5f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    } //END OF SLIDER SHIT

    /**
     *
     * @param context
     * CLEAR CACHE on close
     */

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}