package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.jayzonsolutions.lunchboxfoodmaker.model.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DishService {


    @GET("dishes/get-dish-list")
    Call<List<Dish>> getDishList();
}
