package com.sable.businesslistingapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WPPostDetails extends AppCompatActivity {
    TextView title;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);

        title = findViewById(R.id.title);
        webView = findViewById(R.id.postwebview);
        //Intent i = getIntent();
        //int postion = i.getExtras().getInt("locationMatch");

        //Log.e("WpPostDetails", " title is " + MainActivity.mListPost.get(postion).getTitle().getRendered());

        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/08/sableWelcomeWide.mp4");
        //webView.loadUrl(MainActivity.mListPost.get(postion).getLink());
        //open webview inside app
        webView.setWebViewClient(new WebViewClient());
    }
}

