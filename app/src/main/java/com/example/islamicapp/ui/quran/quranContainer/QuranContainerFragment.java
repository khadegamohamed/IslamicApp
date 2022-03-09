package com.example.islamicapp.ui.quran.quranContainer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;


public class QuranContainerFragment extends Fragment {

private ViewPager2 viewPager2;
private QuranPagesAdepter pageradepter;
private QuranContainerFragmentArgs args;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        args= QuranContainerFragmentArgs.fromBundle(requireArguments());
        return inflater.inflate(R.layout.fragment_quran_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 =view.findViewById(R.id.quran_pager);
        pageradepter = new QuranPagesAdepter(getActivity());
        viewPager2.setAdapter(pageradepter);
        viewPager2.setCurrentItem(604-args.getStartPage());
    }
}