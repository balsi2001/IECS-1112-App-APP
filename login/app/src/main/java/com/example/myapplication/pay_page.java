package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pay_page extends AppCompatActivity {
  private  dbcus dbcus;
  private TextView textView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pay_page);

    dbcus=new dbcus(this);
    dbcus.open();


    Bundle bundle = getIntent().getExtras();
    String hash = bundle.getString("hash");

    textView = findViewById(R.id.et_pay_meal_list);
    String str="";

     Cursor cursor=dbcus.gerOneMeal(hash);

     while(cursor.moveToNext()){
       //price
       //name
       //num
       str+="餐點: "+cursor.getString(1)+" 數量:"+cursor.getInt(3)+" 總價錢:"+cursor.getInt(2)+"\n";

     }


    textView.setText(str);
  }

}/*
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
}*/