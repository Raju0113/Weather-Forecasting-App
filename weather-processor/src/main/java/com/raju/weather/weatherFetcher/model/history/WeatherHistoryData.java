package com.raju.weather.weatherFetcher.model.history;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.weatherFetcher.model.WeatherData;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherHistoryData {
	
	private String message;
	
    private String cod;
    
    @JsonProperty("city_id")
    private  Integer cityId;
    
    @JsonProperty("calctime")
    private Double calculateTime;
    
    @JsonProperty("cnt")
    private Integer count;
    
    List<WeatherData>list;
	

}
