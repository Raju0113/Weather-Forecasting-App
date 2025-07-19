package com.raju.weather.weatherFetcher.model.daily;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FeelsLike {
	
    private Double day;
    private Double night;
    
    @JsonProperty("eve")
    private Double evening;
    
    @JsonProperty("morn")
    private Double morning;
}
