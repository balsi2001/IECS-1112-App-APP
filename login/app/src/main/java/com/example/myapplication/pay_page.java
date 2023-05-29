package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
  public void onClick(View view) {
    String num =tvPhoneNum.getText().toString();

    switch (view.getId()){
      case R.id.bt_in_use:
        AlertDialog.Builder dialog = new AlertDialog.Builder( MainActivity.this );
        dialog.setTitle("確認視窗");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("請至櫃台結帳");
        dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            finish();
          }
        });
        dialog.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {

          }
        });
        dialog.show();
        break;
      case R.id.btn_close:
        AlertDialog.Builder dialog = new AlertDialog.Builder( MainActivity.this );
        dialog.setTitle("確認視窗");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("請至櫃台結帳");
        dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            finish();
          }
        });
        dialog.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {

          }
        });
        dialog.show();
        break;
    }
  }
  }
}