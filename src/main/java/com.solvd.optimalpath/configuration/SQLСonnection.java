package com.solvd.optimalpath.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLСonnection {

    private static final Logger LOGGER = LogManager.getLogger(SQLСonnection.class);
    private static SQLСonnection instance;


    private SQLСonnection(){
        //let`s try it without forname
    }

    public static SQLСonnection newInstance(){
        if (instance == null){
            new SQLСonnection();
        }
        return instance;
    }

    public static Connection getConnection(String url, String uname, String pas){
        try{
            return DriverManager.getConnection(url,uname,pas);
        }catch (SQLException e ) {
            LOGGER.info("there is no connection. enjoy your null");
            LOGGER.error(e);
            return null;
        }
    }

}
