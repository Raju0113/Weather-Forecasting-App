/*
 * This class interacts with external api of the OpenWeather to get the weather info
 * for that it required latitude and longitude and api key to make api call 
 * this class getting the latitude and longitude from the  Controller class and this class makes api call and return reponse to controller
 * */
package com.raju.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.raju.weather.openWeather.model.CurrentWeatherResponse;
import com.raju.weather.openWeather.model.DailyWeatherResponse;
import com.raju.weather.openWeather.model.HourlyWeatherResponse;
import com.raju.weather.openWeather.model.WeatherHistoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherClient {
	
		private final RestClient restClient;
		
		@Value("${openweather.api.key}")
		private String apiKey;
		
		@Value("${openweather.api.weather-url}")//@Value annotation is used to read the values form the property files
		private String currentApiUrl;
		
		@Value("${openweather.api.hostory-url}")
		private String historyApiUrl;
		
		@Value("${openweather.api.hourly-url}")
		private String hourlyApiUrl;
		
		@Value("${openweather.api.daily-url}")
		private String dailyApiUrl;
		
		
		public ResponseEntity<CurrentWeatherResponse> getWeatherByCoordinates(double lat,double lon) {
			
			
//			String url="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+ Constants.access_Key;
			
			String url = currentApiUrl+"?lat="+lat+"&lon="+lon+"&appid="+apiKey;
			
//			WeatherData response = restClient.get()
//					               .uri(url)
//					               .retrieve()
//					               .body(WeatherData.class);   //body method only returns body of the reponse it doesn't returns http status codes and headers
	
			return restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(CurrentWeatherResponse.class);
			
	}
		
		
	public ResponseEntity<WeatherHistoryResponse> getWeatherHistoryByUnixTime(double lat, double lon, long startDate, Long endDate, Integer cnt) {

//	https://history.openweathermap.org/data/2.5/history/city?lat={lat}&lon={lon}&type=hour&start={start}&end={end}&appid={API key}

		UriComponentsBuilder uriBuilder = UriComponentsBuilder //building url
		        .fromUriString(historyApiUrl)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon)
		        .queryParam("type", "hour")
		        .queryParam("start", startDate)
		        .queryParam("appid", apiKey);

		if (endDate != null) {
		    uriBuilder.queryParam("end", endDate);
		} else if (cnt != null) {
		    uriBuilder.queryParam("cnt", cnt);
		}

		String url = uriBuilder.toUriString();
		log.info("history url: {}",url);

		        ResponseEntity<WeatherHistoryResponse> response = restClient.get()
                      .uri(url)
                      .retrieve()
                      .toEntity(WeatherHistoryResponse.class);
		        
		        return response;
			
	}
	public ResponseEntity<HourlyWeatherResponse> getWeatherHourlyByCoordinates(double lat, double lon, Integer count) {
		
                   //		?lat={lat}&lon={lon}&appid={API key}
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				                         .fromUriString(hourlyApiUrl)
				                         .queryParam("lat", lat)
				                         .queryParam("lon", lon)
				                         .queryParam("appid", apiKey);
		if(count!=null) {
			uriBuilder.queryParam("cnt", count);
		}
		
		    String url = uriBuilder.toUriString();
		    log.info("Hourly url: {}",url);
		    
		    ResponseEntity<HourlyWeatherResponse> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(HourlyWeatherResponse.class);
		
		       return response;
	}


	public ResponseEntity<DailyWeatherResponse> getWeatherDailyByCoordinates(double lat, double lon, Integer count) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				                  .fromUriString(dailyApiUrl)
				                  .queryParam("lat", lat)
				                  .queryParam("lon", lon)
				                  .queryParam("appid", apiKey);
		if (count != null) {
			uriBuilder.queryParam("cnt", count);
		}

		String url = uriBuilder.toUriString();
		log.info("Hourly url: {}", url);

		ResponseEntity<DailyWeatherResponse> response = restClient
				.get()
				.uri(url)
				.retrieve()
				.toEntity(DailyWeatherResponse.class);

		return response;
		
		
		
	}
}



















