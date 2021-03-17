package com.macinternetservices.sablebusinessdirectory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WooProductDetail extends AppCompatActivity {
    TextView title;
    WebView webView;
    String url;
    private ProgressBar progressBar;
    RecyclerView horizontalRecyclervView;
    HorizontalAdapter horizontalAdapter;
    ArrayList<WooModel> horizontalList;
    LinearLayoutManager hLayoutManager;
    String baseURL = "https://www.thesablebusinessdirectory.com";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wooproductdetails);

        title = findViewById(R.id.title);
        webView = findViewById(R.id.productwebview);
        progressBar = findViewById(R.id.progressBar1);
        /* Set Horizontal LinearLayout to RecyclerView */
        horizontalRecyclervView = findViewById(R.id.featuredListingsRecyclerView);
        horizontalRecyclervView.setHasFixedSize(true);
        hLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclervView.setLayoutManager(hLayoutManager);
        horizontalList = new ArrayList<>();

        /* Set HorizontalAdapter to RecyclerView */
        horizontalAdapter = new HorizontalAdapter(horizontalList, getApplicationContext());
        horizontalRecyclervView.setAdapter(horizontalAdapter);

        if( getIntent().getExtras() == null){

            url = "https://www.thesablebusinessdirectory.com/shop/";

        } else {
            url = getIntent().getStringExtra("url");

        }

        new MyAsynTask().execute();
        getRetrofitWoo(); //call to woocommerce products api


    }

    private class MyAsynTask extends AsyncTask<Void, Void, org.jsoup.nodes.Document> {
        @Override
        protected org.jsoup.nodes.Document doInBackground(Void... voids) {

            org.jsoup.nodes.Document document = null;
            try {
                document= Jsoup.connect(url).get();
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

            webView.loadDataWithBaseURL(url,document.toString(),"text/html","utf-8","");
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setBuiltInZoomControls(true);

            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);


                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
            progressBar.setVisibility(View.GONE); //hide progressBar


        }
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
}

