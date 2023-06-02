package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;

public class signingActivity extends AppCompatActivity {
  private Button btnsignin;
  private Button btnsignup;

private dbuser dbuser;
private Cursor cursor;
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
    dbuser=new dbuser(this);
    dbuser.open();
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
        cursor=dbuser.getallusers();
        try {
          password= com.example.myapplication.dbuser.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
        }
        int ch=0;
        while(cursor.moveToNext()){
          //id
          //acc
          //pw
          if(username.equals(cursor.getString(1))&&password.equals(cursor.getString(2))){
            sharedpreferences.edit().putBoolean("signedin", true).apply();
            if(username.equals("admin"))
              sharedpreferences.edit().putBoolean("admin", true).apply();
            else
              sharedpreferences.edit().putBoolean("admin", false).apply();
            sharedpreferences.edit().putString("account",username).apply();
            Intent intent = new Intent(signingActivity.this, MainActivity.class);
            startActivity(intent);
            ch=1;

            break;
          }
        }


if(ch==0)
        Toast.makeText(signingActivity.this, "此帳號或密碼不存在", Toast.LENGTH_SHORT).show();


      }
    };
    btnsignin.setOnClickListener(onClickListener);
    btnsignup.setOnClickListener(onClickListener);
  }
}