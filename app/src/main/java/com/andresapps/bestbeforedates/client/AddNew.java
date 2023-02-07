package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;
import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsFetch;
import com.andresapps.bestbeforedates.database.LocalDatabase;
import com.andresapps.bestbeforedates.database.SaveFoodList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AddNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Button addBttn = findViewById(R.id.searchDBBttn);
        LocalDatabase.DatabaseHelper dbHelper = new LocalDatabase.DatabaseHelper(this);

        addBttn.setOnClickListener(v -> {
            EditText editText = (EditText) findViewById(R.id.typedFood);
            String typedFood = editText.getText().toString();
            ArrayList<Food> foundList = (ArrayList<Food>) dbHelper.searchByName(typedFood);

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for(Food current : foundList) {
                current.setDate_added(now.format(formatter));
            }

            ArrayList<Food> previousList = SaveFoodList.getFoodList(this);
            ArrayList<Food> combinedList = new ArrayList<>();
            combinedList.addAll(foundList);
            if(previousList != null)
                combinedList.addAll(previousList);

            SaveFoodList.saveFoodList(this,combinedList);
            finish();
        });
    }

}