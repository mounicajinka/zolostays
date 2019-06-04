package com.vogella.android.trialapplication.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "meal_analysis")
public class MealAnalysis {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "manager")
    @SerializedName("username")
    private String manager;

    @ColumnInfo(name = "city")
    @SerializedName("City_name")
    private String city;

    @ColumnInfo(name = "property")
    @SerializedName("Property_name")
    private String property;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    private Date date;

    @ColumnInfo(name = "mealtype")
    @SerializedName("meal_type")
    private String mealtype;

    @ColumnInfo(name = "itemname")
    @SerializedName("item_name")
    private String itemname;

    @ColumnInfo(name = "servicetype")
    @SerializedName("service_Type")
    private String servicetype;

    @ColumnInfo(name = "vesselid")
    @SerializedName("vessel_id")
    private String vessel_id;

    @ColumnInfo(name = "wastage")
    @SerializedName("weight")
    private Integer wastage;

    public MealAnalysis(String manager, String city, String property,Date date, String mealtype, String itemname,
                        String servicetype, String vessel_id, Integer wastage){
        this.manager = manager;
        this.city = city;
        this.property =property;
        this.date = date;
        this.mealtype = mealtype;
        this.itemname = itemname;
        this.servicetype = servicetype;
        this.vessel_id= vessel_id;
        this.wastage = wastage;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMealtype() {
        return mealtype;
    }

    public void setMealtype(String mealtype) {
        this.mealtype = mealtype;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getVessel_id() {
        return vessel_id;
    }

    public void setVessel_id(String vessel_id) {
        this.vessel_id = vessel_id;
    }

    public Integer getWastage() {
        return wastage;
    }

    public void setWastage(Integer wastage) {
        this.wastage = wastage;
    }
}
