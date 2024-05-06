package com.example.signuploginrealtime;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class videoslist extends AppCompatActivity {

    LinearLayout containerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        containerLayout = findViewById(R.id.containerLayout);

        showVideosData();
    }

    private void showVideosData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("videos");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot videoSnapshot : snapshot.getChildren()) {
                    Log.d("NewsListActivity", "videoSnapshot: " + videoSnapshot.toString());

                    final String title = videoSnapshot.child("title").getValue(String.class);
                    String youtubeLink = videoSnapshot.child("youtubeLink").getValue(String.class);
                    String imageUrl = videoSnapshot.child("imageUrl").getValue(String.class);

                    Log.d("NewsListActivity", "title: " + title);
                    Log.d("NewsListActivity", "youtubeLink: " + youtubeLink);
                    Log.d("NewsListActivity", "imageUrl: " + imageUrl);

                    // Inflate the CardView layout
                    View cardViewLayout = getLayoutInflater().inflate(R.layout.item_videos, null);

                    // Find Views inside the CardView layout
                    ImageView imageView = cardViewLayout.findViewById(R.id.imageViewNews);
                    TextView titleTextView = cardViewLayout.findViewById(R.id.textViewHeading);


                    // Load the image using Picasso
                    Picasso.get().load(imageUrl).into(imageView);

                    // Set title and YouTube link to TextViews
                    titleTextView.setText(title);
                    

                    // Set onClickListener for the CardView
                    cardViewLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Play the video within the app using a WebView
                            Intent intent = new Intent(videoslist.this, VideoPlayerActivity.class);
                            intent.putExtra("VIDEO_URL", youtubeLink);
                            startActivity(intent);
                        }
                    });

                    // Add the inflated CardView to the container layout
                    containerLayout.addView(cardViewLayout);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
                Log.e("NewsListActivity", "Error retrieving data: " + error.getMessage());
            }
        });
    }
}
