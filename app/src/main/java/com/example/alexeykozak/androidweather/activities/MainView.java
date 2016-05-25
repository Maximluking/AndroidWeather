package com.example.alexeykozak.androidweather.activities;

import com.example.alexeykozak.androidweather.model.City;


public interface MainView {
    void showWeatherForCity(City city);

    void showWeatherIcon(String iconUrl);
}
