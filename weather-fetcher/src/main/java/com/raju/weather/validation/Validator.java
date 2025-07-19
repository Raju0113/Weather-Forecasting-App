package com.raju.weather.validation;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.exception.WeatherServiceException;

@Component
public class Validator {
	
	public  void validCity(String city) {
		
		//validating the city  
		if(city == null || city.trim().isEmpty() || !city.matches("^[a-zA-Z\\s-]+$")) {
			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
					       ErrorCodeEnum.INVALID_INPUT.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	public void validCount(Integer count) { //Validating count for daily api call
		
		if( count <= 0 || count >=17) {
			throw new WeatherServiceException(ErrorCodeEnum.INVALID_INPUT.getCode(),
					       "Count must be from 1 to 16",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	public void validStartDate(String startDate) {
		if (startDate == null || startDate.trim().isEmpty()) {
		    throw new WeatherServiceException(
		        ErrorCodeEnum.INVALID_INPUT.getCode(),
		        "Start date is required",
		        HttpStatus.BAD_REQUEST);
		}
	}
	
	public void validEndDateOrCnt(String endDate, Integer cnt) {
		if ( //both endDate and cnt should not be empty and availble mean either only one should be available	
			((endDate == null || endDate.trim().isEmpty()) && cnt == null) || //one must be available
		    ((endDate != null && !endDate.trim().isEmpty()) && cnt != null)   //but not both
			){
			
		    throw new WeatherServiceException(
		        ErrorCodeEnum.INVALID_INPUT.getCode(),
		        "Either end date or count (cnt) must be provided",
		        HttpStatus.BAD_REQUEST);
		}
	}
	
	public Map<String,Long> dateToUnix(String startDate,String endDate){
		
		Map<String,Long> unixDates = new HashMap<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//TODO add time later

	    // 2. Parse the input strings into LocalDate
	    LocalDate start = LocalDate.parse(startDate, formatter);
	    
	 // 3. Convert both dates to Unix timestamps (UTC)
	    Long startUnix = start.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
	    
	    unixDates.put("startDate", startUnix);
	    
	    LocalDate end=null;
	    Long endUnix = null;
	    
	    if(endDate!=null && !endDate.trim().isEmpty()) { 
	     end = LocalDate.parse(endDate, formatter);      //only parse if endDate is not null other wise bypass this logic to avoid null pointer excepetion
	      endUnix = end.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
	      unixDates.put("endDate", endUnix);
	    }
	    		
		return unixDates;
		
	}
	
public void validStartDateAndEndDate(String startDate, String endDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date1 = LocalDate.parse(startDate, formatter);
        LocalDate date2 = LocalDate.parse(endDate, formatter);

		     if (date1.isAfter(date2)) {
		            throw new WeatherServiceException(
		    		      ErrorCodeEnum.INVALID_INPUT.getCode(),
		    		      "start Date must be before the end Date",
		    		      HttpStatus.BAD_REQUEST);

				}

			}

}
