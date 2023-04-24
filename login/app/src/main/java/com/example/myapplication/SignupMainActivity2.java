package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class SignupMainActivity2 extends AppCompatActivity {
  private EditText etuser;
  private EditText etpassword;
  private Button btnsignup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup_main2);

    etuser = findViewById(R.id.et_user);
    etpassword = findViewById(R.id.et_pass);
    btnsignup = findViewById(R.id.btnsignup);

    View.OnClickListener listener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = etuser.getText().toString();
        String password = etpassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
          Toast.makeText(SignupMainActivity2.this, "請輸入帳號和密碼", Toast.LENGTH_LONG).show();
          return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String username1 = sharedPreferences.getString("username", "").toString();
        if(username.equals(username1)){
          Toast.makeText(SignupMainActivity2.this, "此帳號已存在", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(SignupMainActivity2.this, signingActivity.class);
          startActivity(intent);
        }else {
        SharedPreferences.Editor editot = sharedPreferences.edit();
        editot.putString("username", username);
        editot.putString("password", password);
        editot.commit();
        Toast.makeText(SignupMainActivity2.this, "建立成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SignupMainActivity2.this, signingActivity.class);
        startActivity(intent);

      }}
    };
    btnsignup.setOnClickListener(listener);
  }
}