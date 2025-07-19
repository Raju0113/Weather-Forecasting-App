package com.raju.weather.weatherFetcher.model.daily;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.weatherFetcher.model.common.Temperature;
import com.raju.weather.weatherFetcher.model.common.Weather;

import lombok.Data;

@Data
public class DailyWeather {
	
	private Long dt;
    private Long sunrise;
    private Long sunset;

    private Temperature temp;
    
    @JsonProperty("feels_like")
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
