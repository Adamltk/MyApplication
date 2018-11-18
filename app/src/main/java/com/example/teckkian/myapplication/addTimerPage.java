package com.example.teckkian.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class addTimerPage extends AppCompatActivity {


    TimePicker timePicker;
    Spinner mySpinner;
    String CMD;
    ToggleButton toggleButton;



    public final static String Shared_Prefs = "sharedPrefs";
    public final static String HOUR = "0";
    public final static String MIN = "0";
    public final static String toggle = "toggle";

    private int hour;
    private int min;
    private int spin;
    private boolean toggleOnOff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer_page);

        //getting the timepicker object
        timePicker = findViewById(R.id.timePicker);
        mySpinner = findViewById(R.id.spinner);
        toggleButton =  findViewById(R.id.alarmToggle);




        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this, R.array.amount, android.R.layout.simple_spinner_item);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        loadData();
        update();


    }



/**

    private void Alm(final DatabaseReference refAlm, final ToggleButton toggleButton ){
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                refAlm.setValue(isChecked);
            }
        });
        refAlm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean status_Alarm=(Boolean)dataSnapshot.getValue();
                toggleButton.setChecked(status_Alarm);
                if(status_Alarm) {
                    Log.d("MyActivity", "Alarm On");
                    Calendar calendar = Calendar.getInstance();
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                                timePicker.getHour(), timePicker.getMinute(), 0);
                    } else {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                                timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                    }
                    setAlarm(calendar.getTimeInMillis());
                    toggleButton.setTextOn("Switch Off");

                    saveData();

                }else {
                    toggleButton.setTextOff("Switch On");
                    cancelAlarm();

                    saveData();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });**/


    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            saveData();
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
            } else {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
            }
            setAlarm(calendar.getTimeInMillis());



        } else {
            saveData();
            cancelAlarm();

        }
    }

    public  void saveData(){

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Hour", timePicker.getHour());
        editor.putInt("Min", timePicker.getMinute());
        editor.putBoolean(toggle, toggleButton.isChecked());
        int selectedPosition = mySpinner.getSelectedItemPosition();
        editor.putInt("spinnerSelection", selectedPosition);

        hour = timePicker.getHour();
        min = timePicker.getMinute();

        editor.apply();
    }


    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE );
        hour = sharedPreferences.getInt("Hour", 0);
        min = sharedPreferences.getInt("Min", 0);
        spin = sharedPreferences.getInt("spinnerSelection",0);
        toggleOnOff = sharedPreferences.getBoolean(toggle, false);


    }

    public void update(){

        timePicker.setHour(hour);
        timePicker.setMinute(min);
        mySpinner.setSelection(spin);
        toggleButton.setChecked(toggleOnOff);
    }



    private void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);


        if (mySpinner.getSelectedItem().toString().equals("Small")) {
            CMD = "Up";
            i.putExtra("CMD",CMD);

        }

        else if (mySpinner.getSelectedItem().toString().equals("Medium")) {
            CMD = "Down";
            i.putExtra("CMD", CMD);

        }

        else if (mySpinner.getSelectedItem().toString().equals("Large")) {
            CMD = "L";
            i.putExtra("CMD", CMD);

        }

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);


        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Timer is set: " + hour + " : " + min, Toast.LENGTH_SHORT).show();

    }

    private void cancelAlarm() {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);



        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

       am.cancel(pi);
        Toast.makeText(this, "Timer is cancel", Toast.LENGTH_SHORT).show();

    }




}
