///*
// * This class interacts with external api of the OpenWeather to get the weather info
// * for that it required latitude and longitude and api key to make api call 
// * this class getting the latitude and longitude from the  Controller class and this class makes api call and return reponse to controller
// * */
//package com.raju.weather.client;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.raju.weather.constants.ErrorCodeEnum;
//import com.raju.weather.exception.WeatherServiceException;
//import com.raju.weather.weatherFetcher.model.WeatherData;
//import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
//import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
//import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class WeatherFetcherClient {
//	
//		private final RestClient restClient;
//		
//		@Value("${weather.fetcher-service.current-url}")//@Value annotation is used to read the values form the property files
//		private String currentApiUrl;
//		
//		@Value("${weather.fetcher-service.hourly-url}")
//		private String hourlyApiUrl;
//		
//		@Value("${weather.fetcher-service.daily-url}")
//		private String  dailyApiUrl;
//		
//		@Value("${weather.fetcher-service.history-url}")
//		private String historyUrl;
//		
//		public ResponseEntity<WeatherData> getCurrentWeatherByCity(String city){
//						
//			String url = currentApiUrl+"="+city;
//			
////			WeatherData response = restClient.get()
////					               .uri(url)
////					               .retrieve()
////					               .body(WeatherData.class);   //body method only returns body of the reponse it doesn't returns http status codes and headers
//	
//	   try {		
//			   return restClient.get()
//		                        .uri(url)
//		                        .retrieve()
//		                        .toEntity(WeatherData.class);
//			            
//	}catch(RestClientException e) {
//		throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
//				"Failed to fetch weather for "+city,
//				HttpStatus.NOT_FOUND);
//	}
//}
//		
//		public ResponseEntity<HourlyWeatherData> getHourlyWeatherByCity(String city,Integer count){
//			
//			UriComponentsBuilder uriBuilder = UriComponentsBuilder
//	                  .fromUriString(hourlyApiUrl)
//	                  .queryParam("city",city);
//			
//			if (count != null) {
//				uriBuilder.queryParam("count", count);
//			}
//
//			String url = uriBuilder.toUriString();
//			
//		try {	
//			return  restClient.get()
//		                      .uri(url)
//		                      .retrieve()
//		                      .toEntity(HourlyWeatherData.class);
//			
//		}catch(RestClientException e) {
//				
//				throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
//						"Failed to fetch weather for "+city,
//						HttpStatus.NOT_FOUND);
//			}
//	}
//
//		public ResponseEntity<DailyWeatherData> getDailyWeatherByCity(String city, Integer count) {
//			
//			UriComponentsBuilder uriBuilder = UriComponentsBuilder
//	                  .fromUriString(dailyApiUrl)
//	                  .queryParam("city",city);
//			
//			if (count != null) {
//				uriBuilder.queryParam("count", count);
//			}
//
//			String url = uriBuilder.toUriString();
//			
//			try {
//			     return  restClient.get()
//		                            .uri(url)
//		                            .retrieve()
//		                            .toEntity(DailyWeatherData.class);
//			}catch(RestClientException e) {
//				throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
//						"Failed to fetch weather for "+city,
//						HttpStatus.NOT_FOUND);
//			}
//	
//		}
//
//		public ResponseEntity<WeatherHistoryData> getWeatherHistory(String city, String startDate, String endDate, Integer count) {
//			//?city=Visakhapatnam&startDate=10-07-2025&endDate=13-07-2025
//			
//			UriComponentsBuilder uriBuilder = UriComponentsBuilder
//					                          .fromUriString(historyUrl)
//					                          .queryParam("city", city)
//					                          .queryParam("startDate",startDate);
//			if (endDate != null) {
//			    uriBuilder.queryParam("endDate", endDate);
//			} else if (count != null) {
//			    uriBuilder.queryParam("cnt", count);
//			}
//		
//			String url = uriBuilder.toUriString();
//		
//			try {
//	        	return  restClient.get()
//                    .uri(url)
//                    .retrieve()
//                    .toEntity(WeatherHistoryData.class);
//			}catch(RestClientException e) {
//				 log.error("Error fetching current weather for city '{}': {}", city, e.getMessage(), e);
//				throw new WeatherServiceException(ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
//						"Failed to fetch weather for "+city,
//						HttpStatus.NOT_FOUND);
//			}
//                     
//		}
//}




package com.raju.weather.client;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;
import com.raju.weather.weatherFetcher.model.WeatherData;
import com.raju.weather.weatherFetcher.model.daily.DailyWeatherData;
import com.raju.weather.weatherFetcher.model.history.WeatherHistoryData;
import com.raju.weather.weatherFetcher.model.hourly.HourlyWeatherData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherFetcherClient {

    private final RestClient restClient;

    @Value("${weather.fetcher-service.current-url}")
    private String currentApiUrl;

    @Value("${weather.fetcher-service.hourly-url}")
    private String hourlyApiUrl;

    @Value("${weather.fetcher-service.daily-url}")
    private String dailyApiUrl;

    @Value("${weather.fetcher-service.history-url}")
    private String historyUrl;

    public ResponseEntity<WeatherData> getCurrentWeatherByCity(String city) {
        String url = currentApiUrl + "=" + city;
        return handleApiCall(() ->
                restClient.get().uri(url).retrieve().toEntity(WeatherData.class), city);
    }

    public ResponseEntity<HourlyWeatherData> getHourlyWeatherByCity(String city, Integer count) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(hourlyApiUrl)
                .queryParam("city", city);

        if (count != null) {
            uriBuilder.queryParam("count", count);
        }

        String url = uriBuilder.toUriString();

        return handleApiCall(() ->
                restClient.get().uri(url).retrieve().toEntity(HourlyWeatherData.class), city);
    }

    public ResponseEntity<DailyWeatherData> getDailyWeatherByCity(String city, Integer count) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(dailyApiUrl)
                .queryParam("city", city);

        if (count != null) {
            uriBuilder.queryParam("count", count);
        }

        String url = uriBuilder.toUriString();

        return handleApiCall(() ->
                restClient.get().uri(url).retrieve().toEntity(DailyWeatherData.class), city);
    }

    public ResponseEntity<WeatherHistoryData> getWeatherHistory(String city, String startDate, String endDate, Integer count) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(historyUrl)
                .queryParam("city", city)
                .queryParam("startDate", startDate);

        if (endDate != null) {
            uriBuilder.queryParam("endDate", endDate);
        } else if (count != null) {
            uriBuilder.queryParam("cnt", count);
        }

        String url = uriBuilder.toUriString();

        return handleApiCall(() ->
                restClient.get().uri(url).retrieve().toEntity(WeatherHistoryData.class), city);
    }
    

    private <T> ResponseEntity<T> handleApiCall(Supplier<ResponseEntity<T>> supplier, String city) {
        try {
            return supplier.get();
        } catch (HttpClientErrorException.NotFound e) {
            // City not found (404)
            throw new WeatherServiceException(
                    ErrorCodeEnum.CITY_NOT_FOUND.getCode(),
                    "City not found: " + city,
                    HttpStatus.NOT_FOUND);
        } catch (ResourceAccessException e) {
            // Timeout or connection issue
            throw new WeatherServiceException(
                    ErrorCodeEnum.SERVICE_UNAVAILABLE.getCode(),
                    ErrorCodeEnum.SERVICE_UNAVAILABLE.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            // Generic internal server error
            throw new WeatherServiceException(
                    ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
                    "Unexpected error while fetching weather for: " + city,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

