package com.example.signuploginrealtime;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class Upload_gst_act extends AppCompatActivity {

    private static final int PICK_PDF_REQUEST = 1;

    private ImageView pdfImageView;
    private Button uploadButton;
    private Uri pdfUri;
    private EditText titleEditText;

    private StorageReference pdfStorageReference;
    private DatabaseReference pdfDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        // Initialize Firebase storage and database references
        pdfStorageReference = FirebaseStorage.getInstance().getReference().child("pdfAct");
        pdfDatabaseReference = FirebaseDatabase.getInstance().getReference("pdfAct");
        titleEditText = findViewById(R.id.titleEditText);
        pdfImageView = findViewById(R.id.pdfImageView);
        uploadButton = findViewById(R.id.uploadButton);

        pdfImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPdf();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPdf();
            }
        });
    }

    private void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
        } catch (ActivityNotFoundException e) {
            // Handle the exception or inform the user
            Toast.makeText(this, "PDF reader not installed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pdfUri = data.getData();
            // Optionally, display the selected file's name or other information
            Toast.makeText(this, "Selected PDF: " + pdfUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();

            // Set the selected PDF image
            pdfImageView.setImageDrawable(getResources().getDrawable(R.drawable.place_holder)); // Set your selected image
        }
    }

    private void uploadPdf() {
        if (pdfUri != null) {
            StorageReference fileReference = pdfStorageReference.child(System.currentTimeMillis() + ".pdf");

            fileReference.putFile(pdfUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Save the PDF reference in the database
                                    savePdfReferenceToDatabase(Objects.requireNonNull(uri).toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload_gst_act.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please select a PDF file", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePdfReferenceToDatabase(String pdfUrl) {
        // Get the title from the user (you may use an EditText or any other UI component)
        String title = titleEditText.getText().toString();

        // Save the PDF reference with the title in the "pdf" table in the database
        String pdfId = pdfDatabaseReference.push().getKey();
        pdfDatabaseReference.child(title).setValue(new PdfItem(title, pdfUrl));

        Toast.makeText(Upload_gst_act.this, "PDF uploaded successfully", Toast.LENGTH_SHORT).show();
    }
}
