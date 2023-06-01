package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupMainActivity2 extends AppCompatActivity {
  private EditText etuser;
  private EditText etpassword;
  private Button btnsignup;
  private Cursor cursor;
  private dbuser dbuser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup_main2);
    dbuser=new dbuser(this);
    dbuser.open();
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
        cursor= dbuser.getallusers();
   int ch=0;
        while(cursor.moveToNext()){
          //id
          //acc
          //pw
          if(username.equals(cursor.getString(1))){
            Toast.makeText(SignupMainActivity2.this, "此帳號已存在", Toast.LENGTH_LONG).show();

            ch=1;
            break;
          }
        }
        if(ch!=1)
        dbuser.adduser(username,password);
        cursor=dbuser.getallusers();

          Toast.makeText(SignupMainActivity2.this, "建立成功", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(SignupMainActivity2.this, signingActivity.class);
          startActivity(intent);

        }
    };
    btnsignup.setOnClickListener(listener);
  }
}