package com.jayzonsolutions.lunchboxfoodmaker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivty extends AppCompatActivity {


    private OrderService orderService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activty);

        /***
         *
         */
        orderService = ApiUtils.getOrderService();
        orderService.updateOrderStatus(1,3).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(TestActivty.this, "success" , Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(TestActivty.this, "Response Failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "failed" );
            }
        });



    }
}
