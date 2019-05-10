package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "zoloFoods")
public class ZoloFoods {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "manager")
    private String manager;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "property")
    private String property;

    @Embedded
    private Meals meals;

    @ColumnInfo(name = "isSubmited")
    private Boolean isSubmited;

    public ZoloFoods(String manager, String city, String property, Meals meals, Boolean isSubmited) {
        this.manager = manager;
        this.city = city;
        this.property = property;
        this.meals = meals;
        this.isSubmited = isSubmited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

    public Boolean getSubmited() {
        return isSubmited;
    }

    public void setSubmited(Boolean submited) {
        isSubmited = submited;
    }
}
