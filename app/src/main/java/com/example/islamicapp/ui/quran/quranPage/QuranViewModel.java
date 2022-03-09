package com.example.islamicapp.ui.quran.quranPage;

import android.content.Context;

import com.example.islamicapp.data.PagesManger;

import java.text.DecimalFormat;

public class QuranViewModel {
    public  int getQuranImageByPageNumber(Context context, int pageNumber){
        return PagesManger.getQuranImageByPageNumber(context,pageNumber);
    }
}
