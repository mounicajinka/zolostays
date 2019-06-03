package com.vogella.android.trialapplication.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class KitchenMenu {


    @SerializedName("usercity")
    private String city;

    @SerializedName("userhotel")
    private String property;

    @SerializedName("mealtype")
    private String mealtype;

    @SerializedName("daily_date")
    private Date date;

    @SerializedName("item_name")
    private String item;

    @SerializedName(("username"))
    private String manager;


    public KitchenMenu(String city, String property, String mealtype, Date date, String item, String manager) {
        this.city = city;
        this.property = property;
        this.mealtype = mealtype;
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
        return mealtype;
    }

    public void setMealtype(String mealtype) {
        this.mealtype = mealtype;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}











