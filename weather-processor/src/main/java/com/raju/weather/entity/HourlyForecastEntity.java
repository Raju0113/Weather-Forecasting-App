package com.raju.weather.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HourlyForecastEntity {
	
    @Id
    private LocalDateTime timestamp;
    @Id
    private String city;
    private Double temperature;
    private Double feelsLike;
    private String weatherMain;
    private String weatherDescription;
    private Double rainProbability;
    private Double windSpeed;
    private Integer humidity;
    private boolean sendAlert;
}
