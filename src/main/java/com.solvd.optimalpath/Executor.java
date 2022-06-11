package com.solvd.optimalpath;

import com.solvd.optimalpath.services.methods.ClientMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {


    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(Executor.class);

        ClientMenu.start();
    }


}
