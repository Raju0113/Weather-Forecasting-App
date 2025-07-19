package com.raju.weather.openWeather.model.common;

import lombok.Data;

@Data
public class Weather {
	private Integer id;
	private String main;
	private String description;
	private String icon;
}
