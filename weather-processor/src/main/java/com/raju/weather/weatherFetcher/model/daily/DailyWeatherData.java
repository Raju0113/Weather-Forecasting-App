package com.raju.weather.weatherFetcher.model.daily;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.weatherFetcher.model.common.City;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeatherData {
	
	private Integer message;
	
	@JsonProperty("list")
    private List<DailyWeather> dailyWeather;
    
    private City city;
   
    @JsonProperty("cod")
    private String code;
    
    @JsonProperty("cnt")
    private Integer count;

}
