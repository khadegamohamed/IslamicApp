package com.example.islamicapp.ui.quran.soralist;

import android.content.Context;

import com.example.islamicapp.data.Utils.IndexTabsUtils;
import com.example.islamicapp.data.database.QuranDao;
import com.example.islamicapp.data.database.QuranDatabase;
import com.example.islamicapp.data.pojo.quran.Jozz;
import com.example.islamicapp.data.pojo.quran.Sora;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SoraListViewModel  {
public ArrayList<Sora> getAllSoras(Context context){
    QuranDao qurandao = QuranDatabase .getInstance(context).quranDao();
    ArrayList<Sora> soras = new ArrayList<>();
    for(int i=1;i<=114;i++){
        soras.add(qurandao.getSoraByNumber(i));
    }
    return soras;
}
public ArrayList<Jozz> getAllJozzs(Context context){
    QuranDao quranDao = QuranDatabase.getInstance(context).quranDao();
    ArrayList<Jozz> jozzs = new ArrayList<>();
    for (int i=1;i<=30;i++){
        jozzs.add(quranDao.getJozzByNumber(i));
    }
    return jozzs;
}
public List<Integer> getAllPages(){
    return IntStream.range(1,604).boxed().collect(Collectors.toList());
}
public List<?> provideIndexList(Context context, IndexTabsUtils.QuranTabs quranTabs){
    switch (quranTabs){
        case Sora:
            return getAllSoras(context);
        case Jozz:
            return getAllJozzs(context);
        case Page:
            return getAllPages();
        default:
            return null;
    }
}


}
