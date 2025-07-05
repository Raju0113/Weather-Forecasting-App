package com.raju.weather.pojo;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private String errorCode;
	private String errorMessage; 

	public ErrorResponse(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;

	}

}
