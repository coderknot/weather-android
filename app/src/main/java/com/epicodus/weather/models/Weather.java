package com.epicodus.weather.models;

public class Weather {

    String city; //name
    String country; //country
    String time; //dt_text
    String description; //main
    double temperature; //temp
    double longitude; //lon
    double latitude; //lat

    public Weather(String city,
                   String country,
                   String time,
                   String description,
                   double temperature,
                   double longitude,
                   double latitude) {
        this.city = city;
        this.country = country;
        this.time = time;
        this.description = description;
        this.temperature = temperature;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.description;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public double getLongitude() {
        return  this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }
}
