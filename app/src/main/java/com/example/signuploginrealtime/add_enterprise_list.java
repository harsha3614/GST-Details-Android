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

public class add_enterprise_list extends AppCompatActivity {

    EditText enterpriseNameEditText;
    EditText enterpriseTypeEditText;
    EditText registrationNumberEditText;
    EditText addressEditText;
    EditText emailAddressEditText;
    EditText passwordEditText;
    EditText phoneNumberEditText;
    EditText gstIdentificationNumberEditText;
    EditText taxRegistrationTypeEditText;
    EditText bankNameEditText;
    EditText accountNumberEditText;
    EditText ifscCodeEditText;
    EditText designationEditText;
    EditText natureOfBusinessEditText;
    EditText panCardEditText;
    EditText aadhaarCardEditText;

    Button uploadButton;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    DatabaseReference enterpriseListReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enterprise_list);

        // Initialize Firebase database reference
        enterpriseListReference = FirebaseDatabase.getInstance().getReference("enterprise_list");

        // Initialize all EditText fields
        enterpriseNameEditText = findViewById(R.id.Enterprise_Name);
        enterpriseTypeEditText = findViewById(R.id.Enterprise_Type);
        registrationNumberEditText = findViewById(R.id.Registration_Number);
        addressEditText = findViewById(R.id.Address);
        emailAddressEditText = findViewById(R.id.Email_Address);
        passwordEditText = findViewById(R.id.Password);
        phoneNumberEditText = findViewById(R.id.Phone_Number);
        gstIdentificationNumberEditText = findViewById(R.id.GST_Identification_Number);
        taxRegistrationTypeEditText = findViewById(R.id.Tax_Registration_Type);
        bankNameEditText = findViewById(R.id.Bank_Name);
        accountNumberEditText = findViewById(R.id.Account_Number);
        ifscCodeEditText = findViewById(R.id.IFSC_Codee);
        designationEditText = findViewById(R.id.Designation);
        natureOfBusinessEditText = findViewById(R.id.Nature_of_Business);
        panCardEditText = findViewById(R.id.PAN_Card);
        aadhaarCardEditText = findViewById(R.id.Aadhaar_Card);

        // Initialize the uploadButton
        uploadButton = findViewById(R.id.uploadButton);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Set click listener for the uploadButton
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform actions on button click
                uploadEnterpriseDetails();
            }
        });

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.dashboard) {
                    Intent intent = new Intent(add_enterprise_list.this, Admin_home_page.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_Enterprises) {
                    // Redirect to GstCalculationActivity when "Dashboard" is clicked
                    Intent intent = new Intent(add_enterprise_list.this, add_enterprise_list.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_News) {
                    Intent viewWrappersIntent = new Intent(add_enterprise_list.this, AddNews.class);

                    startActivity(viewWrappersIntent);

                } else if (itemId == R.id.Add_Videos) {
                    Intent viewWrappersIntent = new Intent(add_enterprise_list.this, upload_videos.class);

                    startActivity(viewWrappersIntent);
                } else if (itemId == R.id.Add_GST_Rates) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, Add.class);
//
//                    startActivity(viewWrappersIntent);
                }else if (itemId == R.id.menu_logout) {
                    Intent viewWrappersIntent = new Intent(add_enterprise_list.this, admin_login.class);

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
                    Intent intent = new Intent(add_enterprise_list.this, Admin_home_page.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);
//                    startActivity(viewWrappersIntent);

                }
            }
        });
    }

    private void uploadEnterpriseDetails() {
        // Get values from EditText fields
        String enterpriseName = enterpriseNameEditText.getText().toString().trim();
        String enterpriseType = enterpriseTypeEditText.getText().toString().trim();
        String registrationNumber = registrationNumberEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String emailAddress = emailAddressEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String gstIdentificationNumber = gstIdentificationNumberEditText.getText().toString().trim();
        String taxRegistrationType = taxRegistrationTypeEditText.getText().toString().trim();
        String bankName = bankNameEditText.getText().toString().trim();
        String accountNumber = accountNumberEditText.getText().toString().trim();
        String ifscCode = ifscCodeEditText.getText().toString().trim();
        String designation = designationEditText.getText().toString().trim();
        String natureOfBusiness = natureOfBusinessEditText.getText().toString().trim();
        String panCard = panCardEditText.getText().toString().trim();
        String aadhaarCard = aadhaarCardEditText.getText().toString().trim();

        // Create an Enterprise object with the details
        Enterprise enterprise = new Enterprise(
                enterpriseName, enterpriseType, registrationNumber, address,
                emailAddress, password, phoneNumber, gstIdentificationNumber,
                taxRegistrationType, bankName, accountNumber, ifscCode,
                designation, natureOfBusiness, panCard, aadhaarCard
        );

        // Upload the enterprise details to the Firebase Realtime Database
        enterpriseListReference.child(emailAddress).setValue(enterprise);

        Toast.makeText(add_enterprise_list.this, "Enterprise details uploaded successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
