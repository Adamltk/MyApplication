package com.example.teckkian.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.teckkian.myapplication.notification.CHANNEL_1_ID;
import static com.example.teckkian.myapplication.notification.CHANNEL_2_ID;

public class MyService extends Service {
    public MyService() {

    }
    NotificationManagerCompat notificationManager;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = NotificationManagerCompat.from(this);

        database.child("WeightR").child("Weight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    //String value = noteDataSnapshot.getValue(String.class);
                    Integer value = noteDataSnapshot.getValue(Integer.class);

                    if(value < 10 ){

                        sendOnChannel1();
                    }

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        database.child("WaterL").child("Water").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    //String value = noteDataSnapshot.getValue(String.class);
                    Integer value = noteDataSnapshot.getValue(Integer.class);




                    if (value <= 100) {

                        sendOnChannel2();

                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    public void sendOnChannel1() {


        Intent landingIntent = new Intent(this, statusPage.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("The food is running out!!!")
                .setContentText("Please add food.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(landingPendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);
    }



    public void sendOnChannel2() {
        Intent landingIntent = new Intent(this, statusPage.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle("Low water level")
                .setContentText("Please add water.")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(landingPendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(2, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }
}
