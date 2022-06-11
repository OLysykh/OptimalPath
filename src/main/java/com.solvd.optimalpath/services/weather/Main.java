package com.solvd.optimalpath.services.weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    @JsonProperty
    private double temp;
    @JsonProperty("feels_like")
    private double feelslike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    @JsonProperty
    private int pressure;
    @JsonProperty
    private int humidity;

    public Main() {
    }

    public Main(double temp, double feelslike, double tempMin, double tempMax, int pressure, int humidity) {
        this.temp = temp;
        this.feelslike = feelslike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return
                "temperature is = " + temp +"C "+
                ", feels like= " + feelslike +"C "+
                ", pressure=" + pressure + "Mpa" +
                ", humidity= " + humidity + " %";
    }
}
