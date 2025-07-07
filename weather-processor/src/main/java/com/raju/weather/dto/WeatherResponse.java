package com.raju.weather.dto;

import lombok.Data;

@Data
public class WeatherResponse {
	
//	//@JsonProperty("name")
//    private String cityName;
//	
//    private String country;
//	
//   // @JsonProperty("temp")
//    private double temperature;        // in °C
//    
//   // @JsonProperty("feels_like")
//    private double feelsLike;          // in °C
//   
//  //  @JsonProperty("description")
//    private String weatherDescription;
//    
//  //  @JsonProperty("icon")
//    private String weatherIcon;
//    
//   // @JsonProperty("speed")
//    private double windSpeed;          // in m/s
//   
//   // @JsonProperty("deg")
//    private String windDirection;      // as SW, NE, etc.
//    
//    private int humidity;              // %
//    
//    private int pressure;              // hPa
//    
//    private double visibility;         // in km
//    
//   // @JsonProperty("dt")
//    private String observationTime;    // formatted from 'dt'
	
	
	//@JsonProperty("name")
    private String cityName;
	
    private String country;
	
   // @JsonProperty("temp")
    private String temperature;        // in °C
    
   // @JsonProperty("feels_like")
    private String feelsLike;          // in °C
   
  //  @JsonProperty("description")
    private String weatherDescription;
    
  //  @JsonProperty("icon")
    private String weatherIcon;
    
   // @JsonProperty("speed")
    private String windSpeed;          // in m/s
   
   // @JsonProperty("deg")
    private String windDirection;      // as SW, NE, etc.
    
    private String humidity;              // %
    
    private String pressure;              // hPa
    
    private String visibility;         // in km
    
   // @JsonProperty("dt")
    private String observationTime;    // formatted from 'dt'
  
}
