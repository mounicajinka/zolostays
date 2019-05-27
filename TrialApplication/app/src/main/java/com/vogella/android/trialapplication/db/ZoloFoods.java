package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "zolo_foods")
public class ZoloFoods {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "manager")
    @SerializedName("username")
    private String manager;

    @ColumnInfo(name = "city")
    @SerializedName("usercity")
    private String city;

    @ColumnInfo(name = "property")
    @SerializedName("userhotel")
    private String property;

    @ColumnInfo(name = "date")
    @SerializedName("daily_date")
    private String date;

    @Embedded
    private Meals meals;

    @ColumnInfo(name = "isSubmited")
    private Boolean isSubmited;

    public ZoloFoods( String manager, String city, String property,String date, Meals meals, Boolean isSubmited) {
        this.manager = manager;
        this.city = city;
        this.property = property;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
