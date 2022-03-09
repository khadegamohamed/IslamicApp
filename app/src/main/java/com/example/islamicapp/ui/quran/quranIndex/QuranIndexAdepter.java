package com.example.islamicapp.ui.quran.quranIndex;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.islamicapp.data.Utils.IndexTabsUtils;
import com.example.islamicapp.ui.quran.soralist.SoraListFragment;

public class QuranIndexAdepter extends FragmentStateAdapter {
public static int PAGES_COUNT = 3;
    public QuranIndexAdepter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new SoraListFragment(IndexTabsUtils.QuranTabs.Sora);
            case 1:
                return  new SoraListFragment(IndexTabsUtils.QuranTabs.Jozz);
            case 2:
                return  new SoraListFragment(IndexTabsUtils.QuranTabs.Page);
            default: return null;
        }

    }

    @Override
    public int getItemCount() {
        return PAGES_COUNT;
    }
}
