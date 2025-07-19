package com.raju.weather.openWeather.model.current;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.openWeather.model.common.Clouds;
import com.raju.weather.openWeather.model.common.Coord;
import com.raju.weather.openWeather.model.common.Main;
import com.raju.weather.openWeather.model.common.Sys;
import com.raju.weather.openWeather.model.common.Weather;
import com.raju.weather.openWeather.model.common.Wind;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponse {
	
	private Coord coord;
	private List<Weather> weather;
	private Main main;
	private Wind wind;
	private Clouds clouds;
	private Sys sys;
	private String base;
	private Integer visibility;
	private Integer timezone;
    private Integer id;
    private Long dt;
    private String name;
    private Integer cod;
    
    private Double pop;  
    
    @JsonProperty("dt_txt")
    private String dtTxt;
    
    

}
