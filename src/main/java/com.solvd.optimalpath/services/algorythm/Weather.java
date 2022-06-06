package com.solvd.optimalpath.services.algorythm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    private static String query = "https://api.openweathermap.org/data/2.5/onecall?lat=50.4501&lon=30.5234&appid=46ad646a46ebd0606746c40d0f19d357";

    public static void getWeather(){
        HttpURLConnection connection = null;
        try{
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();
            StringBuilder sb =new StringBuilder();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine())!= null ){
                sb.append(line);
                sb.append("\n");
                }
                System.out.println(sb.toString());
            }

        }catch (Exception cause){
            cause.printStackTrace();
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }
    }
}
