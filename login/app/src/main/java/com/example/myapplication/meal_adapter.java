package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

public class meal_adapter extends BaseAdapter {
private String mealname;
private int mealprice;
private int num;
private ImageView up;
private ImageView down;
private EditText editText;

private ImageView imageView;

private Bitmap image;
private Context context;
    public meal_adapter(Context context, Bitmap image, String mealname, int num, int mealprice){
        this.image=image;
        this.context=context;
        this.mealname=mealname;
        this.mealprice=mealprice;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.activity_orderpage, viewGroup, false);
        up=view.findViewById(R.id.oeder_page_up_iv);
        down=view.findViewById(R.id.order_page_down_iv);
        editText=view.findViewById(R.id.order_et);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.oeder_page_up_iv){
                    num=parseInt(editText.getText().toString());

                    editText.setText(num+1);
                }
                else if(v.getId()==R.id.order_page_down_iv){
                    num=parseInt(editText.getText().toString());
                if(num-1>=0)
                    editText.setText(num-1);
                else
                    editText.setText("請重新輸入大於等於0的數字");
                }
            }
        };
up.setOnClickListener(onClickListener);
down.setOnClickListener(onClickListener);
imageView.setImageBitmap(image);
        return view;
    }
}
