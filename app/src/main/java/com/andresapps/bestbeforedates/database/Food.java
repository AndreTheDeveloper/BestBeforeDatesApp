package com.andresapps.bestbeforedates.database;

public class Food {
    private int id;
    private String name;
    private String bbdmin;
    private String bbdmax;
    private String extra;
    private String date_added;
    private int selected;

    public Food(int id, String name, String bbdmin, String bbdmax, String extra, int selected, String date_added) {
        this.id = id;
        this.name = name;
        this.bbdmin = bbdmin;
        this.bbdmax = bbdmax;
        this.extra = extra;
        this.selected = selected;
        this.date_added = date_added;
    }

    public int getSelected() { return selected; }

    public String getName() {
        return name;
    }

    public String getBbdmin() {
        return bbdmin;
    }

    public String getBbdmax() {
        return bbdmax;
    }

    public String getExtra() {
        return extra;
    }

    public String getDate_added() {
        return date_added;
    }

    public int getId() { return id; }
}
