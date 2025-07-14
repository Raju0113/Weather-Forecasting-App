package com.raju.weather.openWeather.model;

import java.util.Map;
import lombok.Data;

@Data
public class GeoLocation {
	private String name;
	private Map<String, String> local_names;
	private Double lat;
	private Double lon;
	private String country;
	private String state;

}
