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

import com.raju.weather.openWeather.model.WeatherData;

@Service
public class OpenWeatherClient {
	
		RestClient restClient;
		
		@Value("${openweather.api.weather-url}")//@Value annotation is used to read the values form the property files
		private String apiUrl;
		
		@Value("${openweather.api.key}")
		private String apiKey;

		OpenWeatherClient(RestClient restClient) {
			this.restClient = restClient;
		}
		public ResponseEntity<WeatherData> getWeatherByCoordinates(double lat,double lon) {
			
			
//			String url="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+ Constants.access_Key;
			
			String url = apiUrl+"?lat="+lat+"&lon="+lon+"&appid="+apiKey;
			
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
