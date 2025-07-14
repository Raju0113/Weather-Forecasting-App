package com.raju.weather.openWeather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherHistoryResponse {
	
	private String message;
	
    private String cod;
    
    @JsonProperty("city_id")
    private  Integer cityId;
    
    @JsonProperty("calctime")
    private Double calculateTime;
    
    @JsonProperty("cnt")
    private Integer count;
    
    List<CurrentWeatherResponse>list;
	

}
