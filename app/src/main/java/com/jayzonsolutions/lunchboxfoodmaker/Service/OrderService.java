package com.jayzonsolutions.lunchboxfoodmaker.Service;

import com.google.gson.JsonArray;

import com.jayzonsolutions.lunchboxfoodmaker.model.Order;
import com.jayzonsolutions.lunchboxfoodmaker.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {


    @GET("order/get-order-list")
    Call<List<Order>> getOrderList();

    @GET("order/get-order-list")
    Call<JsonArray> getOrderListTest();


    @GET("order/update-order-status")
    Call<Void> updateOrderStatus(@Query("orderStatus") Integer orderStatus,@Query("orderId") Integer orderId);

    @GET("rider/send-notification-near-by-riders")
    Call<Void> requestToNearByRider(@Query("lat") Double lat,@Query("longt") Double longt,@Query("orderId") Integer orderId);

    @GET("order/get-order")
    Call<Order> getOrderByOrderId(@Query("id") Integer orderId);
}
