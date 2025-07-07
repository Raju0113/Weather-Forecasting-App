package com.raju.weather.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.raju.weather.dto.WeatherResponse;
import com.raju.weather.weatherFetcher.model.WeatherData;

@Component
public class WeatherDataMapper {
	
	
	public WeatherResponse mapToResponse(WeatherData weatherData) {
		WeatherResponse weatherResponse = new WeatherResponse();
		
		weatherResponse.setCityName(weatherData.getName());
		weatherResponse.setCountry(weatherData.getSys().getCountry());
		weatherResponse.setTemperature(kelvinToCelsius(weatherData.getMain().getTemp())+"°C");
		weatherResponse.setFeelsLike(kelvinToCelsius(weatherData.getMain().getFeels_like())+"°C");
		
		 if (weatherData.getWeather() != null && !weatherData.getWeather().isEmpty()) {
			 weatherResponse.setWeatherDescription(capitalize(weatherData.getWeather().get(0).getDescription()));
			 weatherResponse.setWeatherIcon(weatherData.getWeather().get(0).getIcon());
		    }
		 
		 weatherResponse.setWindSpeed(weatherData.getWind().getSpeed()+"m/s");
		 weatherResponse.setWindDirection(getWindDirection(weatherData.getWind().getDeg()));
		 weatherResponse.setHumidity(weatherData.getMain().getHumidity()+"%");
		 weatherResponse.setPressure(weatherData.getMain().getPressure()+"hPa");
		 weatherResponse.setVisibility((weatherData.getVisibility()/1000.0)+"km");
		 weatherResponse.setObservationTime(formatUnixTime(weatherData.getDt()));
		 
		return weatherResponse;
		
	}
	
	private double kelvinToCelsius(double temp) {
		 return Math.round((temp - 273.15) * 100.0) / 100.0;
	}

	private String capitalize(String description) {
		 return description.substring(0, 1).toUpperCase() + description.substring(1);
		
	}
	
     private String getWindDirection(double deg) {
    	 String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
    	    return directions[(int) Math.round(deg % 360 / 45) % 8];
	}

     private String formatUnixTime(long dt) {
    	    Instant instant = Instant.ofEpochSecond(dt);
    	    DateTimeFormatter formatter = DateTimeFormatter
    	            .ofPattern("yyyy-MM-dd hh:mm a") // 12-hour format with AM/PM
    	            .withZone(ZoneId.of("Asia/Kolkata")); // Apply IST timezone
    	    return formatter.format(instant);
    	}


}
