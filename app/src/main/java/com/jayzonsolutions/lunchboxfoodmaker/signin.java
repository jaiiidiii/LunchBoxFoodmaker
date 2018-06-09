package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.APIService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.ApiUtils;
import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;

import customfonts.MyEditText;

import customfonts.MyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class signin extends AppCompatActivity {

    ImageView sback;
    MyTextView login;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sback = findViewById(R.id.sinb);
        login = findViewById(R.id.sin);

        mAPIService = ApiUtils.getAPIService();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(signin.this,"clicked",Toast.LENGTH_LONG).show();
              //api call
             /*   if(validate())
                {
                    mAPIService.savePost("sohail@gmail.com", "123456").enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                            Toast.makeText(signin.this,"success"+response.body().getStatus(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(signin.this,"failed ",Toast.LENGTH_LONG).show();

                        }
                    });
                }
                //api call end
*/
                Intent intent = new Intent(signin.this,MainActivity.class);
                startActivity(intent);



            }
        });

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
        MyEditText name = (MyEditText)findViewById(R.id.usrusr);
        MyEditText pass = (MyEditText)findViewById(R.id.pswrd);

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




}
