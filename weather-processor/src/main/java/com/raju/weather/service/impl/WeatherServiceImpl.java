package com.raju.weather.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.raju.weather.client.WeatherFetcherClient;
import com.raju.weather.dao.interfaces.WeatherDAO;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.validation.Validator;
import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WeatherServiceImpl implements WeatherService {
		
	private final WeatherFetcherClient weatherFetcherClient;
	
	private final Validator validator;
	
	private final WeatherDAO weatherDAO;

	@Override
	public ResponseEntity<WeatherData> getWeather(String city) {
		
		validator.validCity(city);
					 
		    ResponseEntity<WeatherData> weatherData = weatherFetcherClient.getCurrentWeatherByCity(city);
		    
		            return weatherData;
		
	}

	@Override
	public ResponseEntity<HourlyWeatherData> getWeatherHourly(String city, Integer count) {
		// validation for city
		validator.validCity(city);

		ResponseEntity<HourlyWeatherData> weatherData = weatherFetcherClient.
				                             getHourlyWeatherByCity(city, count);

		return weatherData;
	}

	
	@Override
	public ResponseEntity<DailyWeatherData> getWeatherDaily(String city, Integer count) {
		
		ResponseEntity<DailyWeatherData> weatherData = weatherFetcherClient.
                                             getDailyWeatherByCity(city, count);
		
		      return weatherData;
	}
	
	@Override
	public ResponseEntity<WeatherHistoryData> getWeatherHistory(String city, String startDate, 
			                                               String endDate, Integer count) {
		validator.validCity(city);
		validator.validStartDate(startDate);
		validator.validEndDateOrCnt(endDate, count);
		
		if(endDate!=null) {
		       validator.validStartDateAndEndDate(startDate,endDate); //only validates start and end Dates when user pass endDate not count
		}
		
	ResponseEntity<WeatherHistoryData> weatherData = weatherFetcherClient.getWeatherHistory(
				                                           city, startDate,endDate,count);

                        return weatherData;
		
	}

}

