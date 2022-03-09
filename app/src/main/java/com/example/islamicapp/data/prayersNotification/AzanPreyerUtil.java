package com.example.islamicapp.data.prayersNotification;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class AzanPreyerUtil {
    public  static  void registerPrayer(Context context){

        WorkManager.getInstance(context.getApplicationContext()).cancelAllWork();
        PeriodicWorkRequest registerRequest = new PeriodicWorkRequest
                .Builder(RegisterPrayerTimesWorker.class,30, TimeUnit.DAYS)
                .addTag("REGISTER_PRAYER")
                .build();

        WorkManager.getInstance(context.getApplicationContext())
                .enqueueUniquePeriodicWork("REGISTER_PRAYER",
                        ExistingPeriodicWorkPolicy.REPLACE,registerRequest);
    }

}
