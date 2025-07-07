package com.raju.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raju.weather.dto.WeatherResponse;
import com.raju.weather.mapper.WeatherDataMapper;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.weatherFetcher.model.WeatherData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
	private final WeatherDataMapper weatherDataMapper;

	private final WeatherService weatherService; // when we are using reqargsconst annotation no need to do constuctor
													// injection just made this feild as final

	@GetMapping("/current")
	public ResponseEntity<WeatherResponse> getCurrentWeather(@RequestParam String city) {
		log.info("WeatherController.getCurrentWeather.cityName: {}", city);
		
		ResponseEntity<WeatherData> response = weatherService.getWeather(city);
		
		WeatherResponse weatherResponse = weatherDataMapper.mapToResponse(response.getBody());

		return ResponseEntity.ok(weatherResponse);

	}
}
