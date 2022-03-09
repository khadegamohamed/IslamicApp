package com.example.islamicapp.ui.prayertimes.prayersTimesHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.prayerMethods.PrayerTimingMethods;
import com.example.islamicapp.data.pojo.prayerTimes.City;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerAPIResponse;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerTiming;
import com.example.islamicapp.data.pojo.prayerTimes.Timings;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesFragment extends Fragment {
    private static final String TAG= "PrayerTimesFragment";
    private RecyclerView times;
    private PryerTimesListAdapter adepter;
    private PrayerTimesViewModel viewModel;
    private DatePicker datePicker;
    private Spinner citiesSpinner,prayerMethodsSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PrayerTimesViewModel.class);
        return inflater.inflate(R.layout.fragment_prayer_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         initView(view);
        setUpAdepter();
        initDataPicker();
      initClikLisener();
      setUpObserve();

    }

    private void initView(View view){
        times= view.findViewById(R.id.Prayer_List);
        datePicker = view.findViewById(R.id.data_picker);
        citiesSpinner= view.findViewById(R.id.city);
        prayerMethodsSpinner =  view.findViewById(R.id.method);
    }
    private void setUpAdepter(){
        citiesSpinner.setAdapter(new CitiesAdepter(getContext(),viewModel.getCities(getContext())));
        adepter =new PryerTimesListAdapter();
        times.setAdapter(adepter);
    }
    private void initDataPicker(){
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        viewModel.setGetPrayerTiming(getContext(),
                                viewModel.getCurrentCity().getValue(),
                                viewModel.getCurrentPrayerCalculatedMethod().getValue()
                                ,dayOfMonth,monthOfYear,year);
                    }
                });
    }
    private void initClikLisener(){
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setCurrentryCity(position,getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setUpObserve(){
        viewModel.getGetPrayerTiming().observe(getViewLifecycleOwner(), new Observer<ArrayList<PrayerTiming>>() {
            @Override
            public void onChanged(ArrayList<PrayerTiming> prayerTimings) {
                adepter.setTimimg(prayerTimings);
            }
        });
        viewModel.getCurrentCity().observe(getViewLifecycleOwner(), new Observer<City>() {
            @Override
            public void onChanged(City city) {
                viewModel.setGetPrayerTiming(getContext(),city,
                        viewModel.getCurrentPrayerCalculatedMethod().getValue(),
                        datePicker.getMonth(),
                        datePicker.getYear(),
                        datePicker.getYear());
            }
        });
        viewModel.getPrayerTimingMethods().observe(getViewLifecycleOwner(), new Observer<PrayerTimingMethods>() {
            @Override
            public void onChanged(PrayerTimingMethods prayerTimingMethods) {
                prayerMethodsSpinner.setAdapter(new PrayerMethodsAdepter(requireContext(),
                        prayerTimingMethods.getMethods()));
            }
        });

    }

}