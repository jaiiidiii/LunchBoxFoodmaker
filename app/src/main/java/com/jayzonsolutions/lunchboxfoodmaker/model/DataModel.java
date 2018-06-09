package com.jayzonsolutions.lunchboxfoodmaker.model;

public class DataModel {


    public String ItemKey;
    public String Name;
    public String Price;
    public String CategoryKey;
    public String barCode;
    public String Stock;
    public String Image;
    public String CategoryName;
    public int position ;

    public DataModel() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCategoryName() {
        return CategoryName;
    }

   public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public DataModel(String categoryKey, String categoryName) {
        CategoryKey = categoryKey;
        CategoryName = categoryName;
    }

    public DataModel(String itemKey, String name, String price, String categoryKey, String barCode, String stock, String image) {
        ItemKey = itemKey;
        Name = name;
        Price = price;
        CategoryKey = categoryKey;
        this.barCode = barCode;
        Stock = stock;
        Image = image;
    }

    public String getItemKey() {
        return ItemKey;
    }

    public void setItemKey(String itemKey) {
        ItemKey = itemKey;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategoryKey() {
        return CategoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        CategoryKey = categoryKey;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


}