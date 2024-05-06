package com.example.signuploginrealtime;



import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Services extends AppCompatActivity {

    EditText serviceNameEditText;
    EditText descriptionEditText;
    EditText serviceChargeEditText;
    EditText documentsListEditText;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    Button addServiceButton;

    DatabaseReference servicesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        // Initialize Firebase database reference
        servicesReference = FirebaseDatabase.getInstance().getReference("services");

        // Initialize all EditText fields
        serviceNameEditText = findViewById(R.id.editTextServiceName);
        descriptionEditText = findViewById(R.id.editTextDescription);
        serviceChargeEditText = findViewById(R.id.editTextServiceCharge);
        documentsListEditText = findViewById(R.id.editTextDocumentsList);

        // Initialize the addServiceButton
        addServiceButton = findViewById(R.id.buttonAddService);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.dashboard) {
                    // Handle Dashboard click
                    // For example, redirect to a Dashboard activity
                    Intent intent = new Intent(Add_Services.this, enterprise_home.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_Auditors) {
                    // Handle Add Auditors click
                    // For example, redirect to an Add Auditors activity
                    Intent intent = new Intent(Add_Services.this, Add_Auditors.class);
                    startActivity(intent);
                } else if (itemId == R.id.Projects) {
                    // Handle Projects/Services click
                    // For example, redirect to a Projects/Services activity
                    Intent intent = new Intent(Add_Services.this, Add_Services.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_logout) {
                    // Handle Logout click
                    // For example, redirect to the login activity
                    Intent intent = new Intent(Add_Services.this, enterprise_login.class);
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
                    Intent intent = new Intent(Add_Services.this, enterprise_home.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);
//                    startActivity(viewWrappersIntent);

                }
            }
        });

        // Set click listener for the addServiceButton
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform actions on button click
                addServiceToDatabase();
            }
        });
    }

    private void addServiceToDatabase() {
        // Get values from EditText fields
        String serviceName = serviceNameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String serviceChargeStr = serviceChargeEditText.getText().toString().trim();
        String documentsList = documentsListEditText.getText().toString().trim();

        if (serviceName.isEmpty() || description.isEmpty() || serviceChargeStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert serviceCharge to a double
        double serviceCharge = Double.parseDouble(serviceChargeStr);

        // Create a Service object with the details
        Service service = new Service(serviceName, description, serviceCharge, documentsList);

        // Upload the service details to the Firebase Realtime Database
        servicesReference.child(serviceName).setValue(service);

        Toast.makeText(Add_Services.this, "Service added successfully", Toast.LENGTH_SHORT).show();

        // Optionally, you can finish the activity or navigate to another screen
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
