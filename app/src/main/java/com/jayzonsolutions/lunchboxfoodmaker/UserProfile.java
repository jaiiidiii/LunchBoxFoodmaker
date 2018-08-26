package com.jayzonsolutions.lunchboxfoodmaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Address;
import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;

import customfonts.MyEditText;
import customfonts.MyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfile extends AppCompatActivity {

    MyEditText dispName;
    MyEditText dispEmail;
    MyEditText userPhone;
    MyTextView userRatting;
    MyEditText userAddress;
    MyEditText dispcnic;
    com.alexzh.circleimageview.CircleImageView circleview;
    com.rey.material.widget.Switch switcher;

    FloatingActionButton btnSave;
    FloatingActionButton btnCancel;

    FoodmakerService foodmakerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodmakerService = ApiUtils.getFoodmakerService();

        switcher =  findViewById(R.id.switcher);

        circleview = findViewById(R.id.circleview);
        /**
         * images
         * */
     //   String imagePath = ((movieList.get(position).getImagepath() != null)?movieList.get(position).getImagepath():"http://localhost:8080/images/biryani.jpg");

      if(Constant.foodmaker.getFoodmakerImagePath().length() > 21){
          String imagePath = Constant.foodmaker.getFoodmakerImagePath();;


          Glide.with(this).load(ApiUtils.BASE_URL+(imagePath.substring(21))).
                  apply(RequestOptions.
                          centerCropTransform().fitCenter().
                          diskCacheStrategy(DiskCacheStrategy.ALL)).
                  into(circleview);
      }



        int foodmakerStatus = 0;
        /*switcher.setOnCheckedChangeListener(new com.rey.material.widget.Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(com.rey.material.widget.Switch view, boolean checked) {
                if(checked){
                    foodmakerStatus = 1;
                    Toast.makeText(getApplication(), "enabled", Toast.LENGTH_SHORT).show();
                } else {
                    foodmakerStatus = 2;
                    Toast.makeText(getApplication(), "disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                setAllFieldEnable();
            }
        });

        /***
         *
         * */
        dispName = findViewById(R.id.dispName);
        dispEmail  = findViewById(R.id.dispEmail);
        userPhone  = findViewById(R.id.userPhone);
        userRatting  = findViewById(R.id.userRatting);
        userAddress  = findViewById(R.id.userAddress);
        dispcnic =  findViewById(R.id.dispcnic);
        setAllFieldWithDefault();


        /***
         *
         * */
        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Foodmaker foodmaker = Constant.foodmaker;
                foodmaker.setFoodmakerName(""+dispName.getText().toString());
                foodmaker.setFoodmakerEmail(""+dispEmail.getText().toString());
                foodmaker.setFoodmakerPhoneNumber(""+userPhone.getText().toString());
                foodmaker.setFoodmakerNic(""+dispcnic.getText().toString());
                if(switcher.isChecked()){
                    foodmaker.setFoodmakerActive(1);
                }else{
                    foodmaker.setFoodmakerActive(2);
                }
                if(userAddress.getText().toString() == Constant.foodmaker.getFoodmakerAddresId().getAddress()){
                    Address address = new Address(""+userAddress.getText().toString(), "  Karachi");
                    foodmaker.setFoodmakerAddresId(address);
                }
                foodmaker.setFoodmakerCreatedAt(null);
                foodmaker.setFoodmakerLastUpdated(null);



                foodmakerService.foodmakerSignup(foodmaker).enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        Toast.makeText(UserProfile.this, "Successfully Update ", Toast.LENGTH_LONG).show();
                        setAllFieldDisable();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                        Toast.makeText(UserProfile.this, "Connection Problem ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnCancel = (FloatingActionButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAllFieldWithDefault();
            }
        });

        /***
         *
         */

/**
 * */






    }

    public void setAllFieldEnable(){
        dispName = findViewById(R.id.dispName);
        dispEmail  = findViewById(R.id.dispEmail);
        userPhone  = findViewById(R.id.userPhone);
        userRatting  = findViewById(R.id.userRatting);
        userAddress  = findViewById(R.id.userAddress);
        dispcnic =  findViewById(R.id.dispcnic);


        //btn

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);

        dispEmail.setEnabled(true);
        dispName.setEnabled(true);
        dispEmail.setEnabled(true);
        userPhone.setEnabled(true);
        userRatting.setEnabled(true);
        userAddress.setEnabled(true);
        dispcnic.setEnabled(true);
    }
    public void setAllFieldDisable(){
        dispName = findViewById(R.id.dispName);
        dispEmail  = findViewById(R.id.dispEmail);
        userPhone  = findViewById(R.id.userPhone);
        userRatting  = findViewById(R.id.userRatting);
        userAddress  = findViewById(R.id.userAddress);
        dispcnic =  findViewById(R.id.dispcnic);
        //btn

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);


        dispEmail.setEnabled(false);
        dispName.setEnabled(false);
        dispEmail.setEnabled(false);
        userPhone.setEnabled(false);
        userRatting.setEnabled(false);
        userAddress.setEnabled(false);
        dispcnic.setEnabled(false);
    }
    public void setAllFieldWithDefault(){
        dispName = findViewById(R.id.dispName);
        dispEmail  = findViewById(R.id.dispEmail);
        userPhone  = findViewById(R.id.userPhone);
        userRatting  = findViewById(R.id.userRatting);
        userAddress  = findViewById(R.id.userAddress);
        dispcnic =  findViewById(R.id.dispcnic);
        //btn

        dispName.setText(""+Constant.foodmaker.getFoodmakerName());
        dispEmail.setText(""+Constant.foodmaker.getFoodmakerEmail());
        userPhone.setText(""+Constant.foodmaker.getFoodmakerPhoneNumber());
        userAddress.setText(""+Constant.foodmaker.getFoodmakerAddresId().getAddress());
        dispcnic.setText(""+Constant.foodmaker.getFoodmakerNic());
        setAllFieldDisable();
    }





}

