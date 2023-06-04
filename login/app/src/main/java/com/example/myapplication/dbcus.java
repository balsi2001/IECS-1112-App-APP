package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dbcus  {
    private AppCompatActivity activity;
    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "fcu_breakfast_cus.db";
    private static final String CREATE_MEAL_TABLE = "CREATE TABLE IF NOT EXISTS Meals(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "number interger not null, " +
            "price INTEGER NOT NULL, " +
            "photo TEXT ,"+"image blob,"+" hash text not null,"+"rate integer,"+"account text not null )";

/*

_id
name
int number
int price
photo
image
hash
int rate
account
 */
    private static final String DELETE_MEAL_ITEM = "";

    public dbcus(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void update(String id,String rate){
        ContentValues values = new ContentValues();

        database.execSQL("update Meals set rate=? where account=?",new String[]{rate,id});

    }
    public void open() {
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        database.execSQL(CREATE_MEAL_TABLE);
    }

    public void addMeal(String name, int num, int price,String hash,int rate,byte[] image,String account) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("number", num);
        values.put("price", price);
        values.put("rate",rate);
        values.put("hash",hash);
    values.put("account",account);
        values.put("image",image);
        database.insert("Meals", null, values);
    }

    public Cursor getAllMeals() {
        Cursor cursor = database.rawQuery("SELECT * FROM Meals", null);
        Toast.makeText(activity, cursor.getCount() + " is added", Toast.LENGTH_SHORT).show();

        return cursor;
    }

    public Cursor getone() {
        Cursor cursor=database.rawQuery("select _id,name,number,price,photo,image,hash, avg(rate) ,image from Meals  group by name  order by rate desc  ",null);
        Toast.makeText(activity, cursor.getCount() + " is added", Toast.LENGTH_SHORT).show();

        return cursor;
    }
    public  Cursor getrate(String name){
        Cursor cursor=database.rawQuery("select _id,name,number,price,photo,image,hash, avg(rate) ,image from Meals where account =? group by name  order by rate desc  ",new String[]{name});
       //Cursor cursor=database.rawQuery("select _id,name,number,price,photo,image,hash, avg(rate) ,image from Meals  group by name  order by rate desc  ",null);

        return cursor;
    }
    public Cursor gerOneMeal(String id){
        Cursor cursor=database.rawQuery("select * from Meals where  hash =?",new String[]{id});

        return cursor;
    }
    public Cursor gethashmeal(String i){
        Cursor cursor=database.rawQuery("select * from Meals where _id=\"id\"",null);
        return cursor;
    }
    public void deleteMeal(int id) {
        database.delete("Meals", "_id=" + id, null);
    }
}

