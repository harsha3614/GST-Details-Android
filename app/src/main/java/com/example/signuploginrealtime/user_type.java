package com.example.signuploginrealtime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.signuploginrealtime.SignupActivity;
import com.example.signuploginrealtime.admin_login;

public class user_type extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        Button bt1 = findViewById(R.id.bt);
        Button bt2 = findViewById(R.id.bt1);
        Button bt3 = findViewById(R.id.bt2);
        Button bt4 = findViewById(R.id.bt3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the UserActivity when the USER button is clicked
                Intent intent = new Intent(user_type.this, admin_login.class);
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the AdminActivity when the ADMIN button is clicked
                Intent intent = new Intent(user_type.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the AdminActivity when the ADMIN button is clicked
                Intent intent = new Intent(user_type.this, enterprise_login_page.class);
                startActivity(intent);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the AdminActivity when the ADMIN button is clicked
//                Intent intent = new Intent(user_type.this, enterprise_login_page.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}