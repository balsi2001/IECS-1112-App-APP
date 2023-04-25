package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  //private Button btnclear;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
    boolean isLogin = sharedPreferences.getBoolean("signedin", false);
    if (!isLogin) {
      Intent intent = new Intent(MainActivity.this, signingActivity.class);
      startActivity(intent);
    }
   ImageView[] n=new ImageView[5];
    LinearLayout t=findViewById(R.id.l1);
    for(int i=0;i<5;i++){
      ImageView ii=new ImageView(this);
      ii.setImageResource(R.drawable.burger);
      ii.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
      n[i]=ii;
      t.addView(ii);
    }
    for(int i=0;i<5;i++)
    {
      n[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent nn=new Intent(MainActivity.this,signingActivity.class);
          startActivity(nn);
        }
      });
    }
    /*btnclear = findViewById(R.id.btn_clear);
    View.OnClickListener listener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(MainActivity.this, signingActivity.class);
        startActivity(intent);
      }
    };
    Toast.makeText(MainActivity.this, "請點餐", Toast.LENGTH_SHORT).show();
btnclear.setOnClickListener(listener);*/
  }
  /*protected void onStart() {
    super.onStart();
    Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
  }

  protected void onResume() {
    super.onResume();
    Toast.makeText(MainActivity.this, "resume", Toast.LENGTH_SHORT).show();
  }

  protected void onPause() {
    super.onPause();
    Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();
  }

  protected void onStop() {
    super.onStop();
    Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Toast.makeText(MainActivity.this, "restart", Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Toast.makeText(MainActivity.this, "destroy", Toast.LENGTH_SHORT).show();
  }*/
}

