package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signingActivity extends AppCompatActivity {
  private Button btnsignin;
  private Button btnsignup;


  private EditText etusername;
  private EditText etpassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signing);
    btnsignin = findViewById(R.id.btnlogin);
    btnsignup = findViewById(R.id.btn_signup);
    etusername = findViewById(R.id.et_username);
    etpassword = findViewById(R.id.et_password);

    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         if (v.getId() == R.id.btn_signup) {
          Intent intent = new Intent(signingActivity.this, SignupMainActivity2.class);
          startActivity(intent);
          return;
        }
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
          Toast.makeText(signingActivity.this, "請輸入帳號和密碼", Toast.LENGTH_SHORT).show();
          return;
        }
        SharedPreferences sharedpreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String username1 = sharedpreferences.getString("username", "").toString();
        String password1 = sharedpreferences.getString("password", "").toString();
        if (username.equals(username1) && password.equals(password1)) {
          sharedpreferences.edit().putBoolean("signedin", true).apply();
          Intent intent = new Intent(signingActivity.this, MainActivity.class);
          startActivity(intent);
          return;
        } else {
          Toast.makeText(signingActivity.this, "此帳號或密碼不存在", Toast.LENGTH_SHORT).show();
        }

        /*if (v.getId() == R.id.btnlogin) {
          Intent intent = new Intent(signingActivity.this, MainActivity.class);
          startActivity(intent);

        }*/
      }
    };
    btnsignin.setOnClickListener(onClickListener);
    btnsignup.setOnClickListener(onClickListener);
  }
}