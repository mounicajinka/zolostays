package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;

import java.util.ArrayList;

public class Meals {

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "item")
    private String item;


    public Meals(String type, String item) {
        this.type = type;
        this.item = item;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
