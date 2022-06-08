package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.CitiesModel;

import java.util.List;

public interface ICitiesDao {

    void createCities(CitiesModel citiesModel);

    void updateCities(CitiesModel citiesModel);

    void deleteCitiesById(CitiesModel citiesModel);

    CitiesModel getCitiesById(int id);

    List<CitiesModel> getALLCities();
}
