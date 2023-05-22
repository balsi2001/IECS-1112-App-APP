package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //private Button btnclear;
  private ListView lvMainMeals;
  private ListViewAdapter adapter;
    private DatabaseHandler databaseHandler;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      databaseHandler = new DatabaseHandler(this);

      databaseHandler.open();
      SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
      boolean isLogin = sharedPreferences.getBoolean("signedin", false);
      if (!isLogin) {
          Intent intent = new Intent(MainActivity.this, signingActivity.class);
          startActivity(intent);
      }


    }
    @Override
    protected void onResume() {
        super.onResume();
        showAllMeals();
    }

    private void showAllMeals() {
        Cursor cursor = databaseHandler.getAllMeals();
        ArrayList<FoodItem> listFood = new ArrayList<>();
/*
        while (cursor.moveToNext()) {
            byte[] bytes=cursor.getBlob(5);

           Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            listFood.add(new FoodItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),bitmap,cursor.getString(0)));

        }*/
        Cursor c=databaseHandler.getAllMeals();
    cursuradapter cursuradapter=new cursuradapter(MainActivity.this,c,0);
    lvMainMeals=findViewById(R.id.lv_main_meals);
       lvMainMeals.setAdapter(cursuradapter);
    }
  }



