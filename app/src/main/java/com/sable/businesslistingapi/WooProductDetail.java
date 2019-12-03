package com.sable.businesslistingapi;

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

import org.jsoup.Jsoup;

import java.io.IOException;

public class WooProductDetail extends AppCompatActivity {
    TextView title;
    WebView webView;
    String url;
    private ProgressBar progressBar;



   /* public WooProductDetail() {
        // Required empty public constructor
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wooproductdetails);

        title = findViewById(R.id.title);
        webView = findViewById(R.id.productwebview);
        progressBar = findViewById(R.id.progressbar);

        if( getIntent().getExtras() == null){

            url = "https://www.thesablebusinessdirectory.com/shop/";

        } else {
            url = getIntent().getStringExtra("url");

        }

       new MyAsynTask().execute();


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
//            progressBar.setVisibility(View.GONE); //hide progressBar


            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);


                    return super.shouldOverrideUrlLoading(view, request);
                }
            });

        }
    }
}

