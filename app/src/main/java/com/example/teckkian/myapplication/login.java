package com.example.teckkian.myapplication;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.teckkian.myapplication.notification.CHANNEL_1_ID;


public class login extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        EditText EmailEt, PasswordEt;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            notificationManager = NotificationManagerCompat.from(this);



            EmailEt = (EditText)findViewById(R.id.email);
            PasswordEt = (EditText)findViewById(R.id.password);


            database.child("WeightR").child("Weight").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        //String value = noteDataSnapshot.getValue(String.class);
                        Integer value = noteDataSnapshot.getValue(Integer.class);
                         if(value <20){
                            sendOnChannel1();
                        }
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


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





    public void sendOnChannel1() {

        //  String title =  editTextTitle.getText().toString();
        // String message = editTextMessage.getText().toString();
        Intent landingIntent = new Intent(this, notificationPage.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("asas")
                .setContentText("asssssss")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .setContentIntent(landingPendingIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
    }


    public void sendOnChannel2() {
        Intent landingIntent = new Intent(this, notificationPage.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("cvccvcv")
                .setContentText("xcvxcv")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(landingPendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);
    }
    }

