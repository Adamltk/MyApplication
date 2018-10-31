package com.example.teckkian.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class feedPetIns extends AppCompatActivity {


    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;
    String Date;



    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String CMD = "0";
    //UI Element
    Button btnUp;
    Button btnDown;
    Button btnLarge;

    Socket myAppSocket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_pet_ins);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date = dateFormat.format(calendar.getTime());



        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLarge = (Button) findViewById(R.id.large);


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInst();
                getIPandPort();
                CMD = "Up";
                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                cmd_increase_servo.execute();

            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInst();
                getIPandPort();
                CMD = "Down";
                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                cmd_increase_servo.execute();

            }
        });

        btnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedInst();
                getIPandPort();
                CMD = "L";
                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                cmd_increase_servo.execute();

            }
        });

    }

    public void getIPandPort() {
        String iPandPort = "192.168.1.8:21567";
        Log.d("MYTEST", "IP String: " + iPandPort);
        String temp[] = iPandPort.split(":");
        wifiModuleIp = temp[0];
        wifiModulePort = Integer.valueOf(temp[1]);
        Log.d("MY TEST", "IP:" + wifiModuleIp);
        Log.d("MY TEST", "PORT:" + wifiModulePort);
    }  
    public void feedInst() {

        String date = dateFormat.format(calendar.getTime());
        String time1 = timeFormat.format(calendar.getTime());
        String type = "feedInst";
        BackgroundRecTime backgroundRecTime = new BackgroundRecTime(this);
        backgroundRecTime.execute(type, date, time1);
    }
    public class Socket_AsyncTask extends AsyncTask<Void, Void, Void> {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress inetAddress = InetAddress.getByName(feedPetIns.wifiModuleIp);
                socket = new java.net.Socket(inetAddress, feedPetIns.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}
