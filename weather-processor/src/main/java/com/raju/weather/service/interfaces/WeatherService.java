package com.raju.weather.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

public interface WeatherService {

	ResponseEntity<WeatherData> getWeather(String city);

	ResponseEntity<HourlyWeatherData> getWeatherHourly(String city, Integer count);

	ResponseEntity<DailyWeatherData> getWeatherDaily(String city, Integer count);

	ResponseEntity<WeatherHistoryData> getWeatherHistory(String city, String startDate, String endDate, Integer cnt);

	

}
