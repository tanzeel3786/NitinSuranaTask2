package com.example.nitinsuranatask2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }
    public void showNotification(String title,String message)
    {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"mynotification").setContentTitle(title).setSmallIcon(R.drawable.ic_launcher_background).setAutoCancel(true).setContentText(message);
        if(title.equals("message"))
        {
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, Notificationspage.class), PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);


            // Gets an instance of the NotificationManager service
            NotificationManager mNotificationManager =
                    (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


            // Builds the notification and issues it.
            mNotificationManager.notify(0, builder.build());
        }


    }
}
