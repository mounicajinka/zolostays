package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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


    @ColumnInfo(name = "type")
    @SerializedName("meal_type")
    private String meal_type;

    @ColumnInfo(name = "item")
    @SerializedName("item_name")
    private String item;

    @ColumnInfo(name = "service_type")
    @SerializedName("form_type")
    private String  service_type;

    @ColumnInfo(name = "vessel_id")
    @SerializedName("vessel_id")
    private String vessle_id;

    @ColumnInfo(name = "wastage")
    @SerializedName("weight")
    private Integer wastage;

    @ColumnInfo(name = "isSubmited")
    private Boolean isSubmited;

    public ZoloFoods( int id, String manager, String city, String property,String date,String meal_type, String item,String service_type,
                      String  vessle_id,Integer wastage,  Boolean isSubmited) {
        this.id = id;
        this.manager = manager;
        this.city = city;
        this.property = property;
        this.date = date;
        this.meal_type=meal_type;
       this.item =item;
       this.service_type = service_type;
       this.vessle_id = vessle_id;
       this.wastage = wastage;
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

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getVessle_id() {
        return vessle_id;
    }

    public void setVessle_id(String vessle_id) {
        this.vessle_id = vessle_id;
    }

    public Integer getWastage() {
        return wastage;
    }

    public void setWastage(Integer wastage) {
        this.wastage = wastage;
    }

    public Boolean getSubmited() {
        return isSubmited;
    }

    public void setSubmited(Boolean submited) {
        isSubmited = submited;
    }
}
