package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private Button btnclear;
    private ListView lvMainMeals;
    private ListViewAdapter adapter;
    private DatabaseHandler databaseHandler;
    private Button egg;
    private Button toast;
    private Button drink;
    private Button burger;

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
        egg = findViewById(R.id.btn_eggcookie);
        toast = findViewById(R.id.btn_toast);
        drink = findViewById(R.id.btn_drink);
        burger = findViewById(R.id.btn_burger);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_eggcookie) {
                    showeggcookie();
                } else if (v.getId() == R.id.btn_drink) {
                    showdrink();
                } else if (v.getId() == R.id.btn_toast) {
                    showtoast();
                } else if (v.getId() == R.id.btn_burger) {
                    showburger();
                }
            }
        };
        burger.setOnClickListener(onClickListener);
        egg.setOnClickListener(onClickListener);
        toast.setOnClickListener(onClickListener);
        drink.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAllMeals();
    }

    private void showAllMeals() {
        Cursor cursor = databaseHandler.getAllMeals();
        ArrayList<FoodItem> listFood = new ArrayList<>();

        Cursor c = databaseHandler.getAllMeals();

        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setAdapter(cursuradapter);
    }

    private void showeggcookie() {

        ArrayList<FoodItem> listFood = new ArrayList<>();

        Cursor c = databaseHandler.gerOneMeal("eggcookie%");

        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setAdapter(cursuradapter);
    }

    private void showdrink() {

        ArrayList<FoodItem> listFood = new ArrayList<>();


        Cursor c = databaseHandler.gerOneMeal("drink%");

        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setAdapter(cursuradapter);
    }

    private void showburger() {


        Cursor c = databaseHandler.gerOneMeal("burger%");

        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setAdapter(cursuradapter);
    }

    private void showtoast() {

        ArrayList<FoodItem> listFood = new ArrayList<>();


        Cursor c = databaseHandler.gerOneMeal("toast%");

        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setAdapter(cursuradapter);
    }
}



