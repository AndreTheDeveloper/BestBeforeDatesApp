package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsRunnable;
import com.andresapps.bestbeforedates.database.LocalDatabase;

import java.util.ArrayList;
import java.util.List;

public class CurrentList extends AppCompatActivity {
    LocalDatabase.DatabaseHelper db = new LocalDatabase.DatabaseHelper(this);
    List<Food> savedList = new ArrayList<Food>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);

        GoogleSheetsRunnable runnable = new GoogleSheetsRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e("list", e.toString());
        }
        savedList = runnable.getFoodFromName("Bananas");
        Log.e("list", String.valueOf(savedList.size()));

        lv = (ListView) findViewById(R.id.currentListView);
        SavedListAdapter adapter = new SavedListAdapter(getApplicationContext(),savedList);
        lv.setAdapter(adapter);

    }
}