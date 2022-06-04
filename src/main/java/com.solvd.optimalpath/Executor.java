package com.solvd.optimalpath;

import com.solvd.optimalpath.services.DistanceCalculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {


    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {

        DistanceCalculation distanceCalculation = new DistanceCalculation();
        double ttt = DistanceCalculation.distance_Between_LatLong(20.00, 21.01,07.78, 65.98);
        System.out.println(ttt);
    }
}
