package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodmakerDishesService {

//    @GET("dishes/get-dish-list")
//    Call<List<FoodmakerDishes>> getDishList();

    @POST("foodmaker_dishes/add-dish")
    Call<ApiResponse> addFoodmakerDishes(@Body FoodmakerDishes foodmakerDishes);
}
