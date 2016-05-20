package com.example.alexeykozak.androidweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexeykozak.androidweather.util.WeatherAsyncGetter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherAsyncGetter weatherAsyncGetter = new WeatherAsyncGetter();
        weatherAsyncGetter.execute(709930);
    }
}
