package com.jayzonsolutions.lunchboxfoodmaker.Service;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.0.106:8080/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
    public static CustomerService getCustomerService(){
        return RetrofitClient.getClient(BASE_URL).create(CustomerService.class);
    }
 }