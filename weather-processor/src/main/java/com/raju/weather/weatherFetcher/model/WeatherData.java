package com.raju.weather.weatherFetcher.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.weatherFetcher.model.common.Clouds;
import com.raju.weather.weatherFetcher.model.common.Coord;
import com.raju.weather.weatherFetcher.model.common.Main;
import com.raju.weather.weatherFetcher.model.common.Sys;
import com.raju.weather.weatherFetcher.model.common.Weather;
import com.raju.weather.weatherFetcher.model.common.Wind;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)   //corectly map even though classs not conatins all fileds in reponse
public class WeatherData {
	private Coord coord;
	private List<Weather> weather;
	private Main main;
	private Wind wind;
	private Clouds clouds;
	private Sys sys;
	private String base;
	private Integer visibility;
	private Integer timezone;
    private Long id;
    private Long dt;
    private String name;
    private Integer cod;
    private Double pop;
    
    @JsonProperty("dt_txt")
    private String dtText;
    
}
