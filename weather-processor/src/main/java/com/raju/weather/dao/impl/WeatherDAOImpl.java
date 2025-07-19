package com.raju.weather.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.raju.weather.dao.interfaces.WeatherDAO;
import com.raju.weather.entity.HourlyForecastEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Repository
@Slf4j
public class WeatherDAOImpl implements WeatherDAO{
	
	private final EntityManager entityManager;
		
	
	public List<HourlyForecastEntity> existsByTimestamp(LocalDateTime timestamp,String city) {

	    return entityManager.createQuery(
		        "SELECT e FROM HourlyForecastEntity e WHERE e.timestamp = :timestamp AND e.city=:city",
		        HourlyForecastEntity.class)
		       .setParameter("timestamp", timestamp)
		       .setParameter("city", city)
		       .getResultList();
	}

	
	@Override
	@Transactional
	public void saveWeatherData(List<HourlyForecastEntity> hourlyWeatherEntityList) {
		
		log.info("WeatherDAOImpl.saveWeatherData is called");
		
		for (HourlyForecastEntity hourlyWeather : hourlyWeatherEntityList) {
			List<HourlyForecastEntity> entity = existsByTimestamp(hourlyWeather.getTimestamp(),hourlyWeather.getCity());
			
			if (!entity.isEmpty() && entity.get(0)!=null) {
				
				hourlyWeather.setSendAlert(entity.get(0).isSendAlert());
				entityManager.merge(hourlyWeather);
				
			} else {
				hourlyWeather.setSendAlert(false);
				entityManager.persist(hourlyWeather);
			}

		}
		
	}


	@Override
	public List<HourlyForecastEntity> fetchAll(LocalDateTime currentTime) {
		
		log.info("WeatherDAOImpl.fetchAll is called");
		
		return entityManager.createQuery("SELECT h from HourlyForecastEntity h where h.timestamp >=:currentTime",
				                     HourlyForecastEntity.class)
				                    .setParameter("currentTime", currentTime)
				                    .getResultList();
	}

	

}