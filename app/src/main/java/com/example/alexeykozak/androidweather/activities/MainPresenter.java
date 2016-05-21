package com.example.alexeykozak.androidweather.activities;

import com.example.alexeykozak.androidweather.model.Weather;

public interface MainPresenter {
    void updateWeather(Weather weather);

    void showPreferences();
}
