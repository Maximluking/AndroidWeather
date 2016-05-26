package com.example.alexeykozak.androidweather.util;


import android.content.Context;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class WeatherHelper {
    private static final String TAG = "WeatherHelper";

    public static void makeRequest(Integer[] cityIds, Context context) {
        WeatherAsyncGetter weatherAsyncGetter = new WeatherAsyncGetter(context);
        weatherAsyncGetter.execute(cityIds);
        try {
            Log.d(TAG, String.valueOf(weatherAsyncGetter.get().size()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
