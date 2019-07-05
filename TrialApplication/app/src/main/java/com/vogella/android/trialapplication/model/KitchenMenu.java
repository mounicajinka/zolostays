package com.vogella.android.trialapplication.model;

import com.google.gson.annotations.SerializedName;

public class KitchenMenu {


    @SerializedName("id")
    private int id;

    @SerializedName("CITY")
    private String city;

    @SerializedName("Kitchen")
    private String property;

    @SerializedName("meal_type")
    private String meal_type;

    @SerializedName("daily_date")
    private String date;

    @SerializedName("item_name")
    private String item;

    @SerializedName(("entered_by"))
    private String manager;


    public KitchenMenu(int id, String city, String property, String meal_type, String date, String item, String manager) {
        this.id = id;
        this.city = city;
        this.property = property;
        this.meal_type = meal_type;
        this.date = date;
        this.item = item;
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

    public String getMealtype() {
        return meal_type;
    }

    public void setMealtype(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}











