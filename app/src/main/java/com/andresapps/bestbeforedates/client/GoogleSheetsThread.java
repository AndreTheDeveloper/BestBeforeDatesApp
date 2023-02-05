package com.andresapps.bestbeforedates.client;

import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsFetch;


import java.util.List;

public class GoogleSheetsThread extends Thread {
    private List<Food> googleList;

    public GoogleSheetsThread(List<Food> googleList) {
        this.googleList = googleList;
    }

    @Override
    public void run() {
        googleList = GoogleSheetsFetch.foodCollection();
    }

    public List<Food> getGoogleList() {
        return googleList;
    }
}
