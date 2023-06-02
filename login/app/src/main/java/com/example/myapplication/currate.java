package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class currate extends cursuradapter{
    private LayoutInflater layoutInflater;

private Map<String,Integer> map=new HashMap<>();

    public currate(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return layoutInflater.inflate(R.layout.history_rate, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView foodName = view.findViewById(R.id.tv_meal_name);
        TextView foodDescription = view.findViewById(R.id.tv_meal_description);
        TextView foodPrice = view.findViewById(R.id.tv_meal_price);
        ImageView foodImage = view.findViewById(R.id.iv_food_photo);
        foodName.setText(cursor.getString(1));
        ImageButton ib[]={view.findViewById(R.id.btn_s1),view.findViewById(R.id.btn_s2),view.findViewById(R.id.btn_s3),view.findViewById(R.id.btn_s4),view.findViewById(R.id.btn_s5)};

        int t=cursor.getInt(7);
        for(int i=0;i<5;i++){
            if(i<t){
                ib[i].setImageResource(R.drawable.s1);
            }
            else
                ib[i].setImageResource(R.drawable.s0);
        }

        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(5), 0, cursor.getBlob(5).length));

    }
}
