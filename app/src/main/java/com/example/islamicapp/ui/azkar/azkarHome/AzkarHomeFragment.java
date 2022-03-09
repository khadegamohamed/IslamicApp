package com.example.islamicapp.ui.azkar.azkarHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;


public class AzkarHomeFragment extends Fragment {
private RecyclerView azkerTypes;
private AzkarTypesAdepter azkarTypesAdepter;
private AzkarTypesViewModel azkarTypesViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        azkarTypesAdepter = new AzkarTypesAdepter(this);
        azkarTypesViewModel = new AzkarTypesViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_azkar_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkerTypes = view.findViewById(R.id.azkar_types_list);
        azkerTypes.setAdapter(azkarTypesAdepter);
        azkerTypes.setLayoutManager(new LinearLayoutManager(getContext()));
        azkarTypesAdepter.setAzkarTypes(azkarTypesViewModel.getAzkarTypes(getContext()));
    }
}