package com.jayzonsolutions.lunchboxfoodmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Rider {

    @SerializedName("riderId")
    @Expose
    private Integer riderId;

    @SerializedName("riderName")
    @Expose
    private String riderName;

    @SerializedName("riderVehicleId")
    @Expose
    private String riderVehicleId;

    @SerializedName("riderNic")
    @Expose
    private String riderNic;

    @SerializedName("riderPassword")
    @Expose
    private String riderPassword;

    @SerializedName("riderAddresId")
    @Expose
    private Address riderAddresId;


    @SerializedName("riderEmail")
    @Expose
    private String riderEmail;

    @SerializedName("riderAccessType")
    @Expose
    private Integer riderAccessType;


    @SerializedName("riderImagePath")
    @Expose
    private String riderImagePath;


    @SerializedName("riderActive")
    @Expose
    private Integer riderActive;

    @SerializedName("riderPhoneNumber")
    @Expose
    private String riderPhoneNumber;

    @SerializedName("riderAdminId")
    @Expose
    private Integer riderAdminId;

    @SerializedName("riderCreatedAt")
    @Expose
    private Date riderCreatedAt;

    @SerializedName("riderLastUpdated")
    @Expose
    private Date riderLastUpdated;

    @SerializedName("riderLocationId")
    @Expose
    private Integer riderLocationId;

    @SerializedName("riderRegToken")
    @Expose
    private String riderRegToken;

    public Rider() {
    }

    public Rider(String riderName, String riderEmail , String riderVehicleId, String riderNic, String riderPassword, Address riderAddresId, Integer riderAccessType, String riderImagePath, Integer riderActive, String riderPhoneNumber, Integer riderAdminId, Date riderCreatedAt, Date riderLastUpdated, Integer riderLocationId,String riderRegToken) {
        this.riderName = riderName;
        this.riderVehicleId = riderVehicleId;
        this.riderNic = riderNic;
        this.riderPassword = riderPassword;
        this.riderAddresId = riderAddresId;
        this.riderAccessType = riderAccessType;
        this.riderImagePath = riderImagePath;
        this.riderActive = riderActive;
        this.riderPhoneNumber = riderPhoneNumber;
        this.riderAdminId = riderAdminId;
        this.riderCreatedAt = riderCreatedAt;
        this.riderLastUpdated = riderLastUpdated;
        this.riderLocationId = riderLocationId;
        this.riderEmail = riderEmail;
        this.riderRegToken = riderRegToken;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public String getRiderEmail() {
        return riderEmail;
    }

    public void setRiderEmail(String riderEmail) {
        this.riderEmail = riderEmail;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderVehicleId() {
        return riderVehicleId;
    }

    public void setRiderVehicleId(String riderVehicleId) {
        this.riderVehicleId = riderVehicleId;
    }

    public String getRiderNic() {
        return riderNic;
    }

    public void setRiderNic(String riderNic) {
        this.riderNic = riderNic;
    }

    public String getRiderPassword() {
        return riderPassword;
    }

    public void setRiderPassword(String riderPassword) {
        this.riderPassword = riderPassword;
    }

    public Address getRiderAddresId() {
        return riderAddresId;
    }

    public void setRiderAddresId(Address riderAddresId) {
        this.riderAddresId = riderAddresId;
    }

    public Integer getRiderAccessType() {
        return riderAccessType;
    }

    public void setRiderAccessType(Integer riderAccessType) {
        this.riderAccessType = riderAccessType;
    }

    public String getRiderImagePath() {
        return riderImagePath;
    }

    public void setRiderImagePath(String riderImagePath) {
        this.riderImagePath = riderImagePath;
    }

    public Integer getRiderActive() {
        return riderActive;
    }

    public void setRiderActive(Integer riderActive) {
        this.riderActive = riderActive;
    }

    public String getRiderPhoneNumber() {
        return riderPhoneNumber;
    }

    public void setRiderPhoneNumber(String riderPhoneNumber) {
        this.riderPhoneNumber = riderPhoneNumber;
    }

    public Integer getRiderAdminId() {
        return riderAdminId;
    }

    public void setRiderAdminId(Integer riderAdminId) {
        this.riderAdminId = riderAdminId;
    }

    public Date getRiderCreatedAt() {
        return riderCreatedAt;
    }

    public void setRiderCreatedAt(Date riderCreatedAt) {
        this.riderCreatedAt = riderCreatedAt;
    }

    public Date getRiderLastUpdated() {
        return riderLastUpdated;
    }

    public void setRiderLastUpdated(Date riderLastUpdated) {
        this.riderLastUpdated = riderLastUpdated;
    }

    public Integer getRiderLocationId() {
        return riderLocationId;
    }

    public void setRiderLocationId(Integer riderLocationId) {
        this.riderLocationId = riderLocationId;
    }

    public String getRiderRegToken() {
        return riderRegToken;
    }

    public void setRiderRegToken(String riderRegToken) {
        this.riderRegToken = riderRegToken;
    }
}
