package com.example.islamicapp.data.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrayersRetrofit {
    private static Retrofit instance;
    private static  PrayerAPI api;
    private static final String BASE_URL= "http://api.aladhan.com/v1/";
    public static Retrofit getInstance(){
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
    public static PrayerAPI getApi(){
        if (api == null){
            api = getInstance().create(PrayerAPI.class);
        }
        return api;
    }








}
