package com.vogella.android.trialapplication.model;

import com.google.gson.annotations.SerializedName;

public class AdapterData {

    @SerializedName("item_name")
    private String itemname;

    @SerializedName("vessel_id")
    private String vessel_id;

    @SerializedName("weight")
    private Double weight;

    public AdapterData(String itemname,String vessel_id,Double weight){
        this.itemname=itemname;
        this.vessel_id=vessel_id;
        this.weight = weight;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getVessel_id() {
        return vessel_id;
    }

    public void setVessel_id(String vessel_id) {
        this.vessel_id = vessel_id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
