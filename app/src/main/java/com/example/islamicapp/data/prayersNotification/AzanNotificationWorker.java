package com.example.islamicapp.data.prayersNotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.islamicapp.R;

public class AzanNotificationWorker extends Worker {
    public AzanNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private static final String CHANNEL_ID= "AZAN_CHANNEL";
    private static final String CHANNEL_NAME= "azanchannel";
    NotificationManager notificationManager = (NotificationManager) getApplicationContext()
            .getSystemService(Context.NOTIFICATION_SERVICE);
    private void sendNotification(String title, String contant, Uri sound){
        NotificationCompat.Builder  notificationBuilder= creatNotificationBuilder(title,contant,sound);
      creatNotificationChannel(notificationManager,sound);
        notificationManager.notify(0,notificationBuilder.build());
    }

    private NotificationCompat.Builder creatNotificationBuilder(String title, String contant, Uri sound) {
        NotificationCompat.Builder  notificationBuilder= new NotificationCompat.Builder(
                getApplicationContext(),CHANNEL_ID);
        notificationBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(contant)
                .setSound(sound)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        return notificationBuilder;
    }

    private void creatNotificationChannel(NotificationManager notificationManager, Uri sound) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            AudioAttributes audioAttributes= new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION )
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            notificationChannel.setSound(sound,audioAttributes);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        Data input= getInputData();
        Uri azanSound = Uri.parse("android.resource://" +
                getApplicationContext().getPackageName() + "/" + R.raw.azan);
        String title= input.getString(AzanNotificationConstants.NOTIFICATION_TITILE_KEY);
        String content= input.getString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY);
        sendNotification(title,content,azanSound);
        return Result.success();
    }
}
