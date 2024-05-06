package com.example.signuploginrealtime;

// MainActivity.java
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.widget.TextView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class display_services extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_services);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("enterprise_list");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Call the function to set up FirebaseRecyclerAdapter
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Enterprise> options =
                new FirebaseRecyclerOptions.Builder<Enterprise>()
                        .setQuery(databaseReference, Enterprise.class)
                        .build();

        FirebaseRecyclerAdapter<Enterprise, EnterpriseViewHolder> adapter =
                new FirebaseRecyclerAdapter<Enterprise, EnterpriseViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull EnterpriseViewHolder holder, int position, @NonNull Enterprise model) {
                        holder.setEnterpriseName(model.getEnterpriseName());
                        holder.setEnterpriseType(model.getEnterpriseType());
                    }

                    @NonNull
                    @Override
                    public EnterpriseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_enterprise, parent, false);
                        return new EnterpriseViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class EnterpriseViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public EnterpriseViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setEnterpriseName(String enterpriseName) {
            TextView textViewEnterpriseName = mView.findViewById(R.id.textViewEnterpriseName);
            textViewEnterpriseName.setText(enterpriseName);
        }

        public void setEnterpriseType(String enterpriseType) {
            TextView textViewEnterpriseType = mView.findViewById(R.id.textViewEnterpriseType);
            textViewEnterpriseType.setText(enterpriseType);
        }
    }
}
