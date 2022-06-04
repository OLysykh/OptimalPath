package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.AirlinesModel;
import com.solvd.optimalpath.models.CitiesModel;

import java.util.List;

public interface IAirlinesDao {
    void createAirlines(AirlinesModel airlinesModel);

    void updateAirlines(AirlinesModel airlinesModel);

    void deleteAirlinesById(AirlinesModel airlinesModel);

    AirlinesModel getAirlinesById(int id);

    List<AirlinesModel> getALLAirlines();
}
