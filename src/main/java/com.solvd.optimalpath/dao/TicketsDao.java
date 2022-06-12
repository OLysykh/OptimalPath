package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.ITicketsDao;

import com.solvd.optimalpath.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDao implements ITicketsDao {
    private static final Logger LOGGER = LogManager.getLogger(TicketsDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO tickets VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE tickets SET seatsNum = ? WHERE id = ?";
    final String DELETE = "DELETE FROM tickets WHERE id = ? ";
    final String GET = "SELECT * FROM tickets WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM tickets";

    @Override
    public void createTickets(TicketsModel ticketsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
//            statement.setInt(1, ticketsModel.getId());
            statement.setInt(1, ticketsModel.getAirlinesModel().getId());
            statement.setInt(2, ticketsModel.getCitiesModel().getId());
            statement.setInt(3, ticketsModel.getClassTypesModel().getId());
            statement.setInt(4, ticketsModel.getClientsModel().getId());
            statement.setString(5, ticketsModel.getCityArrival());
            statement.setString(6, ticketsModel.getSeatsNum());
            statement.setInt(7, ticketsModel.getPrice());
//            statement.setInt(7, ticketsModel.getPrice());
            int i = statement.executeUpdate();
            LOGGER.info(i + " records inserted");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                DataBaseConnection.close(dbConnect);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateTickets(TicketsModel ticketsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, ticketsModel.getSeatsNum());
            statement.setInt(2, ticketsModel.getId());
            int i = statement.executeUpdate();
            LOGGER.info(i + " records updated");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                DataBaseConnection.close(dbConnect);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTicketsById(TicketsModel ticketsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, ticketsModel.getId());
            int i = statement.executeUpdate();
            LOGGER.info(i + " records deleted");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                DataBaseConnection.close(dbConnect);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TicketsModel getTicketsById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        TicketsModel ticketsModel = new TicketsModel();
        AirlinesModel airlinesModel = new AirlinesModel();
        CitiesModel citiesModel = new CitiesModel();
        ClassTypesModel classTypesModel = new ClassTypesModel();
        ClientsModel clientsModel = new ClientsModel();

        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                ticketsModel.setId(result.getInt(1));
                airlinesModel.setId(result.getInt(2));
                ticketsModel.setAirlinesModel(airlinesModel);
                citiesModel.setId(result.getInt(3));
                ticketsModel.setCitiesModel(citiesModel);
                classTypesModel.setId(result.getInt(4));
                ticketsModel.setClassTypesModel(classTypesModel);
                clientsModel.setId(result.getInt(5));
                ticketsModel.setClientsModel(clientsModel);
                ticketsModel.setDestinationCity(result.getString(6));
                ticketsModel.setSeatsNum(result.getString(7));
                ticketsModel.setPrice(result.getInt(8));
                ticketsModel.toString();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                DataBaseConnection.close(dbConnect);
                statement.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ticketsModel;
    }

    @Override
    public List<TicketsModel> getALLTickets() {
        ArrayList<TicketsModel> ticketsModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                TicketsModel ticketsModel = new TicketsModel();
                ticketsModel.setId(result.getInt(1));
                AirlinesDao airlinesDao = new AirlinesDao();
                ticketsModel.setAirlinesModel(airlinesDao.getAirlinesById(result.getInt("airlinesId")));
                CitiesDao citiesDao = new CitiesDao();
                ticketsModel.setCitiesModel(citiesDao.getCitiesById(result.getInt("citiesId")));
                ClassTypesDao classTypesDao = new ClassTypesDao();
                ticketsModel.setClassTypesModel(classTypesDao.getClassTypesById(result.getInt("classTypesId")));
                ClientsDao clientsDao = new ClientsDao();
                ticketsModel.setClientsModel(clientsDao.getClientsById(result.getInt("clientsId")));
                ticketsModel.setDestinationCity(result.getString(6));
                ticketsModel.setSeatsNum(result.getString(7));
                ticketsModel.setPrice(result.getInt(8));
                ticketsModels.add(ticketsModel);
                ticketsModels.toString();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                DataBaseConnection.close(dbConnect);
                statement.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ticketsModels;
    }

}



