package com.raju.weather.openWeather.model;

import java.util.List;

import lombok.Data;

@Data
public class DailyWeather {
	
	private Long dt;
    private Long sunrise;
    private Long sunset;

    private Temp temp;
    
    private FeelsLike feelsLike;

    private Integer pressure;
    private Integer humidity;

    private List<Weather> weather;

    private Double speed;
    private Integer deg;
    private Double gust;

    private Integer clouds;
    private Double pop;

}
