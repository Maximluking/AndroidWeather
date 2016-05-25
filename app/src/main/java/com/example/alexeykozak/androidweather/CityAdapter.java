package com.example.alexeykozak.androidweather;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexeykozak.androidweather.dao.WeatherDao;
import com.example.alexeykozak.androidweather.model.City;

import java.util.List;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private static Context context;
    private List<City> cities = WeatherDao.getInstance().getAllCities();
    private ViewGroup parent;

    public CityAdapter(Context context) {
        CityAdapter.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false);
        this.parent = parent;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TextView tvCityName = holder.getTvCityName();
        final City city = cities.get(position);
        tvCityName.setText(city.getName());

        tvCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.current_city_id), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(context.getString(R.string.current_city_id), city.getId()).apply();
                ((Activity) parent.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCityName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tv_dialog_city_name);
        }

        public TextView getTvCityName() {
            return tvCityName;
        }

        public void setTvCityName(TextView tvCityName) {
            this.tvCityName = tvCityName;
        }
    }
}
