package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClassTypesModel;

import java.util.List;

public interface IClassTypesDao {
    void createClassTypes(ClassTypesModel classTypesModel);

    void updateClassTypes(ClassTypesModel classTypesModel);

    void deleteClassTypesById(ClassTypesModel classTypesModel);

    ClassTypesModel getClassTypesById(int id);

    List<ClassTypesModel> getALLClassTypes();
}
