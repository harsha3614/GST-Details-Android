package com.example.signuploginrealtime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class Add_Auditors extends AppCompatActivity {

    EditText fullNameEditText;
    EditText dateOfBirthEditText;
    EditText genderEditText;
    EditText contactNumberEditText;
    EditText emailAddressEditText;
    EditText confidentialAddressEditText;
    EditText auditorAINEditText;
    EditText membershipNumberEditText;
    EditText dateOfEnrolmentEditText;
    EditText auditorTypeEditText;
    EditText natureOfAuditEditText;
    EditText panCardEditText;
    EditText aadhaarCardEditText;

    Button uploadButton;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    DatabaseReference auditorListReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auditors);

        // Initialize Firebase database reference
        auditorListReference = FirebaseDatabase.getInstance().getReference("auditor_list");

        // Initialize all EditText fields
        fullNameEditText = findViewById(R.id.fullName);

        genderEditText = findViewById(R.id.gender);
        contactNumberEditText = findViewById(R.id.contactNumber);
        emailAddressEditText = findViewById(R.id.emailAddress);
        confidentialAddressEditText = findViewById(R.id.confidentialAddress);
        auditorAINEditText = findViewById(R.id.auditorAIN);
        membershipNumberEditText = findViewById(R.id.membershipNumber);

        auditorTypeEditText = findViewById(R.id.auditorType);
        natureOfAuditEditText = findViewById(R.id.natureOfAudit);
        panCardEditText = findViewById(R.id.panCard);
        aadhaarCardEditText = findViewById(R.id.aadhaarCard);
        dateOfBirthEditText = findViewById(R.id.dateOfBirth);
        dateOfEnrolmentEditText = findViewById(R.id.dateOfEnrolment);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Initialize the uploadButton
        uploadButton = findViewById(R.id.uploadButton);

        // Set click listener for the uploadButton
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform actions on button click
                uploadAuditorDetails();
            }
        });
// Set click listeners for date EditText fields
        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(dateOfBirthEditText);
            }
        });

        dateOfEnrolmentEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(dateOfEnrolmentEditText);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.dashboard) {
                    // Handle Dashboard click
                    // For example, redirect to a Dashboard activity
                    Intent intent = new Intent(Add_Auditors.this, enterprise_home.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_Auditors) {
                    // Handle Add Auditors click
                    // For example, redirect to an Add Auditors activity
                    Intent intent = new Intent(Add_Auditors.this, Add_Auditors.class);
                    startActivity(intent);
                } else if (itemId == R.id.Projects) {
                    // Handle Projects/Services click
                    // For example, redirect to a Projects/Services activity
                    Intent intent = new Intent(Add_Auditors.this, Add_Services.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_logout) {
                    // Handle Logout click
                    // For example, redirect to the login activity
                    Intent intent = new Intent(Add_Auditors.this, enterprise_login.class);
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
                    Intent intent = new Intent(Add_Auditors.this, enterprise_home.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);
//                    startActivity(viewWrappersIntent);

                }
            }
        });
    }

    // Method to show date picker dialog
    private void showDatePickerDialog(final EditText dateEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Auditors.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set the selected date in the EditText
                        dateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void uploadAuditorDetails() {
        // Get values from EditText fields
        String fullName = fullNameEditText.getText().toString().trim();
        String dateOfBirth = dateOfBirthEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();
        String emailAddress = emailAddressEditText.getText().toString().trim();
        String confidentialAddress = confidentialAddressEditText.getText().toString().trim();
        String auditorAIN = auditorAINEditText.getText().toString().trim();
        String membershipNumber = membershipNumberEditText.getText().toString().trim();
        String dateOfEnrolment = dateOfEnrolmentEditText.getText().toString().trim();
        String auditorType = auditorTypeEditText.getText().toString().trim();
        String natureOfAudit = natureOfAuditEditText.getText().toString().trim();
        String panCard = panCardEditText.getText().toString().trim();
        String aadhaarCard = aadhaarCardEditText.getText().toString().trim();

        // Create an Auditor object with the details
        Auditor auditor = new Auditor(
                fullName, dateOfBirth, gender, contactNumber, emailAddress, confidentialAddress,
                auditorAIN, membershipNumber, dateOfEnrolment, auditorType, natureOfAudit,
                panCard, aadhaarCard
        );

        // Upload the auditor details to the Firebase Realtime Database
        auditorListReference.child(fullName).setValue(auditor);

        Toast.makeText(this, "Auditor details uploaded successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
