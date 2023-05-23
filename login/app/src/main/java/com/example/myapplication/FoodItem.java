package com.example.myapplication;

import android.graphics.Bitmap;

public class FoodItem {
    private String foodName;
    private String foodDescription;
    private String foodPrice;
    private String foodPhotoName;
private String id;
    private Bitmap image;

    public FoodItem(String foodName, String foodDescription, String foodPrice, String foodPhotoName, Bitmap image) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodPhotoName = foodPhotoName;
        this.image=image;

    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getId() {
        return id;
    }

    public String getFoodPhotoName() {
        return foodPhotoName;
    }

    public Bitmap getImage() {
        return image;
    }
}
