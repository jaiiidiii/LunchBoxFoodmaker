package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Customer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CustomerService {

    @POST("customer/login")
    @FormUrlEncoded
    Call<ApiResponse> customerLogin(@Field("customerEmail") String customerEmail, @Field("customerPassword") String customerPassword);

    @POST("customer/signup")
    Call<ApiResponse> customerSignup(@Body Customer customer);

}
