package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.LocalDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);

        LocalDatabase.DatabaseHelper dbHelper = new LocalDatabase.DatabaseHelper(this);
        Date date = new Date();
        final List<Food>[] foodList = new List[]{new ArrayList<Food>()};
        Button bttn = findViewById(R.id.button);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insert("Apples","25","30","Raw",date.toString());
                dbHelper.deleteRow(0);
            }
        });
    }
}