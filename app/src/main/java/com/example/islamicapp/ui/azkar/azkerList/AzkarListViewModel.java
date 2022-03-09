package com.example.islamicapp.ui.azkar.azkerList;

import android.content.Context;

import com.example.islamicapp.data.azkarProvider.AzkarProvider;
import com.example.islamicapp.data.pojo.azkar.Zekr;

import java.util.ArrayList;

public class AzkarListViewModel {
    public ArrayList<Zekr> getAzkar(Context context,String zkerType){

     return  AzkarProvider.getZekrByType(context,zkerType);
}

}
