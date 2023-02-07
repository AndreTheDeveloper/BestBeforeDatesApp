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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        if(savedFoodList.get(i).getExtra().length() > 0)
            foodTitle.setText(savedFoodList.get(i).getExtra().substring(0,1).toUpperCase(Locale.ROOT) + savedFoodList.get(i).getExtra().substring(1) + " " + savedFoodList.get(i).getName());
        else
            foodTitle.setText(savedFoodList.get(i).getName());

        ProgressBar bar;
        bar = (ProgressBar) view.findViewById(R.id.simpleProgressBar);
        bar.setMax(Integer.parseInt(savedFoodList.get(i).getBbdmax()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now().plusDays(5);
        LocalDate date_added = LocalDate.parse(savedFoodList.get(i).getDate_added(), formatter);

        int daysUntilExpired = (int) Duration.between(date_added.atStartOfDay(), now.atStartOfDay()).toDays();
        bar.setProgress(daysUntilExpired);
        return view;
    }
}
