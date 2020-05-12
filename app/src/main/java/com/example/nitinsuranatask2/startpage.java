package com.example.nitinsuranatask2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class startpage extends AppCompatActivity {
private Handler handler;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        {
            NotificationChannel channel=new NotificationChannel("mynotification","mynotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("genral")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "succesful";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }

                      //  Toast.makeText(startpage.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        if(checkInternet()) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //  if(checkInternet())
                    Intent intent = new Intent(startpage.this, MainActivity.class);


                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
        else
            Toast.makeText(startpage.this,"Please turn on your internet and then  start app",Toast.LENGTH_LONG).show();

    }
    private boolean checkInternet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;

    }
}
