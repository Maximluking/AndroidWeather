package com.example.alexeykozak.androidweather.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.dao.WeatherDao;
import com.example.alexeykozak.androidweather.model.City;
import com.example.alexeykozak.androidweather.util.InternetConnectionChecker;
import com.example.alexeykozak.androidweather.util.WeatherHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenter";
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
    public void setTask() {

        List<City> cities = WeatherDao.getInstance().getAllCities();
        final Integer[] cityIds = new Integer[cities.size()];

        for (int i = 0; i < cities.size(); i++) {
            cityIds[i] = cities.get(i).getId();
        }

        if (InternetConnectionChecker.isNetworkConnected(context)) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    WeatherHelper.makeRequest(cityIds, context);
                }
            };
            timer.schedule(task, 0, 30 * 60 * 1000);

        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showPreferences() {

    }

    public static void onWeatherReceived() {
        new MainPresenterImpl(mainView, context).updateCityInfo();
    }
}
