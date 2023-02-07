package com.andresapps.bestbeforedates.database;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String name;
    private String bbdmin;
    private String bbdmax;
    private String extra;
    private String date_added;

    public Food(int id, String name, String bbdmin, String bbdmax, String extra, String date_added) {
        this.id = id;
        this.name = name;
        this.bbdmin = bbdmin;
        this.bbdmax = bbdmax;
        this.extra = extra;
        this.date_added = date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }


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
