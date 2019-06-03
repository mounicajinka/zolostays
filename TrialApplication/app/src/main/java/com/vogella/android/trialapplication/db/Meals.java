package com.vogella.android.trialapplication.db;

import android.arch.persistence.room.ColumnInfo;

import java.util.ArrayList;

public class Meals {

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name="servicetype")
    private String servicetype;

    @ColumnInfo(name="vesselid")
    private String vesselid;

    @ColumnInfo(name = "wastage")
    private Integer wastage;


    public Meals(String type, String item, String servicetype, String vesselid,
                 Integer wastage) {
        this.type = type;
        this.item = item;
        this.servicetype=servicetype;
        this.vesselid=vesselid;
        this.wastage = wastage;

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


    public Integer getWastage() {
        return wastage;
    }

    public void setWastage(Integer wastage) {
        this.wastage = wastage;
    }
}
