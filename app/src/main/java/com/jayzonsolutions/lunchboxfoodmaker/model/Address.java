package com.jayzonsolutions.lunchboxfoodmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Address {

    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("addressPostalCode")
    @Expose
    private Object addressPostalCode;
    @SerializedName("addressLastUpdated")
    @Expose
    private Integer addressLastUpdated;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public Object getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(Object addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public Integer getAddressLastUpdated() {
        return addressLastUpdated;
    }

    public void setAddressLastUpdated(Integer addressLastUpdated) {
        this.addressLastUpdated = addressLastUpdated;
    }
}
