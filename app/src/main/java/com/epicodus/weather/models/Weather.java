package com.epicodus.weather.models;

public class Weather {

    String name; //name
    String mainDescription; //main
    String detailDescription; //description
    double temperature; //temp
    double longitude; //lon
    double latitude; //lat

    public Weather(String name,
                   String mainDescription,
                   String detailDescription,
                   double temperature,
                   double longitude,
                   double latitude) {
        this.name = name;
        this.mainDescription = mainDescription;
        this.detailDescription = detailDescription;
        this.temperature = temperature;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return this.name;
    }

    public String getMainDescription() {
        return this.mainDescription;
    }

    public String getDetailDescription() {
        return this.detailDescription;
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
