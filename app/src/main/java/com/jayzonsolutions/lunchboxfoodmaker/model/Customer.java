package com.jayzonsolutions.lunchboxfoodmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerPassword")
    @Expose
    private String customerPassword;
    @SerializedName("customerNic")
    @Expose
    private String customerNic;
    @SerializedName("customerPhoneNumber")
    @Expose
    private String customerPhoneNumber;
    @SerializedName("customerAccessType")
    @Expose
    private String customerAccessType;
    @SerializedName("customerAddressId")
    @Expose
    private CustomerAddress customerAddress;

    public Customer( String customerName, String customerEmail, String customerPassword, String customerNic, String customerPhoneNumber, String customerAccessType, CustomerAddress customerAddress) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerNic = customerNic;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccessType = customerAccessType;
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAccessType() {
        return customerAccessType;
    }

    public void setCustomerAccessType(String customerAccessType) {
        this.customerAccessType = customerAccessType;
    }

    public CustomerAddress getCustomerAddressId() {
        return customerAddress;
    }

    public void setCustomerAddressId(CustomerAddress customerAddressId) {
        this.customerAddress = customerAddressId;
    }

}