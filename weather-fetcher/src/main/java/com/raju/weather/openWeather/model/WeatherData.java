package com.raju.weather.openWeather.model;

import java.util.List;

import lombok.Data;

@Data
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
    private int id;
    private long dt;
    private String name;
    private int cod;

}
