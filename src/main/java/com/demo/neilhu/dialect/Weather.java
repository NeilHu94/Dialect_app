package com.demo.neilhu.dialect;

/**
 * Created by NeilHu on 2017/5/31.
 */

public class Weather {
    public static final String LOG_TAG = Weather.class.getName();
    private final String week;
    private final String temperature;
    private final String weather;

    public String getWeek() {
        return week;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public Weather(String week, String temperature, String weather) {
        this.weather = weather;
        this.week = week;
        this.temperature = temperature;
    }
}
