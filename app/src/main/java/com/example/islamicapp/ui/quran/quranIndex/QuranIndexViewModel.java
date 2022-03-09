package com.example.islamicapp.ui.quran.quranIndex;

import com.example.islamicapp.data.Utils.IndexTabsUtils;

public class QuranIndexViewModel {
    public String [] tablist;

    public QuranIndexViewModel() {
        tablist = IndexTabsUtils.QURAN_INDEX_TABS;
    }
    public String getTab(int postion){
        return tablist[postion];
    }
}
