package com.epicodus.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.models.Weather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Weather> weatherList = new ArrayList<>();
    private Context context;

    public WeatherListAdapter(Context context, ArrayList<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(weatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.weatherImageView) ImageView weatherImageView;
        @Bind(R.id.weatherLocationTextView) TextView weatherLocationTextView;
        @Bind(R.id.weatherDescriptionTextView) TextView weatherDescriptionTextView;
        @Bind(R.id.weatherTemperatureTextView) TextView weatherTemperatureTextView;
        @Bind(R.id.weatherTimeTextView) TextView weatherTimeTextView;

        private Context context;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindWeather(Weather weather) {
            weatherLocationTextView.setText(weather.getCity() + ", " + weather.getCountry());
            weatherDescriptionTextView.setText(weather.getDescription());
            weatherTemperatureTextView.setText(weather.getTemperatureAsString());
            weatherTimeTextView.setText(weather.getTime());
        }
    }
}
