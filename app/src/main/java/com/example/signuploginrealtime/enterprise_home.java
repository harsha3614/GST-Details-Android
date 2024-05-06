package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.signuploginrealtime.Add_Auditors;
import com.example.signuploginrealtime.Add_Services;
import com.example.signuploginrealtime.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class enterprise_home extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_home);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Set up item click listener or other configurations as needed
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.dashboard) {
                    // Handle Dashboard click
                    // For example, redirect to a Dashboard activity
                    Intent intent = new Intent(enterprise_home.this, enterprise_home.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_Auditors) {
                    // Handle Add Auditors click
                    // For example, redirect to an Add Auditors activity
                    Intent intent = new Intent(enterprise_home.this, Add_Auditors.class);
                    startActivity(intent);
                } else if (itemId == R.id.Projects) {
                    // Handle Projects/Services click
                    // For example, redirect to a Projects/Services activity
                    Intent intent = new Intent(enterprise_home.this, Add_Services.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_logout) {
                    // Handle Logout click
                    // For example, redirect to the login activity
                    Intent intent = new Intent(enterprise_home.this, enterprise_login.class);
                    startActivity(intent);
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
                    Intent intent = new Intent(enterprise_home.this, enterprise_home.class);
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
