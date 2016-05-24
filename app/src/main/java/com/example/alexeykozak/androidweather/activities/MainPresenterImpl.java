package com.example.alexeykozak.androidweather.activities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.dao.WeatherDao;
import com.example.alexeykozak.androidweather.model.Weather;


public class MainPresenterImpl implements MainPresenter {
    private static MainView mainView;
    private static Context context;


    public MainPresenterImpl(MainView mainView, Context context) {
        MainPresenterImpl.mainView = mainView;
        MainPresenterImpl.context = context;
    }

    @Override
    public void updateWeatherInfo() {
        int id;
        String url = "http://openweathermap.org/img/w/";

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.current_city_id), Context.MODE_PRIVATE);
        id = sharedPref.getInt(context.getString(R.string.current_city_id), 0);

        Weather weather = WeatherDao.getInstance().getWeatherByCityId(id);

        mainView.showWeather(weather);
        mainView.showWeatherIcon(url + weather.getIcon() + ".png");
    }


    @Override
    public void showPreferences() {

    }

    public static void onWeatherReceived() {
        new MainPresenterImpl(mainView, context).updateWeatherInfo();
    }
}
