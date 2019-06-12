package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "zolo_foods")
public class ZoloFoods {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    @SerializedName("daily_date")
    private String date;

    @ColumnInfo(name = "manager")
    @SerializedName("username")
    private String manager;

    @ColumnInfo(name = "city")
    @SerializedName("usercity")
    private String city;

    @ColumnInfo(name = "property")
    @SerializedName("userhotel")
    private String property;

    @ColumnInfo(name = "type")
    @SerializedName("meal_type")
    private String meal_type;

    @ColumnInfo(name = "item")
    @SerializedName("item_name")
    private String item;

    @ColumnInfo(name = "isSubmited")
    private Boolean isSubmited;

    public ZoloFoods( int id, String manager, String city, String property,String date,String meal_type, String item, Boolean isSubmited) {
        this.id = id;
        this.manager = manager;
        this.city = city;
        this.property = property;
        this.date = date;
        this.meal_type=meal_type;
       this.item =item;
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

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getSubmited() {
        return isSubmited;
    }

    public void setSubmited(Boolean submited) {
        isSubmited = submited;
    }
}
