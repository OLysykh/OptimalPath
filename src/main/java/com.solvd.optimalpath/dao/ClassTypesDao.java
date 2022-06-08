package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IClassTypesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClassTypesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassTypesDao implements IClassTypesDao {
    private static final Logger LOGGER = LogManager.getLogger(ClassTypesModel.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO classTypes VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE classTypes SET typeName=? WHERE id=?";
    final String DELETE = "DELETE FROM classTypes WHERE id = ?";
    final String GET = "SELECT * FROM classTypes WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM classTypes";


    @Override
    public void createClassTypes(ClassTypesModel classTypesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(INSERT);
            statement.setInt(1, classTypesModel.getId());
            statement.setString(2, classTypesModel.getTypeName());
            statement.setInt(3, classTypesModel.getCitiesModel().getId());
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
    public void updateClassTypes(ClassTypesModel classTypesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(UPDATE);
            statement.setString(1, classTypesModel.getTypeName());
            statement.setInt(2, classTypesModel.getId());
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
    public void deleteClassTypesById(ClassTypesModel classTypesModel) {
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(DELETE);
            statement.setInt(1, classTypesModel.getId());
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
    public ClassTypesModel getClassTypesById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        ClassTypesModel classTypesModel = new ClassTypesModel();
        CitiesModel citiesModel = new CitiesModel();

        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                classTypesModel.setId(result.getInt(1));
                classTypesModel.setTypeName(result.getString(2));
                citiesModel.setId(result.getInt(3));
                classTypesModel.setCitiesModel(citiesModel);
                classTypesModel.toString();
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
        return classTypesModel;
    }

    @Override
    public List<ClassTypesModel> getALLClassTypes() {
        ArrayList<ClassTypesModel> classTypesModel = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                ClassTypesModel classTypes = new ClassTypesModel();
                classTypes.setId(result.getInt(1));
                classTypes.setTypeName(result.getString(2));
                CitiesDao dao = new CitiesDao();
                classTypes.setCitiesModel(dao.getCitiesById(result.getInt("citiesId")));
                classTypesModel.add(classTypes);
                classTypes.toString();
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
        return classTypesModel;
    }
}
