package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private Context content;
    private ArrayList<rate> listFood;
    private DatabaseHandler databaseHandler;


    public ListViewAdapter(Context content, ArrayList<rate> listFood) {
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
            view = LayoutInflater.from(content).inflate(R.layout.history_rate, viewGroup, false);
        rate foodItem = listFood.get(i);
        TextView foodName = view.findViewById(R.id.tv_meal_name);
        TextView foodDescription = view.findViewById(R.id.tv_meal_description);
        TextView foodPrice = view.findViewById(R.id.tv_meal_price);
        ImageView foodImage = view.findViewById(R.id.iv_food_photo);
        ImageButton ib[]={view.findViewById(R.id.btn_s1),view.findViewById(R.id.btn_s2),view.findViewById(R.id.btn_s3),view.findViewById(R.id.btn_s4),view.findViewById(R.id.btn_s5)};
        int is[]={R.id.btn_s1,R.id.btn_s2,R.id.btn_s3,R.id.btn_s4,R.id.btn_s5};

        for(int j=0;j<5;j++) {
            int tm=foodItem.getRate();
            if(foodItem.getRate()>=j){
                for(int k=0;k<=j;k++){
                    ib[k].setImageResource(R.drawable.s1);
                }
            }
        }
        View.OnClickListener listener=new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                for(int i=0;i<5;i++) {
                    if(v.getId()>=is[i]){
                        for(int j=0;j<5;j++){
                            ib[j].setImageResource(R.drawable.s0);

                        }
                        for(int j=0;j<=i;j++)
                            ib[j].setImageResource(R.drawable.s1);
                    }
                }
            }
        };
        for(int j=0;j<5;j++){
            ib[j].setOnClickListener(listener);
        }
        foodName.setText(foodItem.getName());
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(foodItem.getImage(),0,foodItem.getImage().length));
        return view;
    }

}