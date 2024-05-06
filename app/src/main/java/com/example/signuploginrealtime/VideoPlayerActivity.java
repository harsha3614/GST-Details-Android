// VideoPlayerActivity.java

package com.example.signuploginrealtime;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        webView = findViewById(R.id.webView);

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");

        if (videoUrl != null) {
            playVideo(videoUrl);
        }
    }

    private void playVideo(String videoUrl) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Ensure links open in WebView rather than in a browser
        webView.setWebViewClient(new WebViewClient());

        // Enable video playback
        webView.setWebChromeClient(new WebChromeClient());

        // Load the YouTube video URL
        webView.loadUrl(videoUrl);
    }
}
