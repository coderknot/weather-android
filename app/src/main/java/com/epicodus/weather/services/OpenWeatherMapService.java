package com.epicodus.weather.services;

import com.epicodus.weather.Constants;
import com.epicodus.weather.models.Weather;

import java.util.ArrayList;
import java.io.IOException;

import org.json.JSONArray;
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
                JSONArray forecast = openWeatherMapJSON.getJSONArray("list");

                for(int i = 0; i < forecast.length(); i++) {
                    String city = openWeatherMapJSON.getJSONObject("city").getString("name");
                    String country = openWeatherMapJSON.getJSONObject("city").getString("country");
                    double longitude = openWeatherMapJSON.getJSONObject("city").getJSONObject("coord").getDouble("lon");
                    double latitude = openWeatherMapJSON.getJSONObject("city").getJSONObject("coord").getDouble("lat");
                    String time = forecast.getJSONObject(i).getString("dt_txt");
                    String description = forecast.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");
                    double temperature = forecast.getJSONObject(i).getJSONObject("main").getDouble("temp");

                    Weather weather = new Weather(city, country, time, description, temperature, longitude, latitude);
                    weatherList.add(weather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherList;
    }



}
