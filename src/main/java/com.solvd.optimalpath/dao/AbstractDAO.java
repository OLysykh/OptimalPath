package com.solvd.optimalpath.dao;

import com.solvd.optimalpath.configuration.SQLСonnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

abstract public class AbstractDAO {


    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    private Statement statement;
    protected ResultSet resultSet;
    private Connection connection;
    private PreparedStatement preparedStatement;

    ResourceBundle resource = ResourceBundle.getBundle("db");
    String url = resource.getString("db.url");
    String username = resource.getString("db.username");
    String password = resource.getString("db.password");

    public Connection getConnection(){
        SQLСonnection.newInstance();
        connection = SQLСonnection.getConnection(url,username,password);
        return connection;
    }

    public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return getConnection().prepareStatement(sqlQuery);
    }


    //so we can save/update/delete someone with a number of capholders!

    public void getPreparedStatementtoSave(String sqlQuery, Long id) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1,id);
        preparedStatement.execute();
    }
    public void getPreparedStatementtoSave(String sqlQuery, Long id, Integer parameter) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1,id);
        preparedStatement.setLong(2,parameter);
        preparedStatement.execute();
    }

    public void getPreparedStatementtoSave(String sqlQuery, Long id, String name) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(2,id);
        preparedStatement.setString(1,name);
        preparedStatement.execute();
    }

    public void getPreparedStatementtoSave(String sqlQuery, Long id, String name, Long number) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setLong(3, number);
        preparedStatement.execute();
    }


    public ResultSet getResultSet(String sqlQuery) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getResultSet(String sqlQuery, Long id) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1,id);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getResultSet(String sqlQuery, Long id, String name, Long number) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setLong(3, number);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    public void closeAll() {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        if (statement !=  null)
            try {
                statement.close();
            } catch (SQLException ignored){
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException ignored){
            }
    }

}
