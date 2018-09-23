package com.example.teckkian.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class login extends AppCompatActivity {


        EditText EmailEt, PasswordEt;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            EmailEt = (EditText)findViewById(R.id.email);
            PasswordEt = (EditText)findViewById(R.id.password);
        }

        public void OnLogin(View view) {
            String username = EmailEt.getText().toString();
            String password = PasswordEt.getText().toString();
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
        }

    public void OpenReg(View view)
    {
        Intent regIntent = new Intent(this, signUp.class);
        startActivity(regIntent);
    }

    }

