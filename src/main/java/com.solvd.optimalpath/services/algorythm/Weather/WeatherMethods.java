package com.solvd.optimalpath.services.algorythm.Weather;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.solvd.optimalpath.services.algorythm.Executor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class WeatherMethods {

    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    private static String queryStart = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private static String queryMiddle = "&lon=";
    private static String queryEnd = "&appid=46ad646a46ebd0606746c40d0f19d357&units=metric";

    //тут мы генерируем ссылку длоя каждого
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
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/projectMaterials/jsonFiles/Weather.json"))) {
            out.write(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WeatherData readFromJson() {
        File file = new File("src/main/resources/projectMaterials/jsonFiles/Weather.json");
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherData weatherData = new WeatherData();
        try {
//            String result = RequestHolding.getStringFromResponse(lat, lon);
//            StringReader reader = new StringReader(result);
            weatherData = objectMapper.readValue(file, WeatherData.class);
//            objectMapper.writeValue(new File(path), weatherInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return weatherData;
    }



    }

