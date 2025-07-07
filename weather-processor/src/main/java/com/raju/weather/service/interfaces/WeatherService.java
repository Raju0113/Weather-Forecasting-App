package com.raju.weather.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.raju.weather.weatherFetcher.model.WeatherData;

public interface WeatherService {

	ResponseEntity<WeatherData> getWeather(String city);

	

}
