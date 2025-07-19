package com.raju.weather.weatherFetcher.model.hourly;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.common.City;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyWeatherData {
	
	@JsonProperty("cod")
	 private String code;
	
	 private Integer message;
	
	 @JsonProperty("cnt")
	 private Integer count;
	 
	 private  List<WeatherData>list;
	 
	 private City city;

	 

}
