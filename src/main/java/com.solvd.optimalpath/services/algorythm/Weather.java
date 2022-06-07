package com.solvd.optimalpath.services.algorythm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    @JsonProperty("main")
    private String sky;
    @JsonProperty
    private String description;
}
