package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IAnimalsDao;
import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.CitiesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalsDao implements IAnimalsDao {
    private static final Logger LOGGER = LogManager.getLogger(AnimalsDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO animals VALUES (?, ?)";
    final String UPDATE = "UPDATE animals SET typeOfAnimal = ? WHERE id = ? ";
    final String DELETE = "DELETE FROM animals WHERE id = ?";
    final String GET = "SELECT * FROM animals WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM animals";

    @Override
    public void createAnimals(AnimalsModel animalsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, (animalsModel.getId()));
            statement.setString(2, animalsModel.getTypeOfAnimal());
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
    public void updateAnimals(AnimalsModel animalsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, animalsModel.getTypeOfAnimal());
            statement.setInt(2, animalsModel.getId());
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
    public void deleteAnimalsById(AnimalsModel animalsModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, animalsModel.getId());
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
    public AnimalsModel getAnimalsById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        AnimalsModel animalsModel = new AnimalsModel();
        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                animalsModel.setId(result.getInt(1));
                animalsModel.setTypeOfAnimal(result.getString(2));
                animalsModel.toString();
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
        return animalsModel;

    }

    @Override
    public List<AnimalsModel> getALLAnimals() {
            ArrayList<AnimalsModel> animalsModels = new ArrayList<>();
            Connection dbConnect = DataBaseConnection.getConnection();
            try {
                statement = dbConnect.prepareStatement(GET_ALL);
                result = statement.executeQuery();
                while (result.next()) {
                    AnimalsModel animalsModel = new AnimalsModel();
                    animalsModel.setId(result.getInt(1));
                    animalsModel.setTypeOfAnimal(result.getString(2));
                    animalsModels.add(animalsModel);
                    animalsModel.toString();
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
            return animalsModels;
        }

}
