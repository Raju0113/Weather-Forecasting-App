package com.raju.weather.openWeather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyWeatherResponse {
	
	@JsonProperty("cod")
	 private String code;
	
	 private Integer message;
	
	 @JsonProperty("cnt")
	 private Integer count;
	 
	 private  List<CurrentWeatherResponse>list;
	 
	 private City city;

	 

}
