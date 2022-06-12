package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IAdministratorsDao;
import com.solvd.optimalpath.models.AdministratorsModel;
import com.solvd.optimalpath.models.AirlinesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorsDao implements IAdministratorsDao {

    private static final Logger LOGGER = LogManager.getLogger(AdministratorsDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO administrators VALUES ( ?, ?)";
    final String UPDATE = "UPDATE administrators SET name = ? WHERE id = ? ";
    final String DELETE = "DELETE FROM administrators WHERE id = ?";
    final String GET = "SELECT * FROM administrators WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM administrators";
    final String GET_BY_NAME = "SELECT * FROM administrators WHERE adminName = ?";

    @Override
    public void createAdministrators(AdministratorsModel administratorsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, (administratorsModel.getId()));
            statement.setString(2, administratorsModel.getAdminName());
            statement.setString(3, administratorsModel.getPass());
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
    public void updateAdministrators(AdministratorsModel administratorsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, administratorsModel.getAdminName());
            statement.setInt(2, administratorsModel.getId());
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
    public void deleteAdministratorsById(AdministratorsModel administratorsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, administratorsModel.getId());
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
    public AdministratorsModel getAdministratorsById(int id) {

        Connection dbConnect = DataBaseConnection.getConnection();
        AdministratorsModel administratorsModel = new AdministratorsModel();
        try {

            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                administratorsModel.setId(result.getInt(1));
                administratorsModel.setAdminName(result.getString(2));
                administratorsModel.setPass(result.getString(3));
                administratorsModel.toString();
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
        return administratorsModel;

    }

    @Override
    public List<AdministratorsModel> getALLAdministrators() {

        ArrayList<AdministratorsModel> administratorsModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                AdministratorsModel administratorsModel = new AdministratorsModel();
                administratorsModel.setId(result.getInt(1));
                administratorsModel.setAdminName(result.getString(2));
                administratorsModel.setPass(result.getString(3));

                administratorsModels.add(administratorsModel);
                administratorsModel.toString();
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
        return administratorsModels;

    }

    @Override
    public AdministratorsModel getAdministratorsByName(String adminName) {
        Connection dbConnect = DataBaseConnection.getConnection();
        AdministratorsModel administratorsModel = new AdministratorsModel();
        try {

            statement = dbConnect.prepareStatement(GET_BY_NAME);
            statement.setString(1, adminName);
            result = statement.executeQuery();
            while (result.next()) {
                administratorsModel.setId(result.getInt(1));
                administratorsModel.setAdminName(result.getString(2));
                administratorsModel.setPass(result.getString(3));
                administratorsModel.toString();
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
        return administratorsModel;

    }


}
