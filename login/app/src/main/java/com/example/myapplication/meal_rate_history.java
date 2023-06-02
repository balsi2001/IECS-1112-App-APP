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

public class meal_rate_history extends AppCompatActivity {
    private dbcus dbcus;
    private Button btn_go_main;
    private ListView listView;
    private ArrayList<rate> listFood = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_rate_history);
btn_go_main=findViewById(R.id.btn_go_main);
        dbcus=new dbcus(this);
        dbcus.open();
shoe_history_rate();
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(meal_rate_history.this,MainActivity.class);
                startActivity(intent);
            }
        };
        btn_go_main.setOnClickListener(listener);
    }
    private void shoe_history_rate(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String account=sharedPreferences.getString("account","");
        Cursor c = dbcus.getrate(account);



        cursuradapter cursuradapter = new currate(meal_rate_history.this, c, 0);
        listView = findViewById(R.id.listview);

        listView.setAdapter(cursuradapter);

    }
}