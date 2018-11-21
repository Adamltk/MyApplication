package com.example.teckkian.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class homePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Intent intent=new Intent(this, MyService.class);
        this.startService(intent);

        ImageView feedPet = findViewById(R.id.imageView6);
        feedPet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedPetIntent = new Intent(getApplicationContext(), feedPetIns.class);
                startActivity(feedPetIntent);
            }
        });

        ImageView timer = findViewById(R.id.imageView11);
        timer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedPetIntent = new Intent(getApplicationContext(), addTimerPage.class);
                startActivity(feedPetIntent);
            }
        });


        ImageView exit = findViewById(R.id.imageView5);
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), login.class);
                startActivity(logout);

            }
        });



        ImageView status = findViewById(R.id.imageView8);
        status.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewNot = new Intent(getApplicationContext(), statusPage.class);
                startActivity(viewNot);
            }
        });

        ImageView feedPetRec = findViewById(R.id.imageView7);
        feedPetRec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedRec = new Intent(getApplicationContext(), feedingRecPage.class);
                startActivity(feedRec);

            }
        });

        }





    }

