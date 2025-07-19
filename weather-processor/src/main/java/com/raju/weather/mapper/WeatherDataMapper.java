package com.raju.weather.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.raju.weather.dto.CurrentWeatherResponse;
import com.raju.weather.dto.DailyWeatherResponse;
import com.raju.weather.dto.HourlyWeatherResponse;
import com.raju.weather.dto.WeatherHistoryResponse;
import com.raju.weather.entity.HourlyForecastEntity;
import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.daily.DailyWeather;
import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WeatherDataMapper {
	
	public CurrentWeatherResponse mapToCurrentResponse(WeatherData weatherData) {

		CurrentWeatherResponse weatherResponse = new CurrentWeatherResponse();

		weatherResponse.setCityName(weatherData.getName());
		weatherResponse.setCountry(weatherData.getSys().getCountry());
		weatherResponse.setTemperature(kelvinToCelsius(weatherData.getMain().getTemperature()) + "°C");
		weatherResponse.setFeelsLike(kelvinToCelsius(weatherData.getMain().getFeelsLike()) + "°C");

		if (weatherData.getWeather() != null && !weatherData.getWeather().isEmpty()) {
			weatherResponse.setWeatherDescription(capitalize(weatherData.getWeather().get(0).getDescription()));
			weatherResponse.setWeatherIcon(weatherData.getWeather().get(0).getIcon());
		}

		weatherResponse.setWindSpeed(weatherData.getWind().getSpeed() + "m/s");
		weatherResponse.setWindDirection(getWindDirection(weatherData.getWind().getDeg()));
		weatherResponse.setHumidity(weatherData.getMain().getHumidity() + "%");
		weatherResponse.setPressure(weatherData.getMain().getPressure() + "hPa");
		weatherResponse.setVisibility((weatherData.getVisibility() / 1000.0) + "km");
		weatherResponse.setObservationTime(formatUnixTime(weatherData.getDt()));

		return weatherResponse;

	}

	public List<DailyWeatherResponse> mapToDailyResponse(DailyWeatherData data) {

		List<DailyWeatherResponse> responses = new ArrayList<>();

		for (DailyWeather daily : data.getDailyWeather()) { // fetch the dailyweatherlist for different dates
			DailyWeatherResponse weatherResponse = new DailyWeatherResponse();

			weatherResponse.setDate(formatUnixTime(daily.getDt()));
			weatherResponse.setDescription(capitalize(daily.getWeather().get(0).getDescription()));
			weatherResponse.setIcon(daily.getWeather().get(0).getIcon());

			weatherResponse.setTempMax(kelvinToCelsius(daily.getTemp().getMax()) + "°C");
			weatherResponse.setTempMin(kelvinToCelsius(daily.getTemp().getMin()) + "°C");

			weatherResponse.setTempMorn(kelvinToCelsius(daily.getTemp().getMornning()) + "°C");
			weatherResponse.setTempDay(kelvinToCelsius(daily.getTemp().getDay()) + "°C");
			weatherResponse.setTempEve(kelvinToCelsius(daily.getTemp().getEvening()) + "°C");
			weatherResponse.setTempNight(kelvinToCelsius(daily.getTemp().getNight()) + "°C");

			weatherResponse.setHumidity(daily.getHumidity() + "%");
			weatherResponse.setPressure(daily.getPressure() + "hPa");
			weatherResponse.setWindSpeed(daily.getSpeed() + " m/s");
			weatherResponse.setWindDirection(getWindDirection(daily.getDeg()));

			weatherResponse.setSunrise(formatUnixTime(daily.getSunrise()));
			weatherResponse.setSunset(formatUnixTime(daily.getSunset()));

			responses.add(weatherResponse);

		}
		return responses;
	}
	
	public List<HourlyWeatherResponse> mapToHourlyResponse(HourlyWeatherData data) {
		List<HourlyWeatherResponse> responses = new ArrayList<>();
		for(WeatherData hourly : data.getList()) {
	    HourlyWeatherResponse res = new HourlyWeatherResponse();
	    
	    res.setCity(data.getCity().getName());
	    res.setDateTime(hourly.getDtText());
	    res.setDescription(capitalize(hourly.getWeather().get(0).getDescription()));
	    res.setIcon(hourly.getWeather().get(0).getIcon());
	    res.setTemp(kelvinToCelsius(hourly.getMain().getTemperature()) + "°C");
	    res.setTempMin(kelvinToCelsius(hourly.getMain().getTempMax()) + "°C");
	    res.setTempMax(kelvinToCelsius(hourly.getMain().getTempMin()) + "°C");
	    res.setFeelsLike(kelvinToCelsius(hourly.getMain().getFeelsLike()) + "°C");
	    res.setHumidity(hourly.getMain().getHumidity() + "%");
	    res.setPressure(hourly.getMain().getPressure() + " hPa");
	    res.setWindSpeed(hourly.getWind().getSpeed() + " m/s");
	    res.setWindDirection(getWindDirection(hourly.getWind().getDeg()));
	    res.setCloudiness(hourly.getClouds().getAll() + "%");
	    res.setRainProbability((int)(hourly.getPop() * 100) + "%");
	    
	    responses.add(res);
		}

	    return responses;
	}

	
	public List<WeatherHistoryResponse> mapToHistoryWeatherResponse(WeatherHistoryData data) {
		List<WeatherHistoryResponse> responses = new ArrayList<>();
		for(WeatherData history : data.getList()) {
	    WeatherHistoryResponse res = new WeatherHistoryResponse();
	    
	    res.setDateTime(formatUnixTime(history.getDt()));
	    res.setDescription(capitalize(history.getWeather().get(0).getDescription()));
	    res.setIcon(history.getWeather().get(0).getIcon());
	    res.setTemperature(kelvinToCelsius(history.getMain().getTemperature()) + "°C");
	    res.setFeelsLike(kelvinToCelsius(history.getMain().getFeelsLike()) + "°C");
	    res.setHumidity(history.getMain().getHumidity() + "%");
	    res.setWindSpeed(history.getWind().getSpeed() + " m/s");
	    res.setWindDirection(getWindDirection(history.getWind().getDeg()));
	    res.setCloudiness(history.getClouds().getAll() + "%");	    
	    responses.add(res);
		}

	    return responses;
	}
	
	public List<HourlyForecastEntity> mapToHourlyResponseEntity(HourlyWeatherData body) {
		
		List<HourlyForecastEntity> entityList = new ArrayList<>();
		
		for(WeatherData hourly : body.getList()) {
			
		HourlyForecastEntity   entity = new HourlyForecastEntity();
		
		entity.setCity(body.getCity().getName());
		entity.setTemperature(kelvinToCelsius(hourly.getMain().getTemperature()));
		entity.setFeelsLike(kelvinToCelsius(hourly.getMain().getFeelsLike()));
		entity.setHumidity(hourly.getMain().getHumidity());
		entity.setRainProbability(hourly.getPop()*100);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.parse(hourly.getDtText(), formatter);
		entity.setTimestamp(time);
		
		entity.setWeatherDescription(capitalize(hourly.getWeather().get(0).getDescription()));
		entity.setWeatherMain(hourly.getWeather().get(0).getMain());
		entity.setWindSpeed(hourly.getWind().getSpeed());
		entity.setHumidity(hourly.getMain().getHumidity());
		
		entityList.add(entity);
		
		
		}
		return entityList;
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
