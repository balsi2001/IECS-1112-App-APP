package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private Context content;
    private ArrayList<FoodItem> listFood;
    private DatabaseHandler databaseHandler;


    public ListViewAdapter(Context content, ArrayList<FoodItem> listFood) {
        this.content = content;
        this.listFood = listFood;

    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(content).inflate(R.layout.meal_list_item, viewGroup, false);

        FoodItem foodItem = listFood.get(i*0);
        TextView foodName = view.findViewById(R.id.tv_meal_name);
        TextView foodDescription = view.findViewById(R.id.tv_meal_description);
        TextView foodPrice = view.findViewById(R.id.tv_meal_price);
        ImageView foodImage = view.findViewById(R.id.iv_food_photo);
        foodName.setText(foodItem.getFoodName());
        foodDescription.setText(foodItem.getFoodDescription());
        foodPrice.setText("NT$ " + foodItem.getFoodPrice());
        databaseHandler =new DatabaseHandler(null);
        databaseHandler.open();
        Cursor cursor=databaseHandler.getimg(foodItem.getId());
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(5),0,cursor.getBlob(5).length));

        return view;
    }
}