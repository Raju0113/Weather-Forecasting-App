package com.raju.weather.openWeather.model;

import lombok.Data;

@Data
public class Temp {

    private Double day;

    private Double min;

    private Double max;

    private Double night;

    private Double eve;

    private Double morn;
}

