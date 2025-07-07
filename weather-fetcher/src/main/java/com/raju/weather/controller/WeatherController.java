package com.raju.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raju.weather.openWeather.model.WeatherData;
import com.raju.weather.service.interfaces.WeatherService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/weather")
public class WeatherController {

	private WeatherService weatherService;

	WeatherController(WeatherService weatherService) { // Constructor injection

		this.weatherService = weatherService;
	}

	@GetMapping("/current")
	public ResponseEntity<WeatherData> getCurrentWeather(@RequestParam String city) {

		log.info("WeatherController.getCurrentWeather.City name: {}", city);

		ResponseEntity<WeatherData> weather = weatherService.getWeather(city);
		
		WeatherData response = weather.getBody();
		
		log.info("Weather from weather fetcher service; {}", weather);

		return ResponseEntity.ok(response);

	}
}
