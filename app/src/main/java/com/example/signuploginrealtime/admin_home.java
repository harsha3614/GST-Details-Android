package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the TextView for GST Rates
        TextView tvGSTRates = findViewById(R.id.tvGSTRates);
        TextView tvGSTAct = findViewById(R.id.tvGSTAct);
        TextView tvGSTRules = findViewById(R.id.tvGSTRules);
        TextView news = findViewById(R.id.tvNews);
        TextView videos = findViewById(R.id.tvVideos);

        // Set an OnClickListener for GST Rates TextView
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(admin_home.this, upload_videos.class);
                startActivity(intent);
            }
        });
        // Set an OnClickListener for GST Rates TextView
        tvGSTRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(admin_home.this, UploadGstRulesActivity.class);
                startActivity(intent);
            }
        });
        // Set an OnClickListener for GST Rates TextView
        tvGSTRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(admin_home.this, upload_Pdf.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for GST Rates TextView
        tvGSTAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(admin_home.this, Upload_gst_act.class);
                startActivity(intent);
            }
        });


        // Set an OnClickListener for GST Rates TextView
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(admin_home.this, add_enterprise_list.class);
                startActivity(intent);
            }
        });

        // Other initialization code goes here
    }
}

