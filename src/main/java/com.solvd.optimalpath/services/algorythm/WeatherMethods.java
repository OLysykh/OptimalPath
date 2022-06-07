package com.solvd.optimalpath.services.algorythm;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherMethods {

    final Logger LOGGER = LogManager.getLogger(WeatherMethods.class);

//    private static String query = "https://api.openweathermap.org/data/2.5/weather?lat=50.0755&lon=14.4378&appid=46ad646a46ebd0606746c40d0f19d357&units=metric";
    private static String queryStart = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private static String queryMiddle = "&lon=";
    private static String queryEnd = "&appid=46ad646a46ebd0606746c40d0f19d357&units=metric";

    public static void createCityRequest(double lang, double lon){
        getWeather(queryStart + lang + queryMiddle + lon + queryEnd);
    }

    public static void getWeather(String query) {
        HttpURLConnection connection = null;
        String result = "test!";
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();
            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                    result = sb.toString();
                }
                System.out.println(sb);
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/test.json"))) {
            out.write(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}