package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IClientsDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClientsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDao implements IClientsDao {
    private static final Logger LOGGER = LogManager.getLogger(ClientsDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO clients VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE clients SET firstName = ? WHERE id = ?";
    final String DELETE = "DELETE FROM clients WHERE id = ? ";
    final String GET = "SELECT * FROM clients WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM clients";

    @Override
    public void createClients(ClientsModel clientsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, clientsModel.getId());
            statement.setString(2, clientsModel.getFirstName());
            statement.setString(3, clientsModel.getLastName());
            statement.setString(4, clientsModel.getPassportNum());
            statement.setString(5, clientsModel.getPhoneNum());
            int i = statement.executeUpdate();
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
    public void updateClients(ClientsModel clientsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, clientsModel.getFirstName());
            statement.setInt(2, clientsModel.getId());
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
    public void deleteClientsById(ClientsModel clientsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, clientsModel.getId());
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
    public ClientsModel getClientsById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        ClientsModel clientsModel = new ClientsModel();
        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                clientsModel.setId(result.getInt(1));
                clientsModel.setFirstName(result.getString(2));
                clientsModel.setLastName(result.getString(3));
                clientsModel.setPassportNum(result.getString(4));
                clientsModel.setPhoneNum(result.getString(5));
                clientsModel.toString();
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
        return clientsModel;
    }

    @Override
    public List<ClientsModel> getALLClients() {
        ArrayList<ClientsModel> clientsModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                ClientsModel clientsModel = new ClientsModel();
                clientsModel.setId(result.getInt(1));
                clientsModel.setFirstName(result.getString(2));
                clientsModel.setLastName(result.getString(3));
                clientsModel.setPassportNum(result.getString(4));
                clientsModel.setPhoneNum(result.getString(5));
                clientsModels.add(clientsModel);
                clientsModel.toString();
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
        return clientsModels;
    }
}
