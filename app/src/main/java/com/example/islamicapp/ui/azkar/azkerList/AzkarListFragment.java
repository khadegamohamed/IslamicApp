package com.example.islamicapp.ui.azkar.azkerList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islamicapp.R;


public class AzkarListFragment extends Fragment {
    AzkarListFragmentArgs args;
    private AzkarListViewModel viewModel;
    private AzkarListAdepter adepter;
    private RecyclerView azkarList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_azkar_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = AzkarListFragmentArgs.fromBundle(requireArguments());
        viewModel = new AzkarListViewModel();
        adepter = new AzkarListAdepter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        azkarList = view.findViewById(R.id.azker_list);
        azkarList.setAdapter(adepter);
        azkarList.setLayoutManager(new LinearLayoutManager(getContext()));
        adepter.setAzkar(viewModel.getAzkar(getContext(),args.getAzkerType()));
    }
}