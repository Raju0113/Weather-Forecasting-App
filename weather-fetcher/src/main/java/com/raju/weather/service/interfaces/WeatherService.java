package com.raju.weather.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.raju.weather.openWeather.model.current.CurrentWeatherResponse;
import com.raju.weather.openWeather.model.daily.DailyWeatherResponse;
import com.raju.weather.openWeather.model.history.WeatherHistoryResponse;
import com.raju.weather.openWeather.model.hourly.HourlyWeatherResponse;

public interface WeatherService {

	ResponseEntity<CurrentWeatherResponse> getWeather(String city);

	ResponseEntity<WeatherHistoryResponse> getWeatherHistory(String city, String startDate, String endDate, Integer cnt);

	ResponseEntity<HourlyWeatherResponse> getWeatherHourly(String city, Integer count);

	ResponseEntity<DailyWeatherResponse> getWeatherDaily(String city, Integer count);

}
