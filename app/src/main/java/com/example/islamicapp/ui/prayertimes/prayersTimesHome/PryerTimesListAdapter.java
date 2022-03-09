package com.example.islamicapp.ui.prayertimes.prayersTimesHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.prayerTimes.PrayerTiming;

import java.util.ArrayList;

public class PryerTimesListAdapter extends RecyclerView.Adapter<PryerTimesListAdapter.ViewHolder> {
ArrayList<PrayerTiming> timimg;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_prayer,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.bind(timimg.get(position));
    }

    @Override
    public int getItemCount() {
        return timimg == null? 0 :timimg.size();
    }

    public void setTimimg(ArrayList<PrayerTiming> timimg) {
        this.timimg = timimg;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView prayerName,prayerTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerName = itemView.findViewById(R.id.prayer_name);
            prayerTime = itemView.findViewById(R.id.prayer_time);

        }

        public void bind(PrayerTiming timimg) {
            prayerName.setText(timimg.getPrayerName());
            prayerTime.setText(timimg.getPrayerTime());
        }
    }


}
