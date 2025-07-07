package com.raju.weather.constants;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    CITY_NOT_FOUND("E001", "City not found"),
    GEOLOCATION_API_FAILED("E002","Coordinates not found for the given city"),
    WEATHER_API_FAILED("E003", "Failed to get weather information"),
    INVALID_INPUT("E004", "Invalid input"),
    INTERNAL_SERVER_ERROR("E999", "Internal server error");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
