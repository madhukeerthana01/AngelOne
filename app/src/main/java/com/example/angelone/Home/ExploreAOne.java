package com.example.angelone.Home;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.angelone.R;

public class ExploreAOne extends AppCompatActivity {
    private WebView webview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_aone);


        webview1=(WebView)findViewById(R.id.webView);

                webview1.getSettings().setLoadsImagesAutomatically(true);
                webview1.getSettings().setJavaScriptEnabled(true);
                webview1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webview1.loadUrl("https://www.angelone.in/");
    }
}