package com.example.islamicapp.ui.prayertimes.prayersTimesHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.prayerTimes.City;

import java.util.ArrayList;

public class CitiesAdepter extends ArrayAdapter<City> {
    private ArrayList<City> cites;

    public CitiesAdepter(@NonNull Context context,  ArrayList<City> cites) {
        super(context, 0,cites);
        this.cites = cites;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Nullable
    @Override
    public City getItem(int position) {
        return cites.get(position);
    }
    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_list_item,parent,false);
            TextView cityName =convertView.findViewById(R.id.city);
            TextView countryCode=convertView.findViewById(R.id.country_code);
            City city = cites.get(position);
            cityName.setText(city.getName());
            countryCode.setText(city.getCode());
        }
        return convertView;
    }
}
