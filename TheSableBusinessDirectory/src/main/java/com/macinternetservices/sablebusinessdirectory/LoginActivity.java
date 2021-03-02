package com.macinternetservices.sablebusinessdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

public class LoginActivity extends AppCompatActivity {

    private AccessTokenTracker accessTokenTracker;
    private LoginButton loginButton;
    private TextView tvSecureMsg, tvGreeting, textView6, textView7 ;
    private ImageView ivGreeter, ivLogo;
    CallbackManager fbLogincallbackManager;
   // Map<Object, Object> query = new HashMap<>();
    ArrayList<UserAuthModel> userinfo = new ArrayList<>();
    String baseURL = "https://www.thesablebusinessdirectory.com", userImage, userName, userEmail;
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    Button btnBack;

    Animation animFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       tvSecureMsg = findViewById(R.id.tvSecureMsg);
       btnBack = findViewById(R.id.btnBack);
       //tvGreeting = findViewById(R.id.tvGreeting);
       //textView6 = findViewById(R.id.textView6);
       //textView7 = findViewById(R.id.textView7);
       ivGreeter = findViewById(R.id.ivGreeter);
       ivLogo = findViewById(R.id.ivLogo);
       loginButton = findViewById(R.id.login_button);
       loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MarkerClusteringActivity.class));
            }
        });



        // Defining the AccessTokenTracker
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                // currentAccessToken is null if the user is logged out
                if (currentAccessToken != null) {
                    useLoginInformation(currentAccessToken);
                    Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(loginIntent);

                } else {
                    //displayName.setText("Please try again...");
                }
            }
        };

        fbLogincallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(fbLogincallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessToken = loginResult.getAccessToken();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        tvSecureMsg.setVisibility(View.VISIBLE);
        tvSecureMsg.startAnimation(animFadeIn);
//        tvGreeting.setVisibility(View.VISIBLE);
        //tvGreeting.startAnimation(animFadeIn);
        //ivGreeter.setVisibility(View.VISIBLE);
        //ivGreeter.startAnimation(animFadeIn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fbLogincallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onStart() {
        super.onStart();
        //This starts the access token tracking
        accessTokenTracker.startTracking();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            useLoginInformation(accessToken);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        // We stop the tracking before destroying the activity
        accessTokenTracker.stopTracking();
    }

        private void useLoginInformation(final AccessToken accessToken) {

            //String mAccessToken = accessToken;
            //query.put("access_token", String.valueOf(accessToken));
        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            Picasso.Builder builder = new Picasso.Builder(getApplicationContext());



            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {


                    userName = object.getString("name");
                    userEmail = object.getString("email");
                    userImage = object.getString("picture");
                   // displayName.setText(object.getString("name"));
                   // emailID.setText(object.getString("email"));
                    //builder.build().load(object.getString("picture")).into(displayImage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map<String, String> query = new HashMap<>();
                query.put("access_token", accessToken.getToken());
                getRetrofit(query);
            }

        });


            // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);



        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //.addInterceptor(BasicAuthInterceptor(username, password))
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
        Call<UserAuthPOJO> call = service.getUserInfo(query);


        // get filtered data from BusinessListings class and add to recyclerView adapter for display on screen
        call.enqueue(new Callback<UserAuthPOJO>() {
            @Override
            public void onResponse(Call<UserAuthPOJO> call, Response<UserAuthPOJO> response) {
                ////Log.e("main_activity", " response " + response.body());
                if (response.isSuccessful()) {

                    // mListPost = response.body();
                    //progressBar.setVisibility(View.GONE); //hide progressBar
                    // loop through JSON response get parse and output to log

                   // for (int i = 0; i < response.body().size(); i++) {

                        // if (String.format(Locale.US, "%10.6f", response.body().get(i).getLatitude()).equals(String.format(Locale.US, "%10.6f", latitude)) &&
                        //         String.format(Locale.US, "%10.6f", response.body().get(i).getLongitude()).equals(String.format(Locale.US, "%10.6f", longitude))) {

                        userinfo.add(new UserAuthModel(UserAuthModel.IMAGE_TYPE,
                                response.body().getStatus(),
                                response.body().getMsg(),
                                response.body().getWpUserId(),
                                response.body().getCookie(),
                                response.body().getUserLogin(), userName, userImage, userEmail));

                        Intent MainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle locationMatchBundle = new Bundle();
                        locationMatchBundle.putParcelableArrayList("userinfo", userinfo);
                        MainActivity.putExtra("userinfo", userinfo);
                        startActivity(MainActivity);
                   // }
                }
            }

            @Override
            public void onFailure(Call<UserAuthPOJO> call, Throwable t) {

            }
        });
    }
}