package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

import customfonts.MyEditText;
import customfonts.MyTextView;


public class SingleDishFragment extends Fragment {
    private static FoodmakerDishes foodmakerDish ;

    Context context = getActivity();
    MyTextView dish_name;
    MyTextView dish_price;
    MyTextView dish_detail;
    ImageView dish_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_single_dish, container, false);

        dish_name = v.findViewById(R.id.foodmakerName);
         dish_price = v.findViewById(R.id.product_price);
        dish_detail = v.findViewById(R.id.foodmakerdescription);
         dish_image = v.findViewById(R.id.foodmakerImage);

        if(SingleDishFragment.foodmakerDish != null){
            String imagePath = ((foodmakerDish.getImagepath() != null)?foodmakerDish.getImagepath():"asdadadadadadadadadadadadasd");



            Glide.with(context).load(ApiUtils.BASE_URL+(imagePath.substring(21))).
                    apply(RequestOptions.
                            centerCropTransform().fitCenter().
                            diskCacheStrategy(DiskCacheStrategy.ALL)).
                    into(dish_image);
            dish_price.setText(""+SingleDishFragment.foodmakerDish.getPrice());
            dish_name.setText(""+SingleDishFragment.foodmakerDish.getName());
            dish_name.setText(""+SingleDishFragment.foodmakerDish.getDescription());
        }




        return v;
        // Inflate the layout for this fragment

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void setFoodmakerDish(FoodmakerDishes foodmakerDish) {
        SingleDishFragment.foodmakerDish = foodmakerDish;
    }
    public FoodmakerDishes getFoodmakerDish() {
        return SingleDishFragment.foodmakerDish;
    }
}
