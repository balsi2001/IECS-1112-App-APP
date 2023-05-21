package com.example.myapplication;

public class FoodItem {
    private String foodName;
    private String foodDescription;
    private String foodPrice;
    private String foodPhotoName;

    private byte[] image;

    public FoodItem(String foodName, String foodDescription, String foodPrice, String foodPhotoName,byte[] image) {
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

    public String getFoodPhotoName() {
        return foodPhotoName;
    }

    public byte[] getImage() {
        return image;
    }
}
