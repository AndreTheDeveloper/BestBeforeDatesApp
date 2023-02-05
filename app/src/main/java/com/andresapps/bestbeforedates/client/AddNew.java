package com.andresapps.bestbeforedates.client;

import androidx.appcompat.app.AppCompatActivity;
import com.andresapps.bestbeforedates.R;
import com.andresapps.bestbeforedates.database.Food;
import com.andresapps.bestbeforedates.database.GoogleSheetsFetch;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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
    List<Food> savedList = new ArrayList<Food>();
    List<Food> googleSheetsFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
    }

}