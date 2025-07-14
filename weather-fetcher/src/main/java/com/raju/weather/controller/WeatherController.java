package com.raju.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raju.weather.openWeather.model.CurrentWeatherResponse;
import com.raju.weather.openWeather.model.DailyWeatherResponse;
import com.raju.weather.openWeather.model.HourlyWeatherResponse;
import com.raju.weather.openWeather.model.WeatherHistoryResponse;
import com.raju.weather.service.interfaces.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

	private final WeatherService weatherService;

	@GetMapping("/current")
	public ResponseEntity<CurrentWeatherResponse> getCurrentWeather(
			                            @RequestParam String city) {

		log.info("WeatherController.getCurrentWeather.City name: {}", city);

		ResponseEntity<CurrentWeatherResponse> weather = weatherService.getWeather(city);
		
		CurrentWeatherResponse response = weather.getBody();
		
		log.info("Weather from weather fetcher service; {}", weather);

		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/history")
	public ResponseEntity<WeatherHistoryResponse> getWeatherHistory(
			                      @RequestParam String city,
			                      @RequestParam String startDate,
			                      @RequestParam(required=false) String endDate,
			                      @RequestParam(required=false) Integer cnt){ 
		
		log.info("WeatherController.getWeatherHistory.City name: {}", city);
		
		ResponseEntity<WeatherHistoryResponse> response =weatherService.getWeatherHistory(city,startDate,endDate,cnt);
		
		WeatherHistoryResponse historyResponse = response.getBody();
		
		
		return ResponseEntity.ok(historyResponse);
	}
	
	@GetMapping("/hourly")
	public ResponseEntity<HourlyWeatherResponse> getWeatherHourly(@RequestParam String city,
			                       @RequestParam (required=false)Integer count){ 
		
		log.info("WeatherController.getWeatherHourly.City name: count: {}", city,count);
		
		ResponseEntity<HourlyWeatherResponse> response =weatherService.getWeatherHourly(city,count);
		
		HourlyWeatherResponse hourlyResponse = response.getBody();
		
		log.info("Weather from weather fetcher service; {}", hourlyResponse);
		
		
		return ResponseEntity.ok(hourlyResponse);
	}
	
	
	@GetMapping("/daily")
	public ResponseEntity<DailyWeatherResponse> getWeatherDaily(@RequestParam String city,
			                       @RequestParam (required=false)Integer count){ 
		
		log.info("WeatherController.getWeatherHourly.City name: count: {}", city,count);
		
		ResponseEntity<DailyWeatherResponse> response =weatherService.getWeatherDaily(city,count);
		
		DailyWeatherResponse hourlyResponse = response.getBody();
		
		log.info("Weather from weather fetcher service; {}", hourlyResponse);
		
		
		return ResponseEntity.ok(hourlyResponse);
	}

}
