package com.example.teckkian.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addTimerPage extends AppCompatActivity {

    //the timepicker object
    TimePicker timePicker;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer_page);

        //getting the timepicker object
        timePicker = (TimePicker) findViewById(R.id.timePicker);



        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm:ss");




        //attaching clicklistener on button
        findViewById(R.id.buttonAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm
                Calendar calendar = Calendar.getInstance();
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }


                setAlarm(calendar.getTimeInMillis());
            }
        });

        final Button cancelAlarm = findViewById(R.id.buttonCancel);
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });


    }

    private void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();

        String date = dateFormat.format(calendar.getTime());
        String time1 = timeFormat.format(calendar.getTime());
        String type = "feedInst";
        BackgroundRecTime backgroundRecTime = new BackgroundRecTime(this);
        backgroundRecTime.execute(type, date, time1);
    }

    private void cancelAlarm() {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

       am.cancel(pi);
        Toast.makeText(this, "Alarm is cancel", Toast.LENGTH_SHORT).show();

    }




}