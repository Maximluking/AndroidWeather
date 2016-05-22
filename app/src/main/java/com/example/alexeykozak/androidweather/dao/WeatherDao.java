package com.example.alexeykozak.androidweather.dao;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.alexeykozak.androidweather.model.City;
import com.example.alexeykozak.androidweather.model.ContractClass;
import com.example.alexeykozak.androidweather.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherDao {
    public static final String TAG = "TrackDao";

    private WeatherDbHelper db;
    private static WeatherDao instance;

    private WeatherDao() {
        db = new WeatherDbHelper(WeatherDbHelper.DATABASE_NAME);
    }

//    public WeatherDao(WeatherDbHelper weatherDbHelper) {
//        db = weatherDbHelper;
//    }

    public static WeatherDao getInstance() {
        if (instance == null) {
            synchronized (WeatherDao.class) {
                if (instance == null) {
                    instance = new WeatherDao();
                }
            }
        }
        return instance;
    }

    public void updateWeather(List<Weather> weatherList) {
        for (int i = 0; i < weatherList.size(); i++) {
            Weather weather = weatherList.get(i);
            ContentValues values = new ContentValues();

            values.put(ContractClass.Weather.COLUMN_NAME_CITY_FK_ID, weather.getCityId());
            values.put(ContractClass.Weather.COLUMN_NAME_CURRENT_TEMP, weather.getCurrentTemp());
            values.put(ContractClass.Weather.COLUMN_NAME_MAX_TEMP, weather.getMaxTemp());
            values.put(ContractClass.Weather.COLUMN_NAME_MIN_TEMP, weather.getMinTemp());
            values.put(ContractClass.Weather.COLUMN_NAME_DESCRIPTION, weather.getDescription());

            db.getWritableDatabase().insert(ContractClass.Weather.TABLE_NAME, null, values);

            values.clear();
        }
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM " + ContractClass.City.TABLE_NAME;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);

        while (cursor.moveToNext()) {
            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex(ContractClass.City.COLUMN_NAME_ID)));
            city.setName(cursor.getString(cursor.getColumnIndex(ContractClass.City.COLUMN_NAME_NAME)));
            city.setWeather(getWeatherByCityId(city.getId()));

            cities.add(city);
        }
        cursor.close();
        return cities;
    }

    public City getCity(int cityId) {
        City city = new City();
        Cursor cursor = db.getReadableDatabase().query(ContractClass.City.TABLE_NAME,
                ContractClass.City.DEFAULT_PROJECTION,
                ContractClass.City.COLUMN_NAME_ID,
                new String[]{String.valueOf(cityId)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            city.setName(cursor.getString(cursor.getColumnIndex(ContractClass.City.COLUMN_NAME_NAME)));
        }
        cursor.close();

        city.setId(cityId);
        city.setWeather(getWeatherByCityId(cityId));

        return city;
    }

    public Weather getWeatherByCityId(int id) {
        Weather weather = new Weather();
        Cursor cursor = db.getReadableDatabase().query(
                ContractClass.Weather.TABLE_NAME,
                ContractClass.Weather.DEFAULT_PROJECTION,
                ContractClass.Weather.COLUMN_NAME_CITY_FK_ID,
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            weather.setCurrentTemp(cursor.getFloat(cursor.getColumnIndex(ContractClass.Weather.COLUMN_NAME_CURRENT_TEMP)));
            weather.setMaxTemp(cursor.getFloat(cursor.getColumnIndex(ContractClass.Weather.COLUMN_NAME_MAX_TEMP)));
            weather.setMinTemp(cursor.getFloat(cursor.getColumnIndex(ContractClass.Weather.COLUMN_NAME_MIN_TEMP)));
            weather.setDescription(cursor.getString(cursor.getColumnIndex(ContractClass.Weather.COLUMN_NAME_DESCRIPTION)));
            weather.setIcon(cursor.getString(cursor.getColumnIndex(ContractClass.Weather.COLUMN_NAME_ICON)));
        }
        cursor.close();
        return weather;
    }


}
