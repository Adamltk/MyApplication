package com.example.teckkian.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;


public class feedingRecPage extends AppCompatActivity {
    TextView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feeding_rec_page);

        clear = findViewById(R.id.textView9);
    }

    public void readRecords(View view){

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("http://192.168.1.41/new 1.php");

    }
    public void delete(View view){

        BackgroudDelete runner = new BackgroudDelete(feedingRecPage.this);
        runner.execute();

    }
}