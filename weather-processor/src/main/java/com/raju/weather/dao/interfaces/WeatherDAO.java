package com.raju.weather.dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.raju.weather.entity.HourlyForecastEntity;

public interface WeatherDAO {
	
	void saveWeatherData(List<HourlyForecastEntity> hourlyWeatherEntityList);
	
	List<HourlyForecastEntity>fetchAll(LocalDateTime now);
	


}
