package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.configuration.DataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitiesDao implements ICitiesDao {
    private static final Logger LOGGER = LogManager.getLogger(CitiesDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO cities VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE cities SET name = ? WHERE id = ?";
    final String DELETE = "DELETE FROM cities WHERE id = ? ";
    final String GET = "SELECT * FROM cities WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM cities";
    private static final String GET_ALL_NAMES = "SELECT name FROM cities WHERE id>1";

    @Override
    public void createCities(CitiesModel citiesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, citiesModel.getId());
            statement.setString(2, citiesModel.getName());
            statement.setDouble(3, citiesModel.getLatitude());
            statement.setDouble(4, citiesModel.getLongitude());
            statement.setInt(5, citiesModel.getStandartTariff());
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
    public void updateCities(CitiesModel citiesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, citiesModel.getName());
            statement.setInt(2, citiesModel.getId());
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
    public void deleteCitiesById(CitiesModel citiesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, citiesModel.getId());
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
    public CitiesModel getCitiesById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        CitiesModel citiesModel = new CitiesModel();
        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                citiesModel.setId(result.getInt(1));
                citiesModel.setName(result.getString(2));
                citiesModel.setLatitude(result.getDouble(3));
                citiesModel.setLongitude(result.getDouble(4));
                citiesModel.setStandartTariff(result.getInt(5));
                citiesModel.toString();
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
        return citiesModel;
    }

    @Override
    public List<CitiesModel> getALLCities() {
        ArrayList<CitiesModel> citiesModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                CitiesModel citiesModel = new CitiesModel();
                citiesModel.setId(result.getInt(1));
                citiesModel.setName(result.getString(2));
                citiesModel.setLatitude(result.getDouble(3));
                citiesModel.setLongitude(result.getDouble(4));
                citiesModel.setStandartTariff(result.getInt(5));

                citiesModels.add(citiesModel);
                citiesModel.toString();
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
        return citiesModels;
    }

    public List<CitiesModel> getALLCitiesNames() {
        ArrayList<CitiesModel> citiesModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL_NAMES);
            result = statement.executeQuery();
            while (result.next()) {
                CitiesModel citiesModel = new CitiesModel();
                citiesModel.setName(result.getString("name"));
                citiesModels.add(citiesModel);
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
        return citiesModels;
    }
}
