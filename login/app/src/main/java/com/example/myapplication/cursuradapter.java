package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class cursuradapter extends CursorAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<FoodItem> listFood;


    public cursuradapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return layoutInflater.inflate(R.layout.meal_list_item,parent,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView foodName = view.findViewById(R.id.tv_meal_name);
        TextView foodDescription = view.findViewById(R.id.tv_meal_description);
        TextView foodPrice = view.findViewById(R.id.tv_meal_price);
        ImageView foodImage = view.findViewById(R.id.iv_food_photo);
        foodName.setText(cursor.getString(1));
        foodDescription.setText(cursor.getString(2));
        foodPrice.setText("NT$ " + cursor.getString(3));
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(5),0,cursor.getBlob(5).length));
    }
}
