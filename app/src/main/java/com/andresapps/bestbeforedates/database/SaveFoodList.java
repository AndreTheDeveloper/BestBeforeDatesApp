package com.andresapps.bestbeforedates.database;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveFoodList {
    private static final String PREFS_NAME = "prefs";
    private static final String KEY_NAME = "food_list";

    public static void saveFoodList(Context context, ArrayList<Food> foodList) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(foodList);
        editor.putString(KEY_NAME, json);
        editor.apply();
    }

    public static ArrayList<Food> getFoodList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_NAME, null);
        Type type = new TypeToken<ArrayList<Food>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void removeList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_NAME);
        editor.apply();

    }
}
