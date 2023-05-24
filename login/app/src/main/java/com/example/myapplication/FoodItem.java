package com.example.myapplication;

public class FoodItem {
    private String foodName;
    private String foodDescription;
    private String foodPrice;
    private String foodPhotoName;
private String id;
    private byte[] image;

    public FoodItem(String foodName, String foodDescription, String foodPrice, String foodPhotoName,byte[] image,String id) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodPhotoName = foodPhotoName;
        this.image=image;
    this.id=id;
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

    public byte[] getImage() {
        return image;
    }
}
