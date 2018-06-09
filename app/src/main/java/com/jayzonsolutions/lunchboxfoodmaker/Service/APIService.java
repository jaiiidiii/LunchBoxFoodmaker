package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("customer/login")
    @FormUrlEncoded
    Call<ApiResponse> savePost(@Field("customerEmail") String customerEmail, @Field("customerPassword") String customerPassword);
}
