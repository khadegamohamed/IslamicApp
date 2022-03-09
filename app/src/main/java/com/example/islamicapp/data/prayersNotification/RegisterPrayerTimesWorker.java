package com.example.islamicapp.data.prayersNotification;

import android.content.Context;
import android.hardware.lights.LightState;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.islamicapp.data.networking.PrayersRetrofit;
import com.example.islamicapp.data.pojo.prayerTimes.Datum;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerAPIResponse;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerTiming;
import com.example.islamicapp.data.pojo.prayerTimes.Timings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimesWorker extends Worker {
    public RegisterPrayerTimesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            PrayersPrefrences prayersPrefrences= new PrayersPrefrences(getApplicationContext());
            String city = prayersPrefrences.getCity();
            String country= prayersPrefrences.getCountry();
            int  method= prayersPrefrences.getMethod();
            Response<PrayerAPIResponse> timeResponse=  PrayersRetrofit
                    .getApi().getPrayers(city,country,method,month,year).execute();
            if (timeResponse.isSuccessful()){
                List<Datum> data = timeResponse.body().getData();
     for (int i=0;i<=data.size();i++){
         int day = i+1;
             Datum datum = data.get(i);
             Timings timings = datum.getTimings();
             ArrayList<PrayerTiming> prayerTimings = convertFromTiming(timings);
             prayerTimings.forEach(prayerTiming -> {
                 String prayerTag=""+year+"/"+month+"/"+day+" "+prayerTiming.getPrayerName();
                 Data input = new Data.Builder()
                         .putString(AzanNotificationConstants.NOTIFICATION_TITILE_KEY,prayerTiming.getPrayerName())
                         .putString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY,"حي علي الصلاه")
                         .build();
                 OneTimeWorkRequest rigsterPreayerRequest=
                         new OneTimeWorkRequest.Builder(AzanNotificationWorker.class)
                                 .addTag(prayerTag)
                                 .setInitialDelay(calculatePrayerDeley(year,month,day,prayerTiming), TimeUnit.MILLISECONDS)
                                 .setInputData(input)
                                 .build();
                 WorkManager.getInstance(getApplicationContext()).enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE,rigsterPreayerRequest);

             });
     }
                return Result.success();
            }else {
                return Result.failure();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }

    }

    private long calculatePrayerDeley(int year,int month,int day,PrayerTiming prayerTiming) {
        String pattern =  "yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = ""+year+"/"+decimalFormat.format(month)+"/"+decimalFormat.format(day)+" "+time;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date = format.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            return Math.abs(date.getTime()-currentTime);

        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    ArrayList<PrayerTiming> convertFromTiming(Timings timings){
        ArrayList<PrayerTiming> res = new ArrayList<>();
        res.add(new PrayerTiming("Fajer",timings.getFajr()));
        res.add(new PrayerTiming("Duuhr",timings.getDhuhr()));
        res.add(new PrayerTiming("Asr",timings.getAsr()));
        res.add(new PrayerTiming("Maghrib",timings.getMaghrib()));
        res.add(new PrayerTiming("Isha",timings.getIsha()));
        return res;
    }



}
