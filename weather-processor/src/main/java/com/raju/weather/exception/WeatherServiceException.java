package com.raju.weather.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class WeatherServiceException extends RuntimeException{
	private final String errorCode;
	private final String errorMessage;
	private HttpStatus httpStatus;
	
	public WeatherServiceException(String errorCode, 
			                      String errorMessage, HttpStatus httpStatus) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}
	
	

}
