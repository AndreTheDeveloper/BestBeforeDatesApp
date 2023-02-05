package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsFetch;
import com.andresapps.bestbeforedates.database.LocalDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CurrentList extends AppCompatActivity {
    List<Food> savedList = new ArrayList<Food>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);

        FloatingActionButton addBttn = (FloatingActionButton) findViewById(R.id.addBttn);
        addBttn.setOnClickListener(view -> startActivity(new Intent(CurrentList.this, AddNew.class)));



        LocalDatabase.DatabaseHelper dbHelper = new LocalDatabase.DatabaseHelper(this);
        if(dbHelper.read().size() == 0) {
            try {
                dbHelper.fetchAndInsertFromGoogle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //dbHelper.deleteAllRows();
        //dbHelper.test();
        savedList = dbHelper.read();
        Log.e("list", String.valueOf(savedList.size()));


//        savedList = dbHelper.read();
//        Log.e("list", savedList.get(0).getName());

//        lv = findViewById(R.id.currentListView);
//        SavedListAdapter adapter = new SavedListAdapter(getApplicationContext(),savedList);
//        lv.setAdapter(adapter);

    }
}