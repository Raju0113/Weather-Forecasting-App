package com.raju.weather.dto;

import lombok.Data;

@Data
public class CurrentWeatherResponse {
	
    private String cityName;
	
    private String country;
	
    private String temperature;        // in °C
    
    private String feelsLike;          // in °C
   
    private String weatherDescription;
    
    private String weatherIcon;
    
    private String windSpeed;          // in m/s
   
    private String windDirection;      // as SW, NE, etc.
    
    private String humidity;              // %
    
    private String pressure;              // hPa
    
    private String visibility;         // in km
    
    private String observationTime;    // formatted from 'dt'
  
}
