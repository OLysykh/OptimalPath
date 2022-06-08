package com.solvd.optimalpath.services.algorythm;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CityTemperature {

    final Logger LOGGER = LogManager.getLogger(CityTemperature.class);
    @JsonProperty
double temp;
    @JsonProperty
double feels_like;
    @JsonProperty
int pressure;
    @JsonProperty
int humidity;
    @JsonProperty
    List<Weather> weather;

}
