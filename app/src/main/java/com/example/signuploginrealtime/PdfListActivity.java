// PdfListActivity.java
package com.example.signuploginrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PdfListActivity extends AppCompatActivity {

    private ListView pdfListView;
    private List<PdfItem> pdfList;
    private PdfAdapter pdfAdapter; // Custom adapter for displaying both title and file

    private DatabaseReference pdfDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_list);

        // Initialize Firebase database reference
        pdfDatabaseReference = FirebaseDatabase.getInstance().getReference("pdf");

        pdfListView = findViewById(R.id.pdfListView);
        pdfList = new ArrayList<>();
        pdfAdapter = new PdfAdapter(this, R.layout.pdf_list_item, pdfList);
        pdfListView.setAdapter(pdfAdapter);

        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PdfItem selectedPdf = pdfList.get(position);

            }
        });

        // Retrieve PDFs from the database
        retrievePdfList();
    }

    private void retrievePdfList() {
        pdfDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pdfList.clear(); // Clear existing data
                for (DataSnapshot pdfSnapshot : dataSnapshot.getChildren()) {
                    try {
                        // Use getValue with PdfItem class
                        PdfItem pdfItem = pdfSnapshot.getValue(PdfItem.class);
                        if (pdfItem != null) {
                            pdfList.add(pdfItem); // Add PdfItem to the list
                        }
                    } catch (DatabaseException e) {
                        // Log the exception if there is an error in data conversion
                        Log.e("PdfListActivity", "Error converting data: " + e.getMessage());
                    }
                }

                // Notify the adapter about the data set change
                pdfAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors if needed
            }
        });
    }

    private void displayPdf(String pdfUrl) {
        // In your main activity or wherever appropriate

    }
}
