package com.raju.weather.openWeather.model;

import lombok.Data;

@Data
public class Weather {
	private int id;
	String main;
	String description;
	String icon;

}
