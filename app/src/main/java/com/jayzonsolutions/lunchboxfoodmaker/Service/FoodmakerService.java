package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FoodmakerService {

    @POST("foodmaker/login")
    @FormUrlEncoded
    Call<ApiResponse> foodmakerLogin(@Field("userName") String userName, @Field("password") String password);

    @POST("foodmaker/signup")
    Call<ApiResponse> foodmakerSignup(@Body Foodmaker foodmaker);
}
