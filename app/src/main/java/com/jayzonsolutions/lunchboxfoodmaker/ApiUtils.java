package com.jayzonsolutions.lunchboxfoodmaker;

import com.jayzonsolutions.lunchboxfoodmaker.Service.APIService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.CustomerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.DishService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerDishesService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.RetrofitClient;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.0.107:8080/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
    public static CustomerService getCustomerService(){
        return RetrofitClient.getClient(BASE_URL).create(CustomerService.class);
    }

    static DishService getDishService(){
        return  RetrofitClient.getClient(BASE_URL).create(DishService.class);
    }

    static FoodmakerDishesService getFoodmakerDishes(){
        return  RetrofitClient.getClient(BASE_URL).create(FoodmakerDishesService.class);
    }

    static FoodmakerService getFoodmakerService(){
        return  RetrofitClient.getClient(BASE_URL).create(FoodmakerService.class);
    }
 }