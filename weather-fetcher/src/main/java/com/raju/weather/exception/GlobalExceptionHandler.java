package com.raju.weather.exception;

import java.net.SocketTimeoutException;

import org.apache.hc.client5.http.ConnectTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import com.raju.weather.constants.ErrorCodeEnum;
import com.raju.weather.pojo.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(WeatherServiceException.class)
	public ResponseEntity<ErrorResponse> handleWeatherServiceException(WeatherServiceException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
		return new ResponseEntity<>(errorResponse, e.getHttpStatus());
	}
	
	//for timedout cases
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex) {
	    Throwable cause = ex.getCause();
	    String message = cause instanceof ConnectTimeoutException ? "Connection timeout" :
	                     cause instanceof SocketTimeoutException ? "Read timeout" :
	                     "Resource access error";

	    return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
	            .body(new ErrorResponse("408", message));
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
