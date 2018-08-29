package com.jayzonsolutions.lunchboxfoodmaker;

import com.jayzonsolutions.lunchboxfoodmaker.Service.APIService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.CustomerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.DishService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerDishesService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.RetrofitClient;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.0.108:8080/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
    public static CustomerService getCustomerService(){
        return RetrofitClient.getClient(BASE_URL).create(CustomerService.class);
    }

    public static DishService getDishService(){
        return  RetrofitClient.getClient(BASE_URL).create(DishService.class);
    }

    public static FoodmakerDishesService getFoodmakerDishes(){
        return  RetrofitClient.getClient(BASE_URL).create(FoodmakerDishesService.class);
    }

    public static FoodmakerService getFoodmakerService(){
        return  RetrofitClient.getClient(BASE_URL).create(FoodmakerService.class);
    }
    public static OrderService getOrderService(){
        return  RetrofitClient.getClient(BASE_URL).create(OrderService.class);
    }
 }