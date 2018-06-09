package com.jayzonsolutions.lunchboxfoodmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerAddress {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;

    public CustomerAddress(String address, String city) {
        this.address = address;
        this.city = city;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
