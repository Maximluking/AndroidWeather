package com.example.alexeykozak.androidweather.activities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.dao.WeatherDao;
import com.example.alexeykozak.androidweather.model.Weather;
import com.example.alexeykozak.androidweather.util.WeatherIconLoader;


public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private Context context;


    public MainPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void updateWeatherInfo() {

        int id;

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        id = sharedPref.getInt(context.getString(R.string.preference_file_key), 0);

        Weather weather = WeatherDao.getInstance().getWeatherByCityId(id);

        mainView.showWeather(weather);
        mainView.showWeatherIcon(WeatherIconLoader.loadIcon(context, weather.getIcon()));
    }


    @Override
    public void showPreferences() {

    }
}
