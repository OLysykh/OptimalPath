package com.solvd.optimalpath.interfaces;

import com.solvd.optimalpath.models.ClientsModel;
import com.solvd.optimalpath.models.TicketsModel;

import java.util.List;

public interface ITicketsDao {
    void createTickets(TicketsModel ticketsModel);

    void updateTickets(TicketsModel ticketsModel);

    void deleteTicketsById(TicketsModel ticketsModel);

    TicketsModel getTicketsById(int id);

    List<TicketsModel> getALLTickets();
}
