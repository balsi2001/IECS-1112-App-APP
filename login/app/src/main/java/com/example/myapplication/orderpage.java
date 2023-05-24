package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
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
private Integer num=0;
    private File prjDir;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        prjDir = new File(this.getFilesDir() + "/tmp.jpg");
        up=findViewById(R.id.oeder_page_up_iv);
        down=findViewById(R.id.order_page_down_iv);
        editText=findViewById(R.id.order_et);
imageView=findViewById(R.id.order_page_iv);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("mealname");
        Integer price=bundle.getInt("mealprice");
        String id=bundle.getString("id");

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

     /*   while(!cursor.getString(0).equals(id)){

            cursor.moveToNext();
        }*/

       // img=cursor.getBlob(5);
      //  imageView.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));



        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.oeder_page_up_iv:
                        num++;
                        editText.setText(num.toString());
                        break;
                    case R.id.order_page_down_iv:
                        if(num-1>=0)
                            num--;
                        editText.setText(num.toString());
                        break;
                }
            }
        };


        up.setOnClickListener(onClickListener);
        down.setOnClickListener(onClickListener);
    }
}