package com.sable.businesslistingapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    TextView tvTitle, tvState, tvStreet, tvCity, tvZip, tvHours, tvisOpen, tvContent, tvPhone, tvBldgNo;
    ImageView ivImage;
    RatingBar simpleRatingBar;
    private ProgressBar progressBar;
    String baseURL = "https://www.thesablebusinessdirectory.com",id;
    Double latitude, longitude;



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
        tvTitle = findViewById(R.id.tvTitle);
        tvBldgNo = findViewById(R.id.tvBldgNo);
        tvState = findViewById(R.id.tvState);
        tvStreet = findViewById(R.id.tvStreet);
        tvCity = findViewById(R.id.tvCity);
        tvZip = findViewById(R.id.tvZip);
        tvHours = findViewById(R.id.tvHours);
        tvisOpen = findViewById(R.id.tvIsOpen);
        tvContent = findViewById(R.id.tvContent);
        tvPhone = findViewById(R.id.tvPhone);
        ivImage = findViewById(R.id.Icon);
       // progressBar = findViewById(R.id.progressBar);


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
            tvTitle.setText(locationAdd.get(0).name);
            tvBldgNo.setText(locationAdd.get(0).bldgNo);
            tvStreet.setText(locationAdd.get(0).street);
            tvCity.setText(locationAdd.get(0).city);
            tvZip.setText(locationAdd.get(0).zipcode);
            tvPhone.setText(locationAdd.get(0).phone);
          //  tvHours.setText(locationAdd.get(0).hours);
            tvContent.setText(locationAdd.get(0).description);
            tvState.setText(locationAdd.get(0).state);
            latitude = (locationAdd.get(0).lat);
            longitude = (locationAdd.get(0).lng);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).img1)).into(ivImage);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).img2)).into(ivImage);
            builder.build().load(getIntent().getStringExtra(locationAdd.get(0).img3)).into(ivImage);



        } else {

            tvTitle.setText(locationMatch.get(0).title);
            tvBldgNo.setText(locationMatch.get(0).bldgNo);
            tvStreet.setText(locationMatch.get(0).street);
            tvCity.setText(locationMatch.get(0).city);
            tvZip.setText(locationMatch.get(0).zip);
            tvPhone.setText(locationMatch.get(0).phone);
            tvHours.setText(locationMatch.get(0).hours);
            tvContent.setText(locationMatch.get(0).content);
            tvState.setText(locationMatch.get(0).state);
            // tvEmail.setText(locationMatch.get(0).email);
            //tvWebsite.setText(locationMatch.get(0).website);
            latitude = (locationMatch.get(0).lat);
            longitude = (locationMatch.get(0).lng);
            //tvisOpen.setText(locationMatch.get(0).isOpen);
            builder.build().load(getIntent().getStringExtra(locationMatch.get(0).image)).into(ivImage);
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
                    submitData(latitude, longitude);
                }
            }
        });
    }

    private void submitData(final Double latitude, final Double longitude){
        String title = tvTitle.getText().toString();
        String bldgNo = tvBldgNo.getText().toString();
        String street = tvStreet.getText().toString();
        String city = tvCity.getText().toString();
        String state = tvState.getText().toString();
        String zip = tvZip.getText().toString();
        String phone = tvPhone.getText().toString();
        //String email = tvEmail.getText().toString();
        //String website = tvWebsite.getText().toString();
        String lat = latitude.toString();
        String lng = longitude.toString();
        String content = tvContent.getText().toString();
        String hours = tvHours.getText().toString();

        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<List<BusinessListings>> call = service.postData(id, title, street, city, state, zip, phone, lat, lng, bldgNo, /*email, website,*/ content, hours);
        //calling the api
        call.enqueue(new Callback<List<BusinessListings>>() {
            @Override
            public void onResponse(Call<List<BusinessListings>> call, Response<List<BusinessListings>> response) {
                Log.e("add_listing", " response " + response.body());

                progressBar.setVisibility(View.GONE); //hide progressBar
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),
                            "Post Updated Title: "+response.body().get(0).getTitle()+
                                    " Body: "+response.body().get(0).getContent()+
                                    " PostId: "+response.body().get(0).getId(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<BusinessListings>> call, Throwable t) {
                progressBar.setVisibility(View.GONE); //hide progressBar
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}