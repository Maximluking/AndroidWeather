package com.example.alexeykozak.androidweather.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexeykozak.androidweather.CityAdapter;
import com.example.alexeykozak.androidweather.R;

public class CityListDialogFragment extends DialogFragment {

    private static final String TAG = "CityListDialogFragment";
    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_list_fragment, container);

        getDialog().setTitle("Select city:");

        recyclerView = (RecyclerView) view.findViewById(R.id.city_recycler_view);
        cityAdapter = new CityAdapter(getActivity().getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(cityAdapter);

        return view;
    }


}
