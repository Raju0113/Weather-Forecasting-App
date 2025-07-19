package com.raju.weather.dto;

import lombok.Data;

@Data
public class DailyWeatherResponse {
    private String date; // date timestamp
    private String description;
    
    private String tempMin;
    private String tempMax;

    private String windSpeed;
    private String windDirection;
    private String pressure;
    private String humidity;
//    private String uvi;
//    private String dewPoint;
    private String pop; // probability of precipitation
    private String icon;

    private String sunrise;
    private String sunset;

    // Temperature by time of day
    private String tempMorn;
    private String tempDay;
    private String tempEve;
    private String tempNight;

    // Feels like by time of day
    private String feelsLikeMorn;
    private String feelsLikeDay;
    private String feelsLikeEve;
    private String feelsLikeNight;
}

