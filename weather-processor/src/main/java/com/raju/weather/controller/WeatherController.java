package com.raju.weather.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raju.weather.dao.interfaces.WeatherDAO;
import com.raju.weather.dto.CurrentWeatherResponse;
import com.raju.weather.dto.DailyWeatherResponse;
import com.raju.weather.dto.HourlyWeatherResponse;
import com.raju.weather.dto.WeatherHistoryResponse;
import com.raju.weather.entity.HourlyForecastEntity;
import com.raju.weather.mapper.WeatherDataMapper;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
	private final WeatherDataMapper weatherDataMapper;
	
	private final WeatherDAO weatherDAO;

	private final WeatherService weatherService; // when we are using @RequiredArgsConstructor annotation no need to do constuctor
													// injection just made this field as final

	@GetMapping("/current")
	public ResponseEntity<CurrentWeatherResponse> getCurrentWeather(@RequestParam String city) {
		log.info("WeatherController.getCurrentWeather.cityName: {}", city);
		
		ResponseEntity<WeatherData> response = weatherService.getWeather(city);
		
		CurrentWeatherResponse weatherResponse = weatherDataMapper.mapToCurrentResponse(response.getBody());
		


		return ResponseEntity.ok(weatherResponse);

	}
	
	@GetMapping("/hourly")
	public ResponseEntity<List<HourlyWeatherResponse>> getWeatherHourly(@RequestParam String city,
			                       @RequestParam (required=false)Integer count){ 
		
		log.info("WeatherController.getWeatherHourly.City name:{}, count:{}", city,count);
		
		ResponseEntity<HourlyWeatherData> response =weatherService.getWeatherHourly(city,count);
		
		
		List<HourlyForecastEntity> hourlyWeatherEntityList = weatherDataMapper.mapToHourlyResponseEntity(response.getBody());
		

			weatherDAO.saveWeatherData(hourlyWeatherEntityList);//storing weather data in the db

		
		List<HourlyWeatherResponse> hourlyWeatherResponse = weatherDataMapper.mapToHourlyResponse(response.getBody());
		return ResponseEntity.ok(hourlyWeatherResponse);
	}
	
	
	@GetMapping("/daily")
	public ResponseEntity<List<DailyWeatherResponse>> getWeatherDaily(@RequestParam String city,
			                       @RequestParam (required=false)Integer count){ 
		
		log.info("WeatherController.getWeatherHourly.City name:{} ,count: {}", city,count);
		
		ResponseEntity<DailyWeatherData> response =weatherService.getWeatherDaily(city,count);
		
		log.info("WeatherController.getWeatherDaily.DailyWeatherData.response:{}",response.getBody());
		
		List<DailyWeatherResponse> dailyWeatherResponse = weatherDataMapper.mapToDailyResponse(response.getBody());
		
		
		log.info("Weather from weather fetcher service; {}", dailyWeatherResponse);
		
		
		return ResponseEntity.ok(dailyWeatherResponse);
	}
	
	
	@GetMapping("/history")
	public ResponseEntity<List<WeatherHistoryResponse>> getWeatherHistory(
			                      @RequestParam String city,
			                      @RequestParam String startDate,
			                      @RequestParam(required=false) String endDate,
			                      @RequestParam(required=false) Integer count){ 
		
		log.info("WeatherController.getWeatherHistory.City name: {}", city);
		
		ResponseEntity<WeatherHistoryData> response =weatherService.getWeatherHistory(city,startDate,endDate,count);
		
		List<WeatherHistoryResponse> weatherHistoryResponse = weatherDataMapper.mapToHistoryWeatherResponse(response.getBody());
		
		
		return ResponseEntity.ok(weatherHistoryResponse);
	}
	
	
}
