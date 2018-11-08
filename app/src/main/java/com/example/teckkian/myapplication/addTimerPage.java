package com.example.teckkian.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
    Spinner mySpinner;
    String CMD,CMD2,CMD3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer_page);

        //getting the timepicker object
        timePicker =  findViewById(R.id.timePicker);
        mySpinner =  findViewById(R.id.spinner);


       // ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(addTimerPage.this,
         //       android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.amount));

        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this, R.array.amount, android.R.layout.simple_spinner_item);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm:ss");


/**
        else if (mySpinner.getSelectedItem().toString().equals("Medium")) {
            CMD2 = "Down";
            Intent intent = new Intent("my.action.string.RUN");
            intent.putExtra("CMD", CMD2);
            sendBroadcast(intent);

        }

        else if (mySpinner.getSelectedItem().toString().equals("Large")) {
            CMD3 = "L";
            Intent intent = new Intent("my.action.string.RUN");
            intent.putExtra("CMD",CMD3);
            sendBroadcast(intent);

        }
**/
/**
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    CMD = "Up";
                    Intent intent1 = new Intent(addTimerPage.this, MyAlarm.class);
                    intent1.putExtra("CMD", CMD);
                    startActivity(intent1);

                } else if (i == 2) {
                    CMD = "Down";
                    Intent intent2 = new Intent(addTimerPage.this, MyAlarm.class);
                    intent2.putExtra("CMD", CMD);
                    startActivity(intent2);
                }else if (i == 3) {
                    CMD = "L";
                    Intent intent3 = new Intent(addTimerPage.this, MyAlarm.class);
                    intent3.putExtra("CMD", CMD);
                    startActivity(intent3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

**/




        //attaching clicklistener on button
        findViewById(R.id.buttonAlarm).setOnClickListener(new View.OnClickListener() {

            /**       if (mySpinner.getSelectedItem().toString().equals("Small")) {
             CMD = "Up";
             Intent intent1 = new Intent("my.action.string");
             intent1.putExtra("CMD", CMD);
             sendBroadcast(intent1);

             }

             else if (mySpinner.getSelectedItem().toString().equals("Medium")) {
             CMD = "Down";
             Intent intent2 = new Intent("my.action.string");
             intent2.putExtra("CMD", CMD);
             sendBroadcast(intent2);

             }

             else if (mySpinner.getSelectedItem().toString().equals("Large")) {
             CMD = "L";
             Intent intent3 = new Intent("my.action.string");
             intent3.putExtra("CMD", CMD);
             sendBroadcast(intent3);

             }
             **/
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
        Toast.makeText(this, "Timer is set", Toast.LENGTH_SHORT).show();


            CMD = "Up";
            Intent intent = new Intent("my.action.string.RUN");
            intent.putExtra("CMD", CMD);
            sendBroadcast(intent);


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
        Toast.makeText(this, "Timer is cancel", Toast.LENGTH_SHORT).show();

    }




}