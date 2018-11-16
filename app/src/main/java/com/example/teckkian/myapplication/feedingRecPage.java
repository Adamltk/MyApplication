package com.example.teckkian.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;


public class feedingRecPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feeding_rec_page);


    }

    public void readRecords(View view){

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("http://192.168.1.41/new 1.php");
       /*Cursor r= db.getData(1);
        TextView display=(TextView)findViewById(R.id.showData);
        String show= r.getString(r.getColumnIndex(DatabaseHandler.TEMP_READINGS_VALUE));
        display.setText(show);
    */}
}