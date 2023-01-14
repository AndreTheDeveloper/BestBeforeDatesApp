package com.andresapps.bestbeforedates.database;


import java.util.ArrayList;
import java.util.List;

public class GoogleSheetsRunnable implements Runnable{

    List<Food> googleSheetsFoods = new ArrayList<Food>();
    @Override
    public void run() {
        googleSheetsFoods = GoogleSheetsFetch.foodCollection();
    }

    public List<Food> getFoodFromName(String name) {
        List<Food> foundFoods = new ArrayList<Food>();
        for(int i = 0; i < googleSheetsFoods.size(); i ++) {
            if(googleSheetsFoods.get(i).getName().equals(name)) {
                foundFoods.add(googleSheetsFoods.get(i));
            }
        }
        return foundFoods;
    }
}
