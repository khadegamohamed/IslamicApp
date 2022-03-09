package com.example.islamicapp.ui.quran.quranSearch;

import android.content.Context;

import com.example.islamicapp.data.database.QuranDao;
import com.example.islamicapp.data.database.QuranDatabase;
import com.example.islamicapp.data.pojo.quran.Aya;

import java.util.List;

public class QuranSearchViewModel {
    public List<Aya> getSearchResult(Context context, String keyword) {
        QuranDao deo = QuranDatabase.getInstance(context).quranDao();
        return deo.getAyaBySubText(keyword);
    }
}
