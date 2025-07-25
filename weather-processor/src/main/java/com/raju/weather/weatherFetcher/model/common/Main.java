package com.raju.weather.weatherFetcher.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Main {
	   
	    @JsonProperty("temp")
	    private Double temperature;

	    @JsonProperty("feels_like")
	    private Double feelsLike;

	    @JsonProperty("temp_min")
	    private Double tempMin;

	    @JsonProperty("temp_max")
	    private Double tempMax;

	    private Integer pressure;

	    @JsonProperty("humidity")
	    private Integer humidity;

	    @JsonProperty("sea_level")
	    private Integer seaLevel;

	    @JsonProperty("grnd_level")
	    private Integer grndLevel;

	    @JsonProperty("temp_kf")
	    private Double tempKf;
}
