package com.example.teckkian.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class BackgroudDelete  extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    String userid;
    BackgroudDelete(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params){
        String deleteuser_url = "http://192.168.1.41/delete.php";
        try {
            URL url = new URL(deleteuser_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!=null) {
                result += line;
            }
            bufferedReader.close();;
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Delete User");
    }
    @Override
    protected void onPostExecute(String result){
        if(result.toString().equals("Delete Successfully")) {

            alertDialog.setMessage(result);
            alertDialog.show();

        }
    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

}