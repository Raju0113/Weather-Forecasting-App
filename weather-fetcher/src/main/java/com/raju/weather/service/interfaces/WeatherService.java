package com.raju.weather.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.raju.weather.openWeather.model.WeatherData;

public interface WeatherService {

	ResponseEntity<WeatherData> getWeather(String city);

	

}
