package com.example.alexeykozak.androidweather.activities;

import com.example.alexeykozak.androidweather.model.Weather;


public interface MainView {
    void showWeather(Weather weather);

    void showWeatherIcon(String iconUrl);
}
