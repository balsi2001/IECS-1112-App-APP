package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class mealmanager extends AppCompatActivity {
    private EditText etMealName;
    private EditText etMealDescription;
    private EditText etMealPrice;
    private Button btnAddMeal;
    private Button btnDelMeal;
    private ListView lvMeals;
    private Button button;
    private DatabaseHandler databaseHandler;
    private int selectedId = -1;
    private EditText etMealPhoto;

    private  String[] p={".png",".jpg",".jpeg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealmanager);

        etMealName = findViewById(R.id.et_meal_name);
        etMealDescription = findViewById(R.id.et_meal_description);
        etMealPrice = findViewById(R.id.et_meal_price);
        btnAddMeal = findViewById(R.id.btn_add_meal);
        btnDelMeal = findViewById(R.id.btn_del_meal);
        lvMeals = findViewById(R.id.lv_all_meals);
        etMealPhoto = findViewById(R.id.et_meal_photo_name);
        button=findViewById(R.id.btn_backtomain);
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.open();

        showAllMeals();

        View.OnClickListener listener = view -> {
            if (view.getId() == R.id.btn_add_meal) {
                String mealName = etMealName.getText().toString();
                String mealDescription = etMealDescription.getText().toString();
                int mealPrice = Integer.parseInt(etMealPrice.getText().toString());
                String encodePhoto = etMealPhoto.getText().toString();
                String path=null;//= Environment.getExternalStorageDirectory().getPath() + "/Download/" + encodePhoto + ".jpg";
                for(String i :p){
                    if(new File(Environment.getExternalStorageDirectory().getPath() + "/Download/" + encodePhoto +i).exists()){
                        path=Environment.getExternalStorageDirectory().getPath() + "/Download/" + encodePhoto +i;
                    }
                }
                if(!new File(path).exists())
                for(String i :p){
                    if(new File(Environment.getExternalStorageDirectory().getPath() + "/Downloads/" + encodePhoto +i).exists()){
                        path=Environment.getExternalStorageDirectory().getPath() + "/Downloads/" + encodePhoto +i;
                    }
                }
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                byte[] image = byteArrayOutputStream.toByteArray();

                databaseHandler.addMeal(mealName, mealDescription, mealPrice, encodePhoto, image);

                this.selectedId = -1;
            }

            if (view.getId() == R.id.btn_del_meal) {
                // check if selected or not
                if (this.selectedId != -1) {
                    databaseHandler.deleteMeal(this.selectedId);
                    Toast.makeText(mealmanager.this, "Delete done!", Toast.LENGTH_SHORT).show();
                }

                this.selectedId = -1;
            }
            if(view.getId()==R.id.btn_backtomain){
                Intent intent=new Intent(mealmanager.this,MainActivity.class);
                startActivity(intent);
            }
            showAllMeals();
        };

        button.setOnClickListener(listener);
        btnAddMeal.setOnClickListener(listener);
        btnDelMeal.setOnClickListener(listener);

        AdapterView.OnItemClickListener listviewItemClickListener = (adapterView, view, index, id) -> {
            Cursor cursor = (Cursor) adapterView.getAdapter().getItem(index);
            this.selectedId = Integer.parseInt(cursor.getString(0));
            Toast.makeText(mealmanager.this, "id: " + cursor.getString(0) + " is selected", Toast.LENGTH_SHORT).show();
            etMealName.setText(cursor.getString(1));
            etMealDescription.setText(cursor.getString(2));
            etMealPrice.setText(cursor.getString(3));
            etMealPhoto.setText(cursor.getString(4));

            System.out.println(selectedId);
        };

        lvMeals.setOnItemClickListener(listviewItemClickListener);
    }

    private void showAllMeals() {
        Cursor cursor = databaseHandler.getAllMeals();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                mealmanager.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "price"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );
        lvMeals.setAdapter(adapter);
    }
}