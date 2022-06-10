package com.solvd.optimalpath.services.algorythm;

import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.services.ClientMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Executor {


    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(Executor.class);


//        WeatherMethods.createCityRequest(50.0755, 14.4378);//this feature is in production!
        ClientMenu.start();
///////////////////////////////////////////First way
/*        try {
            System.out.println("Start delay of 1 second, Time is: " + getCurrentTime());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("And delay of 1 second, Time is: " + getCurrentTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
///////////////////////////////////////////Second way

/*        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        for (int i = 2; i < 24; i++)  myNumbers.add(i);*/

/*        for (int i = 0; i < 22; i++) {
            try {
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Press " + myNumbers.get(i) + " if you choose " + citiesNames.get(i));*/
        //}
//////////////////////////////////////////
/*        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        for (int i = 2; i < 24; i++) {
            myNumbers.add(i);
        }
        System.out.println(myNumbers);*/
//      WeatherData weatherData = WeatherMethods.readFromJson();
//        System.out.println(weatherData);
/*CitiesDao citiesDao= new CitiesDao();
List<String> citiesNames=citiesDao.getALLCitiesNames();*/
        //System.out.println(citiesDao.getALLCitiesNames());
        //System.out.println(citiesNames);

    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
