package com.andresapps.bestbeforedates.database;

public class food {
    private String name;
    private int bbdmin;
    private int bbdmax;
    private String extra;

    public food(String name, int bbdmin, int bbdmax, String extra) {
        this.name = name;
        this.bbdmin = bbdmin;
        this.bbdmax = bbdmax;
        this.extra = extra;
    }

    public food(String name, int bbdmin, int bbdmax) {
        this.name = name;
        this.bbdmin = bbdmin;
        this.bbdmax = bbdmax;
    }

    public String getName() {
        return name;
    }

    public int getBbdmin() {
        return bbdmin;
    }

    public int getBbdmax() {
        return bbdmax;
    }

    public String getExtra() {
        return extra;
    }

}
