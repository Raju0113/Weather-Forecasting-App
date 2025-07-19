package com.raju.weather.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.raju.weather.dao.interfaces.WeatherDAO;
import com.raju.weather.entity.HourlyForecastEntity;
import com.raju.weather.mapper.WeatherDataMapper;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherDataScheduler {
	
	private final WeatherService weatherService;
	private final WeatherDataMapper weatherDataMapper;
	
	private final WeatherDAO weatherDAO;
	
	private final AlertService alertService;
	
	@Scheduled(cron = "0 0 */3 * * *") 
//	@Scheduled(cron = "0 * * * * *")
	public void loadDB() {   //This method calls the weather fetching hourly api and persists the weather data in the db
		
		ResponseEntity<HourlyWeatherData> response = weatherService.getWeatherHourly("Visakhapatnam", 96);

		List<HourlyForecastEntity> hourlyWeatherEntityList = weatherDataMapper
				.mapToHourlyResponseEntity(response.getBody());

		weatherDAO.saveWeatherData(hourlyWeatherEntityList);// storing weather data in the db
		
		
	}
	 
//	@Scheduled(cron = "0 * * * * *")
	public void sendAlerts() {
		LocalDateTime now = LocalDateTime.now();
		
		List<HourlyForecastEntity> dbData=weatherDAO.fetchAll(now);
		
		StringBuilder sb = new StringBuilder();
		
		for(HourlyForecastEntity entity:dbData) {
			if( ((entity.getRainProbability()>99) || (entity.getTemperature()>=40)||(entity.getWindSpeed()>=20)) && !entity.isSendAlert()){
				
				sb.append("⛈️ Weather Alert at ").append(entity.getTimestamp()).append("\n");
		        sb.append("City: ").append(entity.getCity()).append("\n");
		        sb.append("Temp: ").append(entity.getTemperature()).append("°C\n");
		        sb.append("Rain: ").append(entity.getRainProbability()).append("%\n");
		        sb.append("Wind: ").append(entity.getWindSpeed()).append(" m/s\n\n");
		        
				alertService.sendAlert("prudhvirajooo563@gmail.com", "⚠️ WeatherAlert",sb.toString() );
				entity.setSendAlert(true);
				weatherDAO.saveWeatherData(dbData);
				 
			}
			
		}
	} 



}
