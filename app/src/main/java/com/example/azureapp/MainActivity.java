package com.example.azureapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.azureapp.databinding.ActivityMainBinding;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    NotificationManager manager;
    NotificationChannel channel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_subscribe, R.id.navigation_resource, R.id.navigation_notifications,R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        channel = new NotificationChannel("1", "不知道这个是啥", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);
        sendAlerts();




    }

    private void sendAlerts() {

        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        String channelId = "007";
                        String channelName = "James Bond";
                        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.createNotificationChannel(notificationChannel);
                        Notification notification = new NotificationCompat.Builder(MainActivity.this, "007")
                                .setContentTitle("Alert")
                                .setContentText("You’ve used 90% of your Azure for Students credit")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.icon_alert)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_alert))
                                .setAutoCancel(true)

                                .build();
                        notificationManager.notify(1, notification);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }


}