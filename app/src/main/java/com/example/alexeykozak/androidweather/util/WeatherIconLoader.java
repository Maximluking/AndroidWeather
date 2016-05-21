package com.example.alexeykozak.androidweather.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class WeatherIconLoader {
    private static final String TAG = "WeatherIconLoader";
    private static final String URL = "http://openweathermap.org/img/w/";

    public static Bitmap loadIcon(Context context, String icon) {
        String url = getUrl(icon);
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(context).load(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static String getUrl(String icon) {
        return URL + icon;
    }
}
