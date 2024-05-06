package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Admin_home_page extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page); // Replace with the actual layout file name

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.Add_Enterprises) {
                    // Redirect to GstCalculationActivity when "Dashboard" is clicked
                    Intent intent = new Intent(Admin_home_page.this, add_enterprise_list.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_News) {
                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);

                    startActivity(viewWrappersIntent);

                } else if (itemId == R.id.Add_Videos) {
                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, upload_videos.class);

                    startActivity(viewWrappersIntent);
                } else if (itemId == R.id.Add_GST_Rates) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, Add.class);
//
//                    startActivity(viewWrappersIntent);
                } else if (itemId == R.id.menu_logout) {
                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, admin_login.class);

                    startActivity(viewWrappersIntent);
                }

                // Close the drawer after handling the item click
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        bottomNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.action_home) {
                    // Redirect to GstCalculationActivity when "Dashboard" is clicked
                    Intent intent = new Intent(Admin_home_page.this, Admin_home_page.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);
//                    startActivity(viewWrappersIntent);

                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}