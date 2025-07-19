package com.raju.weather.openWeather.model.daily;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raju.weather.openWeather.model.common.City;

import lombok.Data;

@Data
public class DailyWeatherResponse {
	
	@JsonProperty("cod")
	 private String code;
	
	 private Integer message;
	
	 @JsonProperty("cnt")
	 private Integer count;
	 
	 private  List<DailyWeather>list;
	 
	 private City city;

}
