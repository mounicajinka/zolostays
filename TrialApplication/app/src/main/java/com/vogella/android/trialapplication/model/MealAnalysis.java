package com.vogella.android.trialapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class MealAnalysis implements Serializable {

    @SerializedName("type_of_entry")
    private String city;

    @SerializedName("Property_name")
    private String property;

    @SerializedName("meal_type")
    private String mealtype;

    @SerializedName("date")
    private Date date;

    @SerializedName("item_name")
    private String item;

    @SerializedName("form_type")
    private String servicetype;

    @SerializedName("vessel_id")
    private String vesselid;

    @SerializedName("weight")
    private Double wastage;

    @SerializedName("kitchen_name")
    private String manager;

    public MealAnalysis() {

    }

//    public MealAnalysis(String city, String property, String mealtype, Date date, String item, String manager,
//                        String servicetype, String vesselid, Integer wastage) {
//        this.city = city;
//        this.property = property;
//        this.mealtype = mealtype;
//        this.date = date;
//        this.item = item;
//        this.manager = manager;
//        this.servicetype = servicetype;
//        this.vesselid = vesselid;
//        this.wastage = wastage;
//
//    }

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

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getVesselid() {
        return vesselid;
    }

    public void setVesselid(String vesselid) {
        this.vesselid = vesselid;
    }

    public Double getWastage() {
        return wastage;
    }

    public void setWastage(Double wastage) {
        this.wastage = wastage;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}

