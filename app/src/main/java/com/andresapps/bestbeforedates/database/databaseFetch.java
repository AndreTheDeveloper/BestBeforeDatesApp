package com.andresapps.bestbeforedates.database;

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
public class databaseFetch {
    private static String URL_TO_JSON = "https://script.google.com/macros/s/AKfycbyVrQLgySXjwK_s0C9CEYeH5H2F7tYbuehlDGPMFmWmjkXHtareUUlVwVSaHYPeoAQoTw/exec";


    public static ArrayList<food> foodCollection () {

        ArrayList<food> foodList = new ArrayList<food>();
        food currentFood;

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
                if(!current.get("Extra").getAsString().equals(null)) {
                    currentFood = new food(current.get("Name").getAsString(), current.get("BBDMin").getAsInt(), current.get("BBDMax").getAsInt(), current.get("Extra").getAsString());
                } else
                    currentFood = new food(current.get("Name").getAsString(), current.get("BBDMin").getAsInt(), current.get("BBDMax").getAsInt());

                foodList.add(currentFood);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return foodList;

    }
}
