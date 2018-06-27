package com.jayzonsolutions.lunchboxfoodmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodmakerDishes {
    @SerializedName("dishId")
    @Expose
    private Integer dishId;
    @SerializedName("foodmakerid")
    @Expose
    private Integer foodmakerid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imagepath")
    @Expose
    private String imagepath;
    @SerializedName("price")
    @Expose
    private String price;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getFoodmakerid() {
        return foodmakerid;
    }

    public void setFoodmakerid(Integer foodmakerid) {
        this.foodmakerid = foodmakerid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
