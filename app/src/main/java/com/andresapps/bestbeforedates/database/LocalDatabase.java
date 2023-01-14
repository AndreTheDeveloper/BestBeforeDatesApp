package com.andresapps.bestbeforedates.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class LocalDatabase {

    private LocalDatabase() {}

    public static class DatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "saved_foods";
        public static final String COLUMN_NAME_FOODNAME = "food_name";
        public static final String COLUMN_NAME_BBDMIN = "BBDMin";
        public static final String COLUMN_NAME_BBDMAX = "BBDMax";
        public static final String COLUMN_NAME_EXTRA = "Extra";
        public static final String COLUMN_NAME_CD = "Date_Added";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DatabaseEntry.TABLE_NAME + " (" +
                DatabaseEntry._ID + " INTEGER PRIMARY KEY, " +
                DatabaseEntry.COLUMN_NAME_FOODNAME + " TEXT," +
                DatabaseEntry.COLUMN_NAME_BBDMIN + " INTEGER," +
                DatabaseEntry.COLUMN_NAME_BBDMAX + " INTEGER," +
                DatabaseEntry.COLUMN_NAME_EXTRA + " TEXT," +
                DatabaseEntry.COLUMN_NAME_CD + " TEXT)";

        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatabaseEntry.TABLE_NAME;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "SelectedFoods.db";
        private final Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DatabaseEntry.SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DatabaseEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
        public Context getContext() {
            return this.context;
        }

        public boolean insert(String food_name, String bBDMin, String bBDMax, String extra, String date_added) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseEntry.COLUMN_NAME_FOODNAME, food_name);
            values.put(DatabaseEntry.COLUMN_NAME_BBDMIN, bBDMin);
            values.put(DatabaseEntry.COLUMN_NAME_BBDMAX, bBDMax);
            values.put(DatabaseEntry.COLUMN_NAME_EXTRA, extra);
            values.put(DatabaseEntry.COLUMN_NAME_CD, date_added);

            db.insert(DatabaseEntry.TABLE_NAME,null,values);
            return true;
        }

        public List<Food> read() {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Food> savedFoodList = new ArrayList<Food>();

            String[] projection = {
                    BaseColumns._ID,
                    DatabaseEntry.COLUMN_NAME_FOODNAME,
                    DatabaseEntry.COLUMN_NAME_BBDMIN,
                    DatabaseEntry.COLUMN_NAME_BBDMAX,
                    DatabaseEntry.COLUMN_NAME_EXTRA,
                    DatabaseEntry.COLUMN_NAME_CD
            };

            Cursor cursor = db.query(
                    DatabaseEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(DatabaseEntry._ID));
                String foodName = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseEntry.COLUMN_NAME_FOODNAME));
                String bbdmin = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseEntry.COLUMN_NAME_BBDMIN));
                String bbdmax = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseEntry.COLUMN_NAME_BBDMAX));
                String extra = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseEntry.COLUMN_NAME_EXTRA));
                String date_added = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseEntry.COLUMN_NAME_CD));
                Food current = new Food(id,foodName,bbdmin,bbdmax,extra,date_added);
                savedFoodList.add(current);
            }
            cursor.close();
            return savedFoodList;
        }
        public void deleteAll() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from " + DatabaseEntry.TABLE_NAME);
        }

        public void deleteRow(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DatabaseEntry.TABLE_NAME,"_id=?", new String[]{String.valueOf(id)});
        }

        public void test() {
            Date cd = new Date();
            insert("Apples", "25", "30","Fridge", cd.toString());
        }
    }
}
