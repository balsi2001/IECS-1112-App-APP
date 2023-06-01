package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class dbuser {
  private AppCompatActivity activity;
  private SQLiteDatabase database;
  private static final String DATABASE_NAME = "user.db";
  private static final String CREATE_MEAL_TABLE = "CREATE TABLE IF NOT EXISTS users(" +
    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    "account TEXT NOT NULL, " +
    "password TEXT not null )";


  private static final String DELETE_MEAL_ITEM = "";

  public dbuser(AppCompatActivity activity) {
    this.activity = activity;
  }

  public void open() {
    database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    database.execSQL(CREATE_MEAL_TABLE);
  }

  public void adduser(String account, String password) {
    ContentValues values = new ContentValues();
    values.put("account", account);

    try {
      values.put("password",hashPassword(password));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    database.insert("users", null, values);
  }

  public Cursor getallusers() {
    Cursor cursor = database.rawQuery("SELECT * FROM users", null);
    Toast.makeText(activity, cursor.getCount() + " is added", Toast.LENGTH_SHORT).show();

    return cursor;
  }

  public Cursor getoneusername(String id){
    Cursor cursor=database.rawQuery("select account from users where  account =?",new String[]{id});

    return cursor;
  }
  public Cursor g(String i){
    Cursor cursor=database.rawQuery("select * from users where _id=\"id\"",null);
    return cursor;
  }
  public void deleteuser(int id) {
    database.delete("users", "_id=" + id, null);
  }
  public static String hashPassword(String password) throws NoSuchAlgorithmException {

    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.reset();
    md.update(password.getBytes());
    byte[] mdArray = md.digest();
    StringBuilder sb = new StringBuilder(mdArray.length * 2);
    for(byte b : mdArray) {
      int v = b & 0xff;
      if(v < 16)
        sb.append('0');
      sb.append(Integer.toHexString(v));
    }



    return sb.toString();

  }
}


