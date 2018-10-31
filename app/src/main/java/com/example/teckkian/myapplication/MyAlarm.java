package com.example.teckkian.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


//class extending the Broadcast Receiver
public class MyAlarm extends BroadcastReceiver {

    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;
    String Date;

    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String CMD = "0";



    //the method will be fired when the alarm is triggerred
    @Override
    public void onReceive(Context context, Intent intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarmBelal", "Alarm just fired");


        getIPandPort();
        CMD = "Up";
        Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
        cmd_increase_servo.execute();

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