package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.DishService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerDishesService;
import com.jayzonsolutions.lunchboxfoodmaker.model.ApiResponse;
import com.jayzonsolutions.lunchboxfoodmaker.model.Dish;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customfonts.MyEditText;
import customfonts.MyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDish extends AppCompatActivity {

    private DishService dishService;
    private FoodmakerDishesService foodmakerDishesService;
    Spinner spinner;
    MyEditText dishName;
    MyEditText dishPrice;
    MyEditText dishDescription;
    MyTextView btnRegisterDish;


    /*
    *image upload
     */
    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;

    public static int selectedItemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);


        imageView = (ImageView) findViewById(android.R.id.icon);

        // resources initialization
        dishName = findViewById(R.id.dishName);
        dishDescription = findViewById(R.id.dishDescription);
        btnRegisterDish = findViewById(R.id.btnRegisterDish);
        dishPrice = findViewById(R.id.dishPrice);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.dishCatSpinner);
        // Spinner click listener





        /**
         * get services */

        dishService = ApiUtils.getDishService();
        foodmakerDishesService = ApiUtils.getFoodmakerDishes();


        /**
         * get dishes for cob=mbo spinner
         * start
         * */
        final List<String> categories = new ArrayList<String>();
        final Map<String,Integer> categoriesKeyVlaue = new HashMap<String,Integer>();

        dishService.getDishList().enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {

                for(Dish dish: response.body()) {
                    categories.add(dish.getDishName());
                    categoriesKeyVlaue.put(dish.getDishName(),dish.getDishId());
                }
                // Spinner Drop down elements
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddDish.this, android.R.layout.simple_spinner_item, categories);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                Toast.makeText(AddDish.this,"failed ",Toast.LENGTH_LONG).show();

            }
        });


        /**
         * get dishes for cob=mbo spinner
         * end
         * */


        // spinner.setOnItemSelectedListener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("***********", "THIS ");
                String selectValue = (String) parent.getSelectedItem();
                 selectedItemId = categoriesKeyVlaue.get(selectValue); //getting id of selected dish

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("***********", "THIS Nothing ");
            }
        });



        btnRegisterDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String dishNameData =  dishName.getText().toString();
                String dishDescriptionData =  dishDescription.getText().toString();
                String dishPriceData = dishPrice.getText().toString();

                FoodmakerDishes foodmakerDishes =new FoodmakerDishes();
                foodmakerDishes.setDescription(dishDescriptionData);
                foodmakerDishes.setDishId(selectedItemId);
                foodmakerDishes.setFoodmakerid(1); //set default foodmaker id
                foodmakerDishes.setPrice(dishPriceData);

                foodmakerDishesService.addFoodmakerDishes(foodmakerDishes).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                        Toast.makeText(AddDish.this,""+response.body().getStatus(),Toast.LENGTH_LONG).show();
                        Intent it = new Intent(AddDish.this,LoginActivity.class);
                        startActivity(it);
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(AddDish.this,"failed ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bitmap = getPath(data.getData());
            imageView.setImageBitmap(bitmap);
        }
    }
    private Bitmap getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }
    public void pickPhoto(View view) {
        //TODO: launch the photo picker
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);

    }
    public void uploadPhoto(View view) {
        try {
            executeMultipartPost();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void executeMultipartPost(){
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();

            Bitmap bitmap = drawable.getBitmap();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);

            byte[] data = bos.toByteArray();
            Toast.makeText(AddDish.this,"btye array = "+data,Toast.LENGTH_LONG).show();

        }catch(Exception e) {

            // handle exception here
            e.printStackTrace();
        }

        }

}

