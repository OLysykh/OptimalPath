package com.solvd.optimalpath.services.algorythm.Weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@JsonDeserialize
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    final Logger LOGGER = LogManager.getLogger(Weather.class);

    @JsonProperty
    private String main; //clouds
    @JsonProperty
    private String description; //more detailed about clouds

    public Weather() {
    }

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return   "on the sky there is/are ='" + main  +
                ", in details='" + description;
    }
}
