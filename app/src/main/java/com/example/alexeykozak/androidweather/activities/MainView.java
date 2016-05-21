package com.example.alexeykozak.androidweather.activities;

import android.graphics.Bitmap;

import com.example.alexeykozak.androidweather.model.Weather;


public interface MainView {
    void showWeather(Weather weather);

    void showWeatherIcon(Bitmap bitmap);
}
