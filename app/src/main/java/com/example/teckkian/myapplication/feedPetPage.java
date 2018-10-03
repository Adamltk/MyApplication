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
    Button feedInBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_pet_page);

        feedInBtn = (Button)findViewById(R.id.feedInstBtn);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = dateFormat.format(calendar.getTime());


         }

    public void feedInst(View view) {

        String date = dateFormat.format(calendar.getTime());
        String type = "feedInst";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, date);
    }


         }



