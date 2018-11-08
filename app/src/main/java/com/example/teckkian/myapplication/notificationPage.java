package com.example.teckkian.myapplication;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.teckkian.myapplication.notification.CHANNEL_1_ID;

public class notificationPage extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    ProgressBar pg123;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);
        pg123 = findViewById(R.id.pg);
        notificationManager = NotificationManagerCompat.from(this);



        database.child("WeightR").child("Weight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                   //String value = noteDataSnapshot.getValue(String.class);
                    Integer value = noteDataSnapshot.getValue(Integer.class);
                   if(value < 10 ){

                       sendOnChannel1();
                       pg123.setProgress(0);
                   }
                    else if(value < 20){
                        sendOnChannel1();
                        pg123.setProgress(20);

                    }
                    else if(value <40){

                        pg123.setProgress(40);
                    } else if(value <60){

                        pg123.setProgress(60);
                    } else if(value <80){

                        pg123.setProgress(80);
                    } else if(value >100){

                        pg123.setProgress(100);
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }

    public void sendOnChannel1() {

      //  String title =  editTextTitle.getText().toString();
       // String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("The food is running out!!!")
                .setContentText("Add food please")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }


    public void sendOnChannel2() {


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("cvccvcv")
                .setContentText("xcvxcv")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
/**
    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }**/
}