package com.example.alexeykozak.androidweather.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.model.Weather;
import com.example.alexeykozak.androidweather.util.WeatherAsyncGetter;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.weather_icon)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherAsyncGetter weatherAsyncGetter = new WeatherAsyncGetter();
        weatherAsyncGetter.execute(709930);

    }

    @Override
    public void showWeather(Weather weather) {

    }

    @Override
    public void showWeatherIcon(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
