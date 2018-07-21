package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.CustomerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Address;
import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Customer;
//import com.jayzonsolutions.lunchboxfoodmaker.model.CustomerAddress;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import customfonts.MyEditText;
import customfonts.MyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class signup extends AppCompatActivity implements Validator.ValidationListener
{
    ImageView sback;
    MyTextView signUp;
    @NotEmpty
    MyEditText fname;
    @NotEmpty
    @Email
    MyEditText userEmail;
    @NotEmpty
    MyEditText userPassword;
    @NotEmpty
    MyEditText userCnic;
    @NotEmpty
    MyEditText userPhone;
    @NotEmpty
    MyEditText userAddress;



    private FoodmakerService foodmakerService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = findViewById(R.id.btnsignup);

         fname = findViewById(R.id.fname);
         userEmail = findViewById(R.id.userEmail);
         userPassword = findViewById(R.id.userPassword);
         userCnic = findViewById(R.id.userCnic);
         userPhone = findViewById(R.id.userPhone);
         userAddress = findViewById(R.id.userAddress);

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        foodmakerService =  ApiUtils.getFoodmakerService();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validator.validate();
            }
        });

        sback = findViewById(R.id.sback);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(signup.this, LoginActivity.class);
                startActivity(it);

            }
        });
    }

    @Override
    public void onValidationSucceeded() {

        String fnameTxt = fname.getText().toString();
        String userEmailTxt = userEmail.getText().toString();
        String userPasswordTxt =userPassword.getText().toString();
        String userCnicTxt = userCnic.getText().toString();
        String userPhoneTxt =userPhone.getText().toString();
        String userAddressTxt =userAddress.getText().toString();

        Address address = new Address(userAddressTxt,"karachi");
        address.setAddress(userAddressTxt);
        address.setCity("Karachi");

        Foodmaker foodmaker = new Foodmaker();
        foodmaker.setFoodmakerName(fnameTxt);
        foodmaker.setFoodmakerEmail(userEmailTxt);
        foodmaker.setFoodmakerpassword(userPasswordTxt);
        foodmaker.setFoodmakerNic(userCnicTxt);
        foodmaker.setFoodmakerPhoneNumber(userPhoneTxt);
        foodmaker.setFoodmakerAddresId(address);
        foodmaker.setFoodmakerAccessType(1);



        foodmakerService.foodmakerSignup(foodmaker).enqueue(new Callback<ApiResponse>(){

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Toast.makeText(signup.this,"success ",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(signup.this,"failer ",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for(ValidationError error : errors){

            View view = error.getView();

            String message = error.getCollatedErrorMessage(this);

            //display the error message
            if(view instanceof MyEditText){
                ((MyEditText) view).setError(message);
                view.requestFocus();

            }else{
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
