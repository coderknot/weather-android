package com.epicodus.weather.services;

import com.epicodus.weather.Constants;
import com.epicodus.weather.models.Weather;

import java.util.ArrayList;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherMapService {

    public static void getWeather(String zip, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OWM_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OWM_ZIP_PARAMETER, zip);
        urlBuilder.addQueryParameter(Constants.OWN_API_KEY_QUERY_PARAMETER, Constants.OWM_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weatherList = new ArrayList<>();

        try {
            if(response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject openWeatherMapJSON = new JSONObject(jsonData);

                String name = openWeatherMapJSON.getString("name");
                String mainDescription = openWeatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("main");
                String detailDescription = openWeatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                double temperature = openWeatherMapJSON.getJSONObject("main").getDouble("temp");
                double longitude = openWeatherMapJSON.getJSONObject("coord").getDouble("lon");
                double latitude = openWeatherMapJSON.getJSONObject("coord").getDouble("lat");

                Weather weather = new Weather(name, mainDescription, detailDescription, temperature, longitude, latitude);
                weatherList.add(weather);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherList;
    }



}
