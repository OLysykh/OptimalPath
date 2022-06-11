package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.solvd.optimalpath.interfaces.IUserPassDao;
import com.solvd.optimalpath.models.UserPassModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPassDao implements IUserPassDao {
    private static final Logger LOGGER = LogManager.getLogger(UserPassDao.class);
    PreparedStatement statement = null;
    ResultSet result = null;
    final String INSERT = "INSERT INTO userpass VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE userpass SET pass = ? WHERE id = ? ";
    final String DELETE = "DELETE FROM userpass WHERE id = ?";
    final String GET = "SELECT * FROM userpass WHERE id = ? ";
    private static final String GET_ALL = "SELECT * FROM userpass";

    @Override
    public void createUserPass(UserPassModel userPassModel) {
            Connection dbConnect = DataBaseConnection.getConnection();
            try {
                statement = dbConnect.prepareStatement(INSERT);
                statement.setInt(1, (userPassModel.getId()));
                statement.setString(2, userPassModel.getUser());
                statement.setString(3, userPassModel.getPass());
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
    public void updateUserPass(UserPassModel userPassModel) {
            Connection dbConnect = DataBaseConnection.getConnection();
            try {
                statement = dbConnect.prepareStatement(UPDATE);
                statement.setString(1, userPassModel.getPass());
                statement.setInt(2, userPassModel.getId());
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
    public void deleteUserPass(UserPassModel userPassModel) {
            Connection dbConnect = DataBaseConnection.getConnection();
            try {
                statement = dbConnect.prepareStatement(DELETE);
                statement.setInt(1, userPassModel.getId());
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
    public UserPassModel getUserPassById(int id) {
        Connection dbConnect = DataBaseConnection.getConnection();
        UserPassModel userPassModel = new UserPassModel();
        try {
            statement = dbConnect.prepareStatement(GET);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                userPassModel.setId(result.getInt(1));
                userPassModel.setUser(result.getString(2));
                userPassModel.setPass(result.getString(2));
                userPassModel.toString();
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
        return userPassModel;
    }

    @Override
    public List<UserPassModel> getALLUserPass() {
        ArrayList<UserPassModel> userPassModels = new ArrayList<>();
        Connection dbConnect = DataBaseConnection.getConnection();
        try {
            statement = dbConnect.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                UserPassModel userPassModel = new UserPassModel();
                userPassModel.setId(result.getInt(1));
                userPassModel.setUser(result.getString(2));
                userPassModel.setPass(result.getString(3));
                userPassModels.toString();
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
        return userPassModels;
    }
}




