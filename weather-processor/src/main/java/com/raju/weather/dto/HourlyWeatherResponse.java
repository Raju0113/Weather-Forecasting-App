package com.raju.weather.dto;

import lombok.Data;

@Data
public class HourlyWeatherResponse {

	private String city;
    private String dateTime;         // Example: "2025-07-17 06:00:00"
    private String description;      // e.g., "Light rain"
    private String icon;             // e.g., "10d"
    private String temp;             // e.g., "30.8°C"
    private String tempMin;          // e.g., "30.8°C"
    private String tempMax;          // e.g., "31.2°C"
    private String feelsLike;        // e.g., "33.4°C"
    private String humidity;         // e.g., "64%"
    private String pressure;         // e.g., "1008 hPa"
    private String windSpeed;        // e.g., "4.5 m/s"
    private String windDirection;    // e.g., "W"
    private String cloudiness;       // e.g., "100%"
    private String rainProbability;  // e.g., "97%"
}
