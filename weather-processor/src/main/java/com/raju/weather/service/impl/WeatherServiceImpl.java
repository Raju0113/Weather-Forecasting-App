package com.raju.weather.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.raju.weather.client.WeatherFetcherClient;
import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.weatherFetcher.model.WeatherData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WeatherServiceImpl implements WeatherService {
		
	private final WeatherFetcherClient weatherFetcherClient;

	@Override
	public ResponseEntity<WeatherData> getWeather(String city) {
		
		//validation for city
		if(city == null || city.trim().isEmpty() || !city.matches("^[a-zA-Z\\s-]+$")) {
			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
					       ErrorCodeEnum.INVALID_INPUT.getMessage(),HttpStatus.BAD_REQUEST);
		}
					 
		    ResponseEntity<WeatherData> weatherData = weatherFetcherClient.getWeatherByCity(city);
	 
		            return weatherData;
		
	}
	

}

