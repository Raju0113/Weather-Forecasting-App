package com.raju.weather.dto;

import lombok.Data;

@Data
public class WeatherHistoryResponse {
	
	    private String description;
	    private String temperature;
	    private String feelsLike;
	    private String humidity;
	    private String windSpeed;
	    private String windDirection;
	    private String cloudiness;
	    private String dateTime; // Convert `dt` to readable time
	    private String icon;
	}
