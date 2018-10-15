package com.example.teckkian.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class feedPetPage extends AppCompatActivity {
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String Date;
    Button addTimer1Btn, feedBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_pet_page);




        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = dateFormat.format(calendar.getTime());


        addTimer1Btn = findViewById(R.id.addTimerBtn);
        addTimer1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), addTimerPage.class);
                startActivity(logout);

            }
        });





         }

    public void feedPPet(View view) {
        Intent feedPetInst = new Intent(getApplicationContext(), feedPetIns.class);
        startActivity(feedPetInst);

    }

         }



