package com.example.teckkian.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button feedPetBtn = (Button)findViewById(R.id.feedPet);
        feedPetBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedPetIntent = new Intent(getApplicationContext(), feedPetPage.class);
                startActivity(feedPetIntent);
            }
        });



        Button logout = (Button)findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), login.class);
                startActivity(logout);

            }
        });


        Button notification = findViewById(R.id.viewNotBtn);
        notification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewNot = new Intent(getApplicationContext(), notificationPage.class);
                startActivity(viewNot);
            }
        });

        Button feedPetRec = findViewById(R.id.viewRecBtn);
        feedPetRec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedRec = new Intent(getApplicationContext(), feedingRecPage.class);
                startActivity(feedRec);

            }
        });

        }





    }

