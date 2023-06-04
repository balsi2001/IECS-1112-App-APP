package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

//53/21
public class solve_order extends AppCompatActivity {
    private dbcus dbcus;
    private String date;
    private int selectedId;
    private Button btn_del;
    private  Button gomain;
    private ListView listView;
    Map<String,Integer> map=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_order);

        View.OnClickListener listener = view -> {

            if (view.getId() == R.id.btn_del) {
                // check if selected or not
                if (this.selectedId != -1) {
                    dbcus.updateore(String.valueOf(this.selectedId));
                    Toast.makeText(solve_order.this, "訂單已完成!", Toast.LENGTH_SHORT).show();
                }

                this.selectedId = -1;
            }else{
                Intent intent=new Intent(solve_order.this,MainActivity.class);
                startActivity(intent);
            }

            showAllMeals();
        };

        dbcus=new dbcus(this);
        dbcus.open();

Bundle bundle=getIntent().getExtras();
      date=  bundle.getString("date");
        AdapterView.OnItemClickListener listviewItemClickListener = (adapterView, view, index, id) -> {
            Cursor cursor = (Cursor) adapterView.getAdapter().getItem(index);
           selectedId = Integer.parseInt(cursor.getString(0));

            Toast.makeText(solve_order.this, "id: " + cursor.getString(0) + " is selected", Toast.LENGTH_SHORT).show();



        };
    //2023-06-04
listView=findViewById(R.id.lv_order);
gomain=findViewById(R.id.btn_go_back);
gomain.setOnClickListener(listener);
btn_del=findViewById(R.id.btn_del);
btn_del.setOnClickListener(listener);

listView.setOnItemClickListener(listviewItemClickListener);
showAllMeals();

    }
    private void showAllMeals() {
        Cursor cursor=dbcus.getallorder(date);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                solve_order.this,
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                new String[] {"name","sum(number)" ,"sum(price)"},
                new int[] {android.R.id.text1, android.R.id.text2},
                0
        );
        listView.setAdapter(adapter);
    }
}