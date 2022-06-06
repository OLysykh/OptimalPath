package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClientsModel;

import java.util.List;

public interface IClientsDao {
    void createClients(ClientsModel clientsModel);

    void updateClients(ClientsModel clientsModel);

    void deleteClientsById(ClientsModel clientsModel);

    ClientsModel getClientsById(int id);

    List<ClientsModel> getALLClients();
}
