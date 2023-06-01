package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dbuser {
  private AppCompatActivity activity;
  private SQLiteDatabase database;
  private static final String DATABASE_NAME = "users.db";
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
    values.put("password", password.hashCode());
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
}


