package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pay_page extends AppCompatActivity {

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pay_page);

    Bundle bundle = getIntent().getExtras();
    String name = bundle.getString("name");

    int price = bundle.getInt("price");

    textView=findViewById(R.id.et_pay_meal_list);

    textView.setText(name+" "+price+"\n");
  }
}