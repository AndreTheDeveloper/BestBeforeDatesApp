package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsFetch;
import com.andresapps.bestbeforedates.database.LocalDatabase;
import com.andresapps.bestbeforedates.database.SaveFoodList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CurrentList extends AppCompatActivity {
    private List<Food> savedList = new ArrayList<>();
    private ListView lv;
    private  SavedListAdapter la;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);

        FloatingActionButton addBttn = (FloatingActionButton) findViewById(R.id.addBttn);

        Intent intent = new Intent(this, AddNew.class);
        intent.putExtra("list_data", (Serializable) savedList);
        addBttn.setOnClickListener(v -> startActivity(intent));

        LocalDatabase.DatabaseHelper dbHelper = new LocalDatabase.DatabaseHelper(this);
        if(dbHelper.read().size() == 0) {
            try {
                dbHelper.fetchAndInsertFromGoogle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        retrieveDataFromSharedPrefs();

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveDataFromSharedPrefs();
    }

    private void retrieveDataFromSharedPrefs() {
        savedList = SaveFoodList.getFoodList(this);
        //SaveFoodList.removeList(this);

        if(savedList != null) {
            la = new SavedListAdapter(this, savedList);
            lv = findViewById(R.id.currentListView);
            lv.setAdapter(la);
            la.notifyDataSetChanged();
        }
    }
}