package com.example.islamicapp.ui.quran.quranSearch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.quran.Aya;

import java.util.List;


public class QuranSearchFragment extends Fragment {
private RecyclerView searchResultRecyclerView;
private EditText searchEditText;
private QuranSearchViewModel quranSearchViewModel;
private QuranSearchAdepter quranSearchAdepter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quranSearchAdepter = new QuranSearchAdepter(this);
        quranSearchViewModel= new QuranSearchViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchEditText = view.findViewById(R.id.search_quran_edit_text);
        searchResultRecyclerView = view.findViewById(R.id.quran_search_result);
        searchResultRecyclerView.setAdapter(quranSearchAdepter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Aya> ayat = quranSearchViewModel.getSearchResult(getContext(),s.toString());
                quranSearchAdepter.setAyas(ayat);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}