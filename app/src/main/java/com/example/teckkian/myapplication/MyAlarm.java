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
    String CMD;


    //the method will be fired when the alarm is triggerred
    @Override
    public void onReceive(Context context, Intent i) {



        CMD = i.getExtras().getString("CMD");
        Log.d("CMDTEST", CMD);
        getIPandPort();
        Socket_AsyncTask cmd_increase_servos = new Socket_AsyncTask();
        cmd_increase_servos.execute();


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
                Log.d("TESTING", "123");

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}