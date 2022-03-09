package com.example.islamicapp.ui.azkar.azkarHome;

import android.content.Context;

import com.example.islamicapp.data.azkarProvider.AzkarProvider;
import com.example.islamicapp.data.pojo.azkar.ZekrTypes;

import java.util.HashSet;

public class AzkarTypesViewModel {
    public HashSet<ZekrTypes> getAzkarTypes(Context context){
            return AzkarProvider.getAzkarTypes(context);
    }
}
