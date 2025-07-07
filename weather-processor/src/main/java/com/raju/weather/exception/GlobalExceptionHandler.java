package com.raju.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(WeatherServiceException.class)
	public ResponseEntity<ErrorResponse> handleWeatherServiceException(WeatherServiceException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
		return new ResponseEntity<>(errorResponse, e.getHttpStatus());
	}
	
	//Generic Exception handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericExeption (Exception e) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
				ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
