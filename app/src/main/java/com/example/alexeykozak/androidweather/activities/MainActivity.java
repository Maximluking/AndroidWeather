package com.example.alexeykozak.androidweather.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.model.City;
import com.example.alexeykozak.androidweather.model.Weather;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private static final String CELSIUS_DEGREE = "°C";
    private static final String DIVIDER = "/";

    @BindView(R.id.weather_icon)
    ImageView imageView;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_max_min_temp)
    TextView tvMaxMinTemp;
    @BindView(R.id.tv_weather_description)
    TextView tvWeatherDescription;

    private MainPresenter presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this, getApplicationContext());

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.current_city_id), Context.MODE_PRIVATE);
        if (sharedPref.getInt(getString(R.string.current_city_id), 0) == 0) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.current_city_id), 709930).apply();
        }

        presenter.setTask();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "OnPause");

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");
        presenter.updateCityInfo();
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void showWeatherForCity(City city) {
        Weather weather = city.getWeather();

        String temp = String.valueOf(weather.getCurrentTemp()) + CELSIUS_DEGREE;
        String minMaxTemp = String.valueOf(weather.getMinTemp()) + CELSIUS_DEGREE + DIVIDER +
                String.valueOf(weather.getMaxTemp()) + CELSIUS_DEGREE;

        tvCityName.setText(city.getName());
        tvTemp.setText(temp);
        tvMaxMinTemp.setText(minMaxTemp);
        tvWeatherDescription.setText(weather.getDescription());

    }

    @Override
    public void showWeatherIcon(String iconUrl) {
        Picasso.with(getBaseContext()).load(iconUrl).into(imageView);
    }

    @OnClick(R.id.settings)
    public void settingsOnClick() {
        startActivity(new Intent(getBaseContext(), CityListActivity.class));
    }
}
