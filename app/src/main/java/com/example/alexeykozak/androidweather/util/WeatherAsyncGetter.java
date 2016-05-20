package com.example.alexeykozak.androidweather.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alexeykozak.androidweather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WeatherAsyncGetter extends AsyncTask<Integer, Void, List<Weather>> {
    private static final String mainUrl = "http://api.openweathermap.org/data/2.5/group?";
    private static final String cityId = "id=";
    private static final String apiKey = "APPID=6f279ed3e8d8248f7b0d79f7589dc5c9";
    private static final String units = "units=metric";
    private static final String language = "lang=ru";
    private static final String TAG = "weatherAsyncGetter";

    /**
     * @param params This parameter is integer array with city id
     * @return Array of weather for cities from input params
     */
    @Override
    protected List<Weather> doInBackground(Integer... params) {
        String url = getUrl(params);

        JSONObject jsonObject = getJson(url);

        return parseJson(jsonObject);
    }

    @Override
    protected void onPostExecute(List<Weather> weathers) {
        super.onPostExecute(weathers);
    }

    private List<Weather> parseJson(JSONObject jsonObject) {
        JSONArray rootJsonArray = null;
        List<Weather> weathers = new ArrayList<>();
        try {
            rootJsonArray = jsonObject.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (rootJsonArray != null) {
            for (int i = 0; i < rootJsonArray.length(); i++) {
                Weather weather = new Weather();
                try {
                    JSONObject jsonWeather = rootJsonArray.getJSONObject(i);

                    weather.setCurrentTemp(jsonWeather.getJSONObject("main").getInt("temp"));
                    weather.setMaxTemp(jsonWeather.getJSONObject("main").getInt("temp_max"));
                    weather.setMinTemp(jsonWeather.getJSONObject("main").getInt("temp_min"));
                    weather.setDescription(jsonWeather.getJSONArray("weather").getJSONObject(0).getString("description"));
                    weather.setIcon(jsonWeather.getJSONArray("weather").getJSONObject(0).getString("icon"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                weathers.add(weather);
            }
        }
        return weathers;
    }

    private JSONObject getJson(String urlString) {
        HttpURLConnection urlConnection = null;
        JSONObject jsonObject = null;
        URL url;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(in));

            String line;

            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {

                responseStrBuilder.append(line);
            }
            in.close();

            jsonObject = new JSONObject(responseStrBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally

        {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jsonObject;
    }

    private String getUrl(Integer[] cityIds) {
        String url;
        StringBuilder builder = new StringBuilder();
        builder.append(mainUrl)
                .append("&")
                .append(apiKey)
                .append("&")
                .append(units)
                .append("&")
                .append(language)
                .append("&")
                .append(cityId)
                .append(cityIds[0]);
        if (cityIds.length > 1) {
            for (int i = 1; i < cityIds.length; i++) {
                builder.append(",")
                        .append(cityIds[i]);
            }
        }

        url = builder.toString();
        Log.d(TAG, url);

        return url;
    }
}
