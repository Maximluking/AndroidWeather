package com.example.alexeykozak.androidweather.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexeykozak.androidweather.R;
import com.example.alexeykozak.androidweather.model.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.weather_icon)
    ImageView imageView;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_max_min_temp)
    TextView tvMaxMinTemp;
    @BindView(R.id.tv_weather_description)
    TextView tvWeatherDescription;
    private static final String CELSIUS_DEGREE = "°C";
    private static final String DIVIDER = "/";

    private MainPresenter presenter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this, getApplicationContext());


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateWeatherInfo();


    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void showWeather(Weather weather) {
        String temp = String.valueOf(weather.getCurrentTemp()) + CELSIUS_DEGREE;
        String minMaxTemp = String.valueOf(weather.getMinTemp()) + CELSIUS_DEGREE + DIVIDER +
                String.valueOf(weather.getMaxTemp()) + CELSIUS_DEGREE;

        tvTemp.setText(temp);
        tvMaxMinTemp.setText(minMaxTemp);
        tvWeatherDescription.setText(weather.getDescription());

    }

    @Override
    public void showWeatherIcon(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
