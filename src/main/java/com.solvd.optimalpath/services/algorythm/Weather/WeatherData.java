package com.solvd.optimalpath.services.algorythm.Weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty
    private List<Weather> weather;
    @JsonProperty
    private Main main;

    public WeatherData() {
    }

    public WeatherData(List<Weather> weather, Main main) {
        this.weather = weather;
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Please take a look on the Weather in your city destination: " +
                weather +
                main;
    }
}
