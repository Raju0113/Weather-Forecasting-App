package com.raju.weather.openWeather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FeelsLike {
	
    private Double day;
    private Double night;
    
    @JsonProperty("eve")
    private Double evening;
    
    @JsonProperty("monr")
    private Double morning;
}

