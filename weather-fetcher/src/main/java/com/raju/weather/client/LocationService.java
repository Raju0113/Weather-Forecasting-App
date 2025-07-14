package com.raju.weather.client;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;
import com.raju.weather.openWeather.model.GeoLocation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

	private final RestClient restClient;
	
	@Value("${openweather.api.geo-url}")
	private String geoUrl;
	
	@Value("${openweather.api.key}")
	private String apiKey;
	
	private Map<String,GeoLocation> coordinatesCache = new ConcurrentHashMap<>(); //simple cache for storing the coordinates
	
	public GeoLocation getCoordinatesByCity(String city) {

//		String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city
//				+ "&appid="+ Constants.access_Key;
		
		if(coordinatesCache.containsKey(city)) {
			log.info("Returning coordinates for '{}' from cache.", city);
			return coordinatesCache.get(city);
			
		}
		
		String url = geoUrl+"?q="+city+"&appid="+apiKey;

		ResponseEntity<GeoLocation[]> responseEntity = restClient.get()
				                                 .uri(url)
				                                 .retrieve()
				                                 .toEntity(GeoLocation[].class);

		if(responseEntity.getStatusCode().is2xxSuccessful()) {
		GeoLocation[] locations = responseEntity.getBody();
		
		if (locations!= null && locations.length > 0) {

			log.info("Latitude:{} ", locations[0].getLat());
			log.info("Longitude:{} ", locations[0].getLon());
						
			coordinatesCache.put(city, locations[0]);//adding coordinates in the map
			
			log.info("Returning coordinates for '{}' from API", city);
			
			return locations[0];
		}
		}else {
			throw new WeatherServiceException(ErrorCodeEnum.GEOLOCATION_API_FAILED.getCode(),
					ErrorCodeEnum.GEOLOCATION_API_FAILED.getMessage(),
					HttpStatus.NOT_FOUND);
		}
		return null;

	}

}
