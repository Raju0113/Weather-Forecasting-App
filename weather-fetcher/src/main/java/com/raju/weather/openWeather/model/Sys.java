package com.raju.weather.openWeather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class Sys {
	private String country;
	private Long sunrise;
	private Long sunset;
	private String pod;

}
