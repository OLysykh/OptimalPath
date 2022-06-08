package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.CitiesModel;

import java.util.List;

public interface IAnimalsDao {
    void createAnimals(AnimalsModel animalsModel);

    void updateAnimals(AnimalsModel animalsModel);

    void deleteAnimalsById(AnimalsModel animalsModel);

    AnimalsModel getAnimalsById(int id);

    List<AnimalsModel> getALLAnimals();
}
