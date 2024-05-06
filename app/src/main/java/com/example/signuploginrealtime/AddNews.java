package com.example.signuploginrealtime;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddNews extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private ImageView wrapperImageView;
    private Button uploadButton;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference wrappersReference;
    private String shopName; // Ensure this is initialized before using

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Initialize Firebase storage and database references
        storageReference = FirebaseStorage.getInstance().getReference().child("News");
        wrappersReference = FirebaseDatabase.getInstance().getReference("News");

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        wrapperImageView = findViewById(R.id.wrapperImageView);
        uploadButton = findViewById(R.id.uploadButton);

        wrapperImageView.setOnClickListener(v -> openFileChooser());
        uploadButton.setOnClickListener(v -> uploadWrapper());

        // Get the shopName from your intent or wherever it is set
        Intent intent = getIntent();
        if (intent != null) {
            shopName = intent.getStringExtra("SHOP_NAME");
        }

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.dashboard) {
                    Intent intent = new Intent(AddNews.this, Admin_home_page.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_Enterprises) {
                    // Redirect to GstCalculationActivity when "Dashboard" is clicked
                    Intent intent = new Intent(AddNews.this, add_enterprise_list.class);
                    startActivity(intent);
                } else if (itemId == R.id.Add_News) {
                    Intent viewWrappersIntent = new Intent(AddNews.this, AddNews.class);

                    startActivity(viewWrappersIntent);

                } else if (itemId == R.id.Add_Videos) {
                    Intent viewWrappersIntent = new Intent(AddNews.this, upload_videos.class);

                    startActivity(viewWrappersIntent);
                } else if (itemId == R.id.Add_GST_Rates) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, Add.class);
//
//                    startActivity(viewWrappersIntent);
                } else if (itemId == R.id.menu_logout) {
                    Intent viewWrappersIntent = new Intent(AddNews.this, admin_login.class);

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
                    Intent intent = new Intent(AddNews.this, Admin_home_page.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile) {
//                    Intent viewWrappersIntent = new Intent(Admin_home_page.this, AddNews.class);
//                    startActivity(viewWrappersIntent);

                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadWrapper() {
        // Get title and description
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (imageUri != null) {
            StorageReference fileReference = storageReference.child("News").child(title + System.currentTimeMillis() + ".jpg");

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Create a new News object with title, description, and imageUrl
                            News news = new News(title, description, uri.toString());

                            // Add the news to the database under the title
                            DatabaseReference newWrapperRef = wrappersReference.child(title);
                            newWrapperRef.setValue(news);

                            Toast.makeText(AddNews.this, "News added successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddNews.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            wrapperImageView.setImageURI(imageUri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
