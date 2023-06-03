package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class meal_rate_history extends AppCompatActivity {
    private dbcus dbcus;

    private Button btn_go_rate;
    private Button btn_go_main;
    private  int idd;
    private ListView listView;
    private ArrayList<rate> listFood = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_rate_history);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String mealname= sharedPreferences.getString("mealname","");
        btn_go_main=findViewById(R.id.btn_go_main);
        dbcus=new dbcus(this);
        btn_go_rate=findViewById(R.id.btn_go_rate);
        dbcus.open();
        listView = findViewById(R.id.listview);
        shoe_history_rate();
        int is[]={R.id.btn_s1,R.id.btn_s2,R.id.btn_s3,R.id.btn_s4,R.id.btn_s5};
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case  R.id.btn_go_main:
                        Intent intent =new Intent(meal_rate_history.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_go_rate:
                        Toast.makeText(meal_rate_history.this,"更新成功",Toast.LENGTH_SHORT).show();
                        shoe_history_rate();
                }

            }
        };




btn_go_rate.setOnClickListener(listener);
        btn_go_main.setOnClickListener(listener);
    }
    private void shoe_history_rate(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String acc= sharedPreferences.getString("acc","");
        Cursor c = dbcus.getrate(acc);

        cursuradapter cursuradapter = new currate(meal_rate_history.this,c,0);

        listView.setAdapter(cursuradapter);

    }
}