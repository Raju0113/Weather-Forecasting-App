package com.raju.weather.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.raju.weather.client.LocationService;
import com.raju.weather.client.OpenWeatherClient;
import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;
import com.raju.weather.openWeather.model.GeoLocation;
import com.raju.weather.openWeather.model.WeatherData;
import com.raju.weather.service.interfaces.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WeatherServiceImpl implements WeatherService {
	
	private LocationService locationService;
	
	private OpenWeatherClient openWeatherClient;


	WeatherServiceImpl(LocationService locationService , OpenWeatherClient openWeatherClient) { // Constructor injection
		
		this.locationService = locationService;
		this.openWeatherClient = openWeatherClient;
	}

	@Override
	public ResponseEntity<WeatherData> getWeather(String city) {
		
		//validation for city
		if(city == null || city.trim().isEmpty() || !city.matches("^[a-zA-Z\\s-]+$")) {
			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
					       ErrorCodeEnum.INVALID_INPUT.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		StopWatch watch = new StopWatch();  //for testing purpose
		watch.start();
		
		GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
		watch.stop();
		log.info("Time taken to fetch coordinates: {} ms", watch.getTotalTimeMillis());
		
		 if (geoLocation == null) {
	            log.warn("No coordinates found for city: {}", city);
	            
//	            return ResponseEntity.status(404).body("City not found: " + city);  //instead of returning like this we better to throw excptions
	            throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
	            		    ErrorCodeEnum.CITY_NOT_FOUND.getMessage(),
	            		            HttpStatus.NOT_FOUND);
	        }
		 
		 log.info("Latitude: {} Longitude: {}", geoLocation.getLat(), geoLocation.getLon());
		 
		    ResponseEntity<WeatherData> weatherData = openWeatherClient.
		    		                  getWeatherByCoordinates(geoLocation.getLat(), geoLocation.getLon());//giving coordinates and getting weather data
	 
		            return weatherData;
		
	}
	

}

