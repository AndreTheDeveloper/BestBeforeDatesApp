package com.andresapps.bestbeforedates.client;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andresapps.bestbeforedates.R;

import com.andresapps.bestbeforedates.database.Food;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class SavedListAdapter extends BaseAdapter {

    Context context;
    List<Food> savedFoodList;
    LayoutInflater inflater;

    public SavedListAdapter(Context ctx, List<Food> savedFoodList) {
        this.context = ctx;
        this.savedFoodList = savedFoodList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return savedFoodList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return savedFoodList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_saved_list_view, null);
        TextView foodTitle = (TextView) view.findViewById(R.id.savedFoodTitle);
        foodTitle.setText(savedFoodList.get(i).getName() + " Extra: " + savedFoodList.get(i).getExtra());

        ProgressBar bar;
        bar = (ProgressBar) view.findViewById(R.id.simpleProgressBar);
        bar.setMax(Integer.parseInt(savedFoodList.get(i).getBbdmax()));

        int daysUntilExpired = (int) Duration.between(LocalDate.now().atStartOfDay(), LocalDate.parse(savedFoodList.get(i).getDate_added()).atStartOfDay()).toDays();
        bar.setProgress(daysUntilExpired);
        Log.e("list", String.valueOf(bar.getProgress()));
        return view;
    }
}
