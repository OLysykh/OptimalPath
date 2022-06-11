package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.AdministratorsModel;
import com.solvd.optimalpath.models.AirlinesModel;

import java.util.List;

public interface IAdministratorsDao {
    void createAdministrators(AdministratorsModel administratorsModel);

    void updateAdministrators(AdministratorsModel administratorsModel);

    void deleteAdministratorsById(AdministratorsModel administratorsModel);

    AdministratorsModel getAdministratorsById(int id);

    List<AdministratorsModel> getALLAdministrators();

    AdministratorsModel getAdministratorsByName(String adminName);


}
