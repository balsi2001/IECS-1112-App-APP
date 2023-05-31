package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class orderpage extends AppCompatActivity {
private ImageView imageView;
private EditText editText;
private ImageView up;
private ImageView down;

private String hash;
private dbcus dbcus;
private Button gopay;
private Integer num=0;
    private File prjDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        dbcus=new dbcus(this);
        dbcus.open();
        prjDir = new File(this.getFilesDir() + "/tmp.jpg");
        up=findViewById(R.id.oeder_page_up_iv);
        down=findViewById(R.id.order_page_down_iv);
        editText=findViewById(R.id.order_et);
        gopay=findViewById(R.id.btn_gotopay);
imageView=findViewById(R.id.order_page_iv);
        Bundle bundle=getIntent().getExtras();


        String name=bundle.getString("mealname");
        Integer price=bundle.getInt("mealprice");
        String id=bundle.getString("id");
hash=bundle.getString("hash");
        try {
            FileInputStream image = openFileInput("tmp.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(image);
            image.close();
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

      if(editText.getText().toString().length()>0)
        num=parseInt(editText.getText().toString());
      else
        num=1;
      Log.d("num",num.toString());
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.oeder_page_up_iv:

                        num++;
                      Log.d("num",num.toString());
                        editText.setText(num.toString());
                        break;
                    case R.id.order_page_down_iv:

                        if(num-1>=0)
                            num--;

                      Log.d("num",num.toString());
                        editText.setText(num.toString());
                        break;
                  case R.id.btn_gotopay:
                    Intent intent=new Intent(orderpage.this, pay_page.class);
                    Bundle bundle=new Bundle();


                    dbcus.addMeal(name,num,num*price,hash);
                    bundle.putInt("price",price*num);
                    num=price*num;
                    Log.d("num",num.toString());
                    bundle.putString("name",name);
                      bundle.putString("hash",hash);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                }
            }
        };

        gopay.setOnClickListener(onClickListener);
        up.setOnClickListener(onClickListener);
        down.setOnClickListener(onClickListener);
    }
}