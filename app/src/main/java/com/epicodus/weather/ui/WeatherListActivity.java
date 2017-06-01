package com.epicodus.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.weather.R;
import com.epicodus.weather.adapters.WeatherListAdapter;
import com.epicodus.weather.models.Weather;
import com.epicodus.weather.services.OpenWeatherMapService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {
    @Bind(R.id.weatherRecyclerView) RecyclerView weatherRecyclerView;
    private WeatherListAdapter adapter;

    public ArrayList<Weather> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zip = intent.getStringExtra("zip");

        getWeatherList(zip);
    }

    private void getWeatherList(String zip) {
        final OpenWeatherMapService openWeatherMapService = new OpenWeatherMapService();
        openWeatherMapService.getWeather(zip, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                weatherList = openWeatherMapService.processResults(response);

                WeatherListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new WeatherListAdapter(getApplicationContext(), weatherList);
                        weatherRecyclerView.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WeatherListActivity.this);
                        weatherRecyclerView.setLayoutManager(layoutManager);
                        weatherRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
