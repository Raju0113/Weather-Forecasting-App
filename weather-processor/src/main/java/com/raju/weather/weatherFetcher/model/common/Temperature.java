package com.raju.weather.weatherFetcher.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Temperature {
	
	private Double day;
    private Double min;
    private Double max;
    private Double night;
    
    @JsonProperty("eve")
    private Double evening;
    
    @JsonProperty("morn")
    private Double mornning;

}
