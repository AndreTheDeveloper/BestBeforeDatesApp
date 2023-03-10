package com.andresapps.bestbeforedates.database;

import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * sheetsAPI
 */
public class GoogleSheetsFetch {
    private static String URL_TO_JSON = "https://script.google.com/macros/s/AKfycbx2uP88u1hIYUrJwgC3_eD3IzNn_9jhsqo1quUSqYj45LiGoXm01j8nTPaBtboC0E_Ofg/exec";


    public static ArrayList<Food> foodCollection () {

        ArrayList<Food> foodList = new ArrayList<Food>();
        Food currentFood;

        try {

            //Opens connection to url and sets the request method to GET
            URL url = new URL(URL_TO_JSON);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //Reads the website
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            //Sets the response as JSON
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.toString(), JsonObject.class);

            //Reads the JSON response and sets the object accordingly
            JsonArray array = json.getAsJsonObject().get("data").getAsJsonArray();
            for(int i = 0; i < array.size(); i++) {
                JsonObject current = array.get(i).getAsJsonObject();
                currentFood = new Food(
                        current.get("_ID").getAsInt(),
                        current.get("Name").getAsString(),
                        current.get("BBDMin").getAsString(),
                        current.get("BBDMax").getAsString(),
                        current.get("Extra").getAsString(),
                        current.get("DateAdded").getAsString());
                foodList.add(currentFood);
            }
        }  catch (Exception e) {
           Log.e("list", String.valueOf(e));
        }

        return foodList;

    }
}
