package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
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

        savedList = db.read();
        if(savedList.isEmpty()) {
            db.test();
            savedList = db.read();
        }
        else if(savedList.size() > 1) {
            db.deleteAll();
            db.test();
            savedList = db.read();
        }

        lv = (ListView) findViewById(R.id.currentListView);
        SavedListAdapter adapter = new SavedListAdapter(getApplicationContext(),savedList);
        lv.setAdapter(adapter);

    }
}