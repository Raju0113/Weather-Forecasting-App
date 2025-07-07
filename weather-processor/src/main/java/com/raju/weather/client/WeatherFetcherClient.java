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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.weather.weatherFetcher.model.WeatherData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherFetcherClient {
	
		private final RestClient restClient;
		
		@Value("${weather.fetcher-service.url}")//@Value annotation is used to read the values form the property files
		private String apiUrl;
		
		public ResponseEntity<WeatherData> getWeatherByCity(String city){
						
			String url = apiUrl+"="+city;
			
//			WeatherData response = restClient.get()
//					               .uri(url)
//					               .retrieve()
//					               .body(WeatherData.class);   //body method only returns body of the reponse it doesn't returns http status codes and headers
	
			
			ResponseEntity<WeatherData>response = restClient.get()
		                                          .uri(url)
		                                          .retrieve()
		                                          .toEntity(WeatherData.class);

			
			
			return response;
	
	
		
	}

}
