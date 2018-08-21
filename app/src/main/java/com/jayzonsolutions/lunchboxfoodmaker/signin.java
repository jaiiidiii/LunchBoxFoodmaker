package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.APIService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.app.Config;
import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;

import customfonts.MyEditText;

import customfonts.MyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class signin extends AppCompatActivity {

    ImageView sback;
    MyTextView login;
    String DeviceID = "not found";
    MyEditText userEmail;
    MyEditText userPassword;
    final Context context = this;

    // Session Manager Class
//    SessionManager session;


    private FoodmakerService foodmakerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        if(Constant.foodmaker != null){
            Intent in =new Intent(this,MainActivity.class);
            startActivity(in);
        }


        sback = findViewById(R.id.sinb);
        login = findViewById(R.id.sin);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        String userEmailStr = userEmail.getText().toString();
        String userPasswordStr = userPassword.getText().toString();

       /* // Session Manager
        session = new SessionManager(context);*/

        foodmakerService = ApiUtils.getFoodmakerService();

        displayFirebaseRegId();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    MyEditText email = (MyEditText)findViewById(R.id.userEmail);
                    MyEditText pass = (MyEditText)findViewById(R.id.userPassword);
                  //  foodmakerService.foodmakerLogin(email.getText().toString(), pass.getText().toString(),DeviceID).enqueue(new Callback<Foodmaker>() { //email:foodmakernew@gmail.com pass:testtest
                    foodmakerService.foodmakerLogin("amir@gmail.com", "27146",DeviceID).enqueue(new Callback<Foodmaker>() { //email:foodmakernew@gmail.com pass:testtest

                        @Override
                        public void onResponse(Call<Foodmaker> call, Response<Foodmaker> response) {

                            if(response.body() != null){
                                Constant.foodmaker = response.body();
                                Toast.makeText(signin.this,"Successfully Logged in",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(signin.this,MainActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<Foodmaker> call, Throwable t) {
                            Toast.makeText(signin.this,"Connection Problem ",Toast.LENGTH_LONG).show();

                        }
                    });
                }
                //api call end

  /*              // Creating user login session
                // For testing Junaid Khan name, email as follow
                // Use user real data
                session.createLoginSession(
                        MACaddress.toString(),
                        passEditText.getText().toString(),
                        TempUserIsAdmin.toString(),
                        TempUserID.toString(),
                        TempGroupID.toString(),
                        TempUserName.toString()
                );

                Intent myIntent = new Intent(signin.this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);



*/
               /* Intent intent = new Intent(signin.this,MainActivity.class);
                startActivity(intent);*/



            }
        });
        /*Intent intent = new Intent(signin.this,MainActivity.class);
        startActivity(intent);
*/
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(signin.this,LoginActivity.class);
                startActivity(it);
            }
        });
    }

    public boolean validate()
    {
        MyEditText name = (MyEditText)findViewById(R.id.userEmail);
        MyEditText pass = (MyEditText)findViewById(R.id.userPassword);

        if( name.getText().toString().length() == 0 && pass.getText().toString().length() == 0)
        {
            name.setError( "Email is required!" );
            pass.setError( "Password is required!" );
            return false;
        }

        else if( name.getText().toString().length() == 0 && pass.getText().toString().length() != 0)
        {
            name.setError( "Email is required!" );
            return false;
        }

        else if( pass.getText().toString().length() == 0 && name.getText().toString().length() != 0)
        {
            pass.setError( "Password is required!" );
            return false;
        }
            return true;
    }


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("DeviceID", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            Toast.makeText(this, "Firebase Reg Id: " + regId, Toast.LENGTH_SHORT).show();
            DeviceID = regId;
        } else
            Toast.makeText(this, "Firebase Reg Id is not received yet!", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Constant.foodmaker != null){
            Intent in =new Intent(this,MainActivity.class);
            startActivity(in);
        }
    }
}



