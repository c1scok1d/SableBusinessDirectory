package com.sable.businesslistingapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsVideo extends AppCompatActivity {



    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_video);

        Button btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(AboutUsVideo.this, MainActivity.class);
                startActivity(MainActivity);

            }
        });

        webView = findViewById(R.id.webView1);
       // webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.thesablebusinessdirectory.com/wp-content/uploads/2019/06/TheSableBusinessDirectorycom.mp4");

    }
}
