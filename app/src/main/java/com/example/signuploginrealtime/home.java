package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {


    private PopupMenu popupMenu;
    private boolean isPopupMenuShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the TextView for GST Rates
        TextView tvGSTRates = findViewById(R.id.tvGSTRates);
        TextView tvServices = findViewById(R.id.tvServices);
        TextView tvGSTRules = findViewById(R.id.tvGSTRules);
        TextView news = findViewById(R.id.tvNews);
        TextView videos = findViewById(R.id.tvVideos);


        ImageView menuIcon = findViewById(R.id.menuIcon);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show or hide the popup menu based on your requirements
                if (isPopupMenuShowing) {
                    popupMenu.dismiss();
                } else {
                    showPopupMenu(v);
                }
            }
        });
        // Set an OnClickListener for GST Rates TextView
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(home.this, videoslist.class);
                startActivity(intent);
            }
        });
        // Set an OnClickListener for GST Rates TextView
        tvGSTRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(home.this, PdfRules.class);
                startActivity(intent);
            }
        });
        // Set an OnClickListener for GST Rates TextView
        tvGSTRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(home.this, PdfListActivity.class);
                startActivity(intent);
            }
        });
        // Set an OnClickListener for GST Rates TextView
        tvServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(home.this, display_services.class);
                startActivity(intent);
            }
        });


        // Set an OnClickListener for GST Rates TextView
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When GST Rates TextView is clicked, open AddNews activity
                Intent intent = new Intent(home.this, NewsListActivity.class);
                startActivity(intent);
            }
        });

        // Other initialization code goes here
    }

    private void showPopupMenu(View view) {
        popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.nav_menu);

        // Set a click listener for each item in the popup menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_GST_Calculator) {
                    // Redirect to AddWrapperActivity when "Add Wrappers" is clicked
                    Intent addWrapperIntent = new Intent(home.this, GstCalculationActivity.class);

                    startActivity(addWrapperIntent);
                    return true;
                } else if (itemId == R.id.menu_Invoice) {
                    Intent viewWrappersIntent = new Intent(home.this, GstInvoiceMakerActivity.class);

                    startActivity(viewWrappersIntent);
                    return true;
                } else if (itemId == R.id.menu_Feedback) {
                    Intent viewWrappersIntent = new Intent(home.this, Feedback.class);

                    startActivity(viewWrappersIntent);
                    return true;
                } else if (itemId == R.id.menu_Rate_App) {
                    Intent viewWrappersIntent = new Intent(home.this, Rate_app.class);

                    startActivity(viewWrappersIntent);
                    return true;
                } else if (itemId == R.id.menu_logout) {
                    Intent logoutIntent = new Intent(home.this, home.class);
                    startActivity(logoutIntent);
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                isPopupMenuShowing = false;
            }
        });

        popupMenu.show();
        isPopupMenuShowing = true;
    }

}

