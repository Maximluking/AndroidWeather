package com.example.alexeykozak.androidweather.activities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.dao.WeatherDao;
import com.example.alexeykozak.androidweather.model.City;


public class MainPresenterImpl implements MainPresenter {
    private static MainView mainView;
    private static Context context;


    public MainPresenterImpl(MainView mainView, Context context) {
        MainPresenterImpl.mainView = mainView;
        MainPresenterImpl.context = context;
    }

    @Override
    public void updateCityInfo() {
        int id;
        String url = "http://openweathermap.org/img/w/";

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.current_city_id), Context.MODE_PRIVATE);
        id = sharedPref.getInt(context.getString(R.string.current_city_id), 0);

        City city = WeatherDao.getInstance().getCity(id);

        mainView.showWeatherForCity(city);
        mainView.showWeatherIcon(url + city.getWeather().getIcon() + ".png");
    }


    @Override
    public void showPreferences() {

    }

    public static void onWeatherReceived() {
        new MainPresenterImpl(mainView, context).updateCityInfo();
    }
}
