package com.example.islamicapp.ui.quran.soralist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;
import com.example.islamicapp.data.Utils.IndexTabsUtils;


public class SoraListFragment extends Fragment {
 private RecyclerView soraList;
 private SoraListViewModel soraListViewModel;
 private IndexTabsUtils.QuranTabs quranTabs;

public SoraListFragment(IndexTabsUtils.QuranTabs quranTabs){
    this.quranTabs = quranTabs;
}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sora_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        soraListViewModel = new SoraListViewModel();
        soraList=view.findViewById(R.id.sora_list);
        soraList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        soraList.setAdapter(new SoraListAdepter(soraListViewModel.provideIndexList(getContext(),quranTabs),this));
    }
}