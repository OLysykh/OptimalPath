package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IAirlinesDao;
import com.solvd.optimalpath.models.AirlinesModel;
import com.solvd.optimalpath.models.CitiesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlinesDao implements IAirlinesDao {
    private static final Logger LOGGER = LogManager.getLogger(AirlinesDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO airlines VALUES (?, ?)";
    final String UPDATE = "UPDATE airlines SET name = ? WHERE id = ? ";
    final String DELETE = "DELETE FROM airlines WHERE id = ?";
    final String GET = "SELECT * FROM airlines WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM airlines";

    @Override
    public void createAirlines(AirlinesModel airlinesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, (airlinesModel.getId()));
            statement.setString(2, airlinesModel.getName());
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
    public void updateAirlines(AirlinesModel airlinesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, airlinesModel.getName());
            statement.setInt(2, airlinesModel.getId());
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
    public void deleteAirlinesById(AirlinesModel airlinesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, airlinesModel.getId());
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
    public AirlinesModel getAirlinesById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        AirlinesModel airlinesModel = new AirlinesModel();
        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                airlinesModel.setId(result.getInt(1));
                airlinesModel.setName(result.getString(2));
                airlinesModel.toString();
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
        LOGGER.info(airlinesModel);
        return airlinesModel;



    }

    @Override
    public List<AirlinesModel> getALLAirlines() {
        ArrayList<AirlinesModel> airlinesModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                AirlinesModel airlinesModel = new AirlinesModel();
                airlinesModel.setId(result.getInt(1));
                airlinesModel.setName(result.getString(2));

                airlinesModels.add(airlinesModel);
                airlinesModel.toString();
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
        return airlinesModels;

    }
}
