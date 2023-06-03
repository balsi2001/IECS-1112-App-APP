package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;



import java.util.HashMap;
import java.util.Map;

public class currate extends cursuradapter{
    private LayoutInflater layoutInflater;
    private  dbcus dbcus;

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
            if(i<=t){
                ib[i].setImageResource(R.drawable.s1);
            }
            else
                ib[i].setImageResource(R.drawable.s0);
        }
int is[]={R.id.btn_s1,R.id.btn_s2,R.id.btn_s3,R.id.btn_s4,R.id.btn_s5};
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
        /*

_id 0
name 1
int number 2
int price 3
photo 4
image 5
hash 6
int rate 7
account 8
 */
        for(int i=0;i<5;i++){
            ib[i].setOnClickListener(listener);

        }
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(5), 0, cursor.getBlob(5).length));

    }
}
