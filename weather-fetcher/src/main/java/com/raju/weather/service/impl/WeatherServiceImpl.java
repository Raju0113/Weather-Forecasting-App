//package com.raju.weather.service.impl;
//
//import java.time.LocalDate;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import com.raju.weather.client.LocationService;
//import com.raju.weather.client.OpenWeatherClient;
//import com.raju.weather.constants.ErrorCodeEnum;
//import com.raju.weather.exception.WeatherServiceException;
//import com.raju.weather.openWeather.model.CurrentWeatherResponse;
//import com.raju.weather.openWeather.model.GeoLocation;
//import com.raju.weather.openWeather.model.WeatherHistoryResponse;
//import com.raju.weather.service.interfaces.WeatherService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class WeatherServiceImpl implements WeatherService {
//	
//	private LocationService locationService;
//	
//	private OpenWeatherClient openWeatherClient;
//
//
//	WeatherServiceImpl(LocationService locationService , OpenWeatherClient openWeatherClient) { // Constructor injection
//		
//		this.locationService = locationService;
//		this.openWeatherClient = openWeatherClient;
//	}
//
//	@Override
//	public ResponseEntity<CurrentWeatherResponse> getWeather(String city) {
//		
//		//validation for city  //TODO -move to another validations class
//		if(city == null || city.trim().isEmpty() || !city.matches("^[a-zA-Z\\s-]+$")) {
//			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
//					       ErrorCodeEnum.INVALID_INPUT.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//		
//		StopWatch watch = new StopWatch();  //for testing purpose
//		watch.start();
//		
//		GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
//		watch.stop();
//		log.info("Time taken to fetch coordinates: {} ms", watch.getTotalTimeMillis());
//		
//		 if (geoLocation == null) {
//	            log.warn("No coordinates found for city: {}", city);
//	            
////	            return ResponseEntity.status(404).body("City not found: " + city);  //instead of returning like this we better to throw excptions
//	            throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
//	            		    ErrorCodeEnum.CITY_NOT_FOUND.getMessage(),
//	            		            HttpStatus.NOT_FOUND);
//	        }
//		 
//		 log.info("Latitude: {} Longitude: {}", geoLocation.getLat(), geoLocation.getLon());
//		 
//		    ResponseEntity<CurrentWeatherResponse> weatherData = openWeatherClient.
//		    		                  getWeatherByCoordinates(geoLocation.getLat(), geoLocation.getLon());//giving coordinates and getting weather data
//	 
//		            return weatherData;
//		
//	}
//
//	@Override
//	public ResponseEntity<WeatherHistoryResponse> getWeatherHistory(String city,String startDate,String endDate,Integer cnt) {
//
//		// validation for city //TODO
//		if (city == null || city.trim().isEmpty() || !city.matches("^[a-zA-Z\\s-]+$")) {
//			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
//					ErrorCodeEnum.INVALID_INPUT.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//		
//		if (startDate == null || startDate.trim().isEmpty()) {
//		    throw new WeatherServiceException(
//		        ErrorCodeEnum.INVALID_INPUT.getCode(),
//		        "Start date is required",
//		        HttpStatus.BAD_REQUEST);
//		}
//		
//		if ( //both endDate and cnt should not be empty and availble mean either only one should be available	
//			((endDate == null || endDate.trim().isEmpty()) && cnt == null) || //one must be available
//		    ((endDate != null && !endDate.trim().isEmpty()) && cnt != null)   //but not both
//			){
//			
//		    throw new WeatherServiceException(
//		        ErrorCodeEnum.INVALID_INPUT.getCode(),
//		        "Either end date or count (cnt) must be provided",
//		        HttpStatus.BAD_REQUEST);
//		}
//			
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//TODO add time later
//
//		    // 2. Parse the input strings into LocalDate
//		    LocalDate start = LocalDate.parse(startDate, formatter);
//		 // 3. Convert both dates to Unix timestamps (UTC)
//		    Long startUnix = start.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
//		    
//		    LocalDate end=null;
//		    Long endUnix = null;
//		    
//		    if(endDate!=null && !endDate.trim().isEmpty()) { 
//		     end = LocalDate.parse(endDate, formatter);      //only parse if endDate is not null other wise bypass this logic to avoid null pointer excepetion
//		      endUnix = end.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
//		    }
//		    
//		    GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
//		    
//		    ResponseEntity<WeatherHistoryResponse> weatherHistory = openWeatherClient.getWeatherHistoryByUnixTime(geoLocation.getLat(), geoLocation.getLon(),startUnix,endUnix,cnt);
//		
//
//		return weatherHistory;
//	}
//}
	


package com.raju.weather.service.impl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.raju.weather.client.LocationService;
import com.raju.weather.client.OpenWeatherClient;
import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;
import com.raju.weather.openWeather.model.GeoLocation;
import com.raju.weather.openWeather.model.current.CurrentWeatherResponse;
import com.raju.weather.openWeather.model.daily.DailyWeatherResponse;
import com.raju.weather.openWeather.model.history.WeatherHistoryResponse;
import com.raju.weather.openWeather.model.hourly.HourlyWeatherResponse;
import com.raju.weather.service.interfaces.WeatherService;
import com.raju.weather.validation.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
	
	private final LocationService locationService;
	
	private final OpenWeatherClient openWeatherClient;
	
	private final Validator validator;


	@Override
	public ResponseEntity<CurrentWeatherResponse> getWeather(String city) {
		
		validator.validCity(city); //validing the city name
		
		StopWatch watch = new StopWatch();  //for testing purpose
		watch.start();
		
		GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
		
		watch.stop();
		log.info("Time taken to fetch coordinates: {} ms", watch.getTotalTimeMillis());
		
		 
		 log.info("Latitude: {} Longitude: {}", geoLocation.getLat(), geoLocation.getLon());
		 
		           return openWeatherClient. getWeatherByCoordinates(
		        		  geoLocation.getLat(), geoLocation.getLon());//giving coordinates and getting weather data        
		
	}


	@Override
	public ResponseEntity<WeatherHistoryResponse> getWeatherHistory(String city,String startDate,String endDate,Integer cnt) {

		validator.validCity(city);
		validator.validStartDate(startDate);
		validator.validEndDateOrCnt(endDate, cnt);
		
		if(endDate!=null) {
			validator.validStartDateAndEndDate(startDate, endDate);
		}
			
		    Map<String, Long> unixDates= validator.dateToUnix(startDate,endDate);
		   
		    GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
		    
		    ResponseEntity<WeatherHistoryResponse> response =  openWeatherClient.getWeatherHistoryByUnixTime(
		    	geoLocation.getLat(), geoLocation.getLon(),
		    	unixDates.get("startDate"),unixDates.get("endDate"),cnt);
		
                   return response;
	}


	@Override
	public ResponseEntity<HourlyWeatherResponse> getWeatherHourly(String city,Integer count) {
		validator.validCity(city);
		GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
		
		 ResponseEntity<HourlyWeatherResponse> response =  openWeatherClient.getWeatherHourlyByCoordinates(
			    	                        geoLocation.getLat(), geoLocation.getLon(),count);

		return response;
	}


	@Override
	public ResponseEntity<DailyWeatherResponse> getWeatherDaily(String city, Integer count) {
		validator.validCity(city);
		validator.validCount(count);
		GeoLocation geoLocation = locationService.getCoordinatesByCity(city);
		
		 ResponseEntity<DailyWeatherResponse> response =  openWeatherClient.getWeatherDailyByCoordinates(
			    	                        geoLocation.getLat(), geoLocation.getLon(),count);

		return response;
		
	}
	
}
