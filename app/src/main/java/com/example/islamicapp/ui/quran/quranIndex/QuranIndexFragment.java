package com.example.islamicapp.ui.quran.quranIndex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;

import com.example.islamicapp.R;
import com.example.islamicapp.data.Utils.IndexTabsUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class QuranIndexFragment extends Fragment {
EditText search;
TabLayout indexTab;
ViewPager2 viewPager2;
QuranIndexAdepter adepter;
QuranIndexViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran_index, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
  search = view.findViewById(R.id.search_quran_edit_text);
  indexTab = view.findViewById(R.id.quran_index_tabs);
  viewPager2 = view.findViewById(R.id.quran_index_pager);
  adepter = new QuranIndexAdepter(this);
  viewModel = new QuranIndexViewModel();
  viewPager2.setAdapter(adepter);
  new TabLayoutMediator(indexTab ,viewPager2 , ((tab, position) ->
          tab.setText(viewModel.getTab(position))
          )).attach();

        search.setOnClickListener(v ->
                NavHostFragment
                        .findNavController(this)
                        .navigate(QuranIndexFragmentDirections
                                .actionQuranIndexFragmentToQuranSearchFragment()));
    }

}