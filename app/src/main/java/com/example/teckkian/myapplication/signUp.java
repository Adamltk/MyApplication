package com.example.teckkian.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class signUp extends AppCompatActivity {

    EditText name1,user_name1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name1 = (EditText)findViewById(R.id.email2);
        user_name1 = (EditText)findViewById(R.id.name2);
        password1 = (EditText)findViewById(R.id.password2);




    }

    public void OnReg(View view) {
        String name = name1.getText().toString();
        String user_name = user_name1.getText().toString();
        String password = password1.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name,user_name, password);
    }

}
