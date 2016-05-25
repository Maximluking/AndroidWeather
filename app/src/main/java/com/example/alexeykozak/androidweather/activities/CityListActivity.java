package com.example.alexeykozak.androidweather.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.alexeykozak.androidweather.CityAdapter;
import com.example.alexeykozak.androidweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CityListActivity extends Activity {

    private static final String TAG = "CityListActivity";
    @BindView(R.id.city_recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
        unbinder = ButterKnife.bind(this);

        CityAdapter cityAdapter = new CityAdapter(getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(cityAdapter);
    }

    @OnClick(R.id.city_recycler_view)
    public void onItemClick() {
        Log.d(TAG, "OnItemClick");
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}