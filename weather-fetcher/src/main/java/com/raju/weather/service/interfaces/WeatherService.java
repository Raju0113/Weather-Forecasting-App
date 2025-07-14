package com.raju.weather.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.raju.weather.openWeather.model.CurrentWeatherResponse;
import com.raju.weather.openWeather.model.DailyWeatherResponse;
import com.raju.weather.openWeather.model.HourlyWeatherResponse;
import com.raju.weather.openWeather.model.WeatherHistoryResponse;

public interface WeatherService {

	ResponseEntity<CurrentWeatherResponse> getWeather(String city);

	ResponseEntity<WeatherHistoryResponse> getWeatherHistory(String city, String startDate, String endDate, Integer cnt);

	ResponseEntity<HourlyWeatherResponse> getWeatherHourly(String city, Integer count);

	ResponseEntity<DailyWeatherResponse> getWeatherDaily(String city, Integer count);

}
