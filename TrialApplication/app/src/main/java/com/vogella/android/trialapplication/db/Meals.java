package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;

import java.util.ArrayList;

public class Meals {

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "items")
    private ArrayList<String> items;

    @ColumnInfo(name = "wastage")
    private Integer wastage;

    public Meals(String type, ArrayList<String> items, Integer wastage) {
        this.type = type;
        this.items = items;
        this.wastage = wastage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public Integer getWastage() {
        return wastage;
    }

    public void setWastage(Integer wastage) {
        this.wastage = wastage;
    }
}
