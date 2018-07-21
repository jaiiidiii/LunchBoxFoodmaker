package com.jayzonsolutions.lunchboxfoodmaker;

public class GlobalVariables {
    private static GlobalVariables instance;

    private String foodmakerId;
    private double Latitude;
    private double Longitude;
    private String UserName;
    private String RegistrationId;

    public static GlobalVariables getInstance() {
        return instance;
    }

    public static void setInstance(GlobalVariables instance) {
        GlobalVariables.instance = instance;
    }

    public static synchronized GlobalVariables GetInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public String getFoodmakerId() {
        return foodmakerId;
    }

    public void setFoodmakerId(String customerId) {
        foodmakerId = customerId;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRegistrationId() {
        return RegistrationId;
    }

    public void setRegistrationId(String registrationId) {
        RegistrationId = registrationId;
    }

    public void ResetVariables() {

        foodmakerId = null;
        Latitude = 0;
        Longitude = 0;
        UserName = null;

    }

}
