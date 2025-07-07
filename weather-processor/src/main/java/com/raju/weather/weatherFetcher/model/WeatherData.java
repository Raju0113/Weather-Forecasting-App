package com.raju.weather.weatherFetcher.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private int visibility;
	private int timezone;
    private long id;
    private long dt;
    private String name;
    private int cod;

}
