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
import com.example.islamicapp.data.pojo.prayerMethods.PrayerTimingMethod;

import java.util.ArrayList;

public class PrayerMethodsAdepter extends ArrayAdapter<PrayerTimingMethod> {
    public PrayerMethodsAdepter(@NonNull Context context,  ArrayList<PrayerTimingMethod> methods) {
        super(context, 0,methods);
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

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
if (convertView==null){
    convertView = LayoutInflater.from(getContext())
            .inflate(R.layout.list_item_prayer_methods,
                    parent,false);
    PrayerTimingMethod method =getItem(position);
    TextView methodId = convertView.findViewById(R.id.prayer_method_id);
    TextView methodName = convertView.findViewById(R.id.prayer_method_name);
    methodId.setText(method.getId());
    methodName.setText(method.getName());
}
return convertView;
    }
}
