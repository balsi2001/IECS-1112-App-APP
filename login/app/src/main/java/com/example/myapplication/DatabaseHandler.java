package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHandler {
    private AppCompatActivity activity;
    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "fcu_breakfast.db";
    private static final String CREATE_MEAL_TABLE = "CREATE TABLE IF NOT EXISTS Meals(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT, " +
            "price INTEGER NOT NULL, " +
            "photo TEXT,"+"image blob)";

    private static final String DELETE_MEAL_ITEM = "";

    public DatabaseHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open() {
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        database.execSQL(CREATE_MEAL_TABLE);
    }

    public void addMeal(String name, String description, int price, String photo,byte[] image) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("photo", photo);
        values.put("image",image);
        database.insert("Meals", null, values);
    }

    public Cursor getAllMeals() {
        Cursor cursor = database.rawQuery("SELECT * FROM Meals", null);
        Toast.makeText(activity, cursor.getCount() + " is added", Toast.LENGTH_SHORT).show();

        return cursor;
    }

    public void deleteMeal(int id) {
        database.delete("Meals", "_id=" + id, null);
    }
}
