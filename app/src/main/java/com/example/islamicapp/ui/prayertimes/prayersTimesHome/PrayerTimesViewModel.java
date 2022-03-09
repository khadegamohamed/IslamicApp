package com.example.islamicapp.ui.prayertimes.prayersTimesHome;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamicapp.data.citiesProvider.CitiesProvider;
import com.example.islamicapp.data.networking.PrayersRetrofit;
import com.example.islamicapp.data.pojo.prayerMethods.PrayerTimesMethodsResponse;
import com.example.islamicapp.data.pojo.prayerMethods.PrayerTimingMethods;
import com.example.islamicapp.data.pojo.prayerTimes.City;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerAPIResponse;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerTiming;
import com.example.islamicapp.data.pojo.prayerTimes.Timings;
import com.example.islamicapp.data.prayersNotification.AzanPreyerUtil;
import com.example.islamicapp.data.prayersNotification.PrayersPrefrences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends ViewModel {
    private static final String TAG= "PrayerTimesFragment";
Call<PrayerAPIResponse> getPrayers(String country,
                                   String city,
                                   int method,
                                   int month,
                                   int year){
    return PrayersRetrofit.getApi().getPrayers(city,country,method,month,year);
}

    public MutableLiveData<City> currentCity;

    private MutableLiveData<PrayerTimingMethods> prayerTimingMethods;
    private MutableLiveData<Integer> currentPrayerCalculatedMethod;
private MutableLiveData<ArrayList<PrayerTiming>> getPrayerTiming;

    public MutableLiveData<PrayerTimingMethods> getPrayerTimingMethods() {
        return prayerTimingMethods;
    }

    public MutableLiveData<Integer> getCurrentPrayerCalculatedMethod() {
        return currentPrayerCalculatedMethod;
    }

    public MutableLiveData<ArrayList<PrayerTiming>> getGetPrayerTiming() {
        return getPrayerTiming;
    }

    public PrayerTimesViewModel() {
        getPrayerTiming = new MutableLiveData<>();
        currentCity = new MutableLiveData<>();
        prayerTimingMethods = new MutableLiveData<>();
        currentPrayerCalculatedMethod = new MutableLiveData<>(5);

       setPrayerTimingMethods();
    }
    Call<PrayerTimesMethodsResponse> getPrayerMethod(){
        return PrayersRetrofit.getApi().getPrayersMethods();
    }
    private void  setPrayerTimingMethods(){
getPrayerMethod().enqueue(new Callback<PrayerTimesMethodsResponse>() {
    @Override
    public void onResponse(Call<PrayerTimesMethodsResponse> call, Response<PrayerTimesMethodsResponse> response) {
    prayerTimingMethods.setValue(new PrayerTimingMethods(response.body().getData()));
    }

    @Override
    public void onFailure(Call<PrayerTimesMethodsResponse> call, Throwable t) {
        Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
    }
});

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
public ArrayList<City> getCities(Context context){
        return CitiesProvider.getCities(context);
}
public void setCurrentryCity(int position,Context context){
        currentCity.setValue(getCities(context).get(position));
}

    public MutableLiveData<City> getCurrentCity() {
        return currentCity;
    }

    public void setGetPrayerTiming(Context context,City city, int method,int day, int monthOfYear, int year){
        getPrayers(city.getCode(), city.getName(),method,monthOfYear,year).enqueue(new Callback<PrayerAPIResponse>() {
         @Override
         public void onResponse(Call<PrayerAPIResponse> call, Response<PrayerAPIResponse> response) {
           Timings timings = response.body().getData().get(day-1).getTimings();
           ArrayList<PrayerTiming> prayerTimings = convertFromTiming(timings);
           getPrayerTiming.setValue(prayerTimings);
           PrayersPrefrences prayersPrefrences = new PrayersPrefrences(context);
           prayersPrefrences.setCity(city.getName());
           prayersPrefrences.setCountry(city.getCode());
           prayersPrefrences.setMethod(method);
             AzanPreyerUtil.registerPrayer(context);
         }

         @Override
         public void onFailure(Call<PrayerAPIResponse> call, Throwable t) {
             Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
         }
     });
 }




}
