package com.example.alexeykozak.androidweather.activities;

import android.content.Context;

import com.example.alexeykozak.androidweather.model.Weather;

/**
 * Created by alexeykozak on 20.05.16.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private Context context;

    public MainPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void updateWeather(Weather weather) {
        mainView.showWeather(weather);
        // mainView.showWeatherIcon();
    }

    @Override
    public void showPreferences() {

    }
}
