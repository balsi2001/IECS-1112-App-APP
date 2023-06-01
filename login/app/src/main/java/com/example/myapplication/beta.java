package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class beta extends AppCompatActivity {
private DatabaseHandler databaseHandler;
  private Button btnmeal;
  private Button egg;
  private Button toast;
  private Button drink;
  private Button burger;
  private  final static String dateTime= LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
  private ListView lvMainMeals;
  private int cnt=0;
  private Bundle bundle;


  private java.util.List List;
  private File prjDir;
  private ArrayList<FoodItem> listFood = new ArrayList<>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_beta);
    databaseHandler = new DatabaseHandler(this);
lvMainMeals=findViewById(R.id.lv_main_meals);

    prjDir = this.getFilesDir();
    egg = findViewById(R.id.btn_eggcookie);
    toast = findViewById(R.id.btn_toast);
    drink = findViewById(R.id.btn_drink);
    burger = findViewById(R.id.btn_burger);

    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (v.getId() == R.id.btn_eggcookie) {
          showeggcookie();
        } else if (v.getId() == R.id.btn_drink) {
          showdrink();
        } else if (v.getId() == R.id.btn_toast) {
          showtoast();
        } else if (v.getId() == R.id.btn_burger) {
          showburger();
        }
      }
    };

    burger.setOnClickListener(onClickListener);
    egg.setOnClickListener(onClickListener);
    toast.setOnClickListener(onClickListener);
    drink.setOnClickListener(onClickListener);


    databaseHandler.open();
    btnmeal=findViewById(R.id.btn_meal);
    View.OnClickListener l = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(beta.this, mealmanager.class);
        startActivity(intent);
      }
    };
    btnmeal.setOnClickListener(l);
    showAllMeals();
  }
  private void showAllMeals() {


    Cursor c = databaseHandler.getAllMeals();

    while (c.moveToNext()){
      //          Bitmap bitmap= BitmapFactory.decodeByteArray(c.getBlob(5),0,c.getBlob(5).length);
      listFood.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));}


    cursuradapter cursuradapter = new cursuradapter(beta.this, c, 0);
    lvMainMeals = findViewById(R.id.lv_main_meals);

    lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(beta.this, orderpage.class);
        // intent.putExtra("img", new String(listFood.get(position).getImage().toString()));
        intent.putExtra("mealname", listFood.get(position).getFoodName());
        int tmp=Integer.parseInt(listFood.get(position).getFoodPrice());
        intent.putExtra("mealprice", tmp);
        intent.putExtra("id",listFood.get(position).getId());
        intent.putExtra("hash", dateTime);
        File outputFile = new File(prjDir, "tmp.jpg");
        cnt++;
        try {
          FileOutputStream fos = new FileOutputStream(prjDir+"/tmp.jpg");
          fos.write(listFood.get(position).getImage());
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        startActivity(intent);
      }
    });
    lvMainMeals.setAdapter(cursuradapter);
  }

  private void showeggcookie() {


    Cursor c = databaseHandler.gerOneMeal("eggcookie%");
    ArrayList<FoodItem> arr = new ArrayList<>();
    while(c.moveToNext()){
      arr.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));
    }
    cursuradapter cursuradapter = new cursuradapter(beta.this, c, 0);
    lvMainMeals = findViewById(R.id.lv_main_meals);
    lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(beta.this, orderpage.class);
        intent.putExtra("id",arr.get(position).getId());
        int tmp=Integer.parseInt(arr.get(position).getFoodPrice());
        intent.putExtra("mealname", arr.get(position).getFoodName());
        intent.putExtra("mealprice", tmp);
        intent.putExtra("hash", dateTime);
        File outputFile = new File(prjDir, "tmp.jpg");
        cnt++;
        try {
          FileOutputStream fos = new FileOutputStream(prjDir+"/tmp.jpg");
          fos.write(arr.get(position).getImage());
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        //  intent.putExtra("img", new String(listFood.get(position).getImage().toString()));
        startActivity(intent);
      }
    });
    lvMainMeals.setAdapter(cursuradapter);

  }

  private void showdrink() {


    Cursor c = databaseHandler.gerOneMeal("drink%");
    ArrayList<FoodItem> arr = new ArrayList<>();
    while(c.moveToNext()){
      arr.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));
    }
    cursuradapter cursuradapter = new cursuradapter(beta.this, c, 0);
    lvMainMeals = findViewById(R.id.lv_main_meals);
    lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(beta.this, orderpage.class);
        intent.putExtra("id",arr.get(position).getId());
        intent.putExtra("mealname", arr.get(position).getFoodName());
        intent.putExtra("mealprice", Integer.parseInt(arr.get(position).getFoodPrice()));
        intent.putExtra("hash", dateTime);
        File outputFile = new File(prjDir, "tmp.jpg");
        cnt++;
        try {
          FileOutputStream fos = new FileOutputStream(prjDir+"/tmp.jpg");
          fos.write(arr.get(position).getImage());
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        // intent.putExtra("img", new String(listFood.get(position).getImage().toString()));
        startActivity(intent);
      }
    });
    lvMainMeals.setAdapter(cursuradapter);

  }

  private void showburger() {


    Cursor c = databaseHandler.gerOneMeal("burger%");
    ArrayList<FoodItem> arr = new ArrayList<>();
    while(c.moveToNext()){
      arr.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));
    }
    cursuradapter cursuradapter = new cursuradapter(beta.this, c, 0);
    lvMainMeals = findViewById(R.id.lv_main_meals);
    lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(beta.this, orderpage.class);
        intent.putExtra("id",arr.get(position).getId());
        intent.putExtra("mealname", arr.get(position).getFoodName());
        intent.putExtra("hash", dateTime);
        intent.putExtra("mealprice", Integer.parseInt(arr.get(position).getFoodPrice()));
        File outputFile = new File(prjDir, "tmp.jpg");
        cnt++;
        try {
          FileOutputStream fos = new FileOutputStream(prjDir+"/tmp.jpg");
          fos.write(arr.get(position).getImage());
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        // intent.putExtra("img", new String(listFood.get(position).getImage().toString()));
        startActivity(intent);
      }
    });
    lvMainMeals.setAdapter(cursuradapter);

  }

  private void showtoast() {


    Cursor c = databaseHandler.gerOneMeal("toast%");
    ArrayList<FoodItem> arr = new ArrayList<>();
    while(c.moveToNext()){
      arr.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));
    }
    cursuradapter cursuradapter = new cursuradapter(beta.this, c, 0);
    lvMainMeals = findViewById(R.id.lv_main_meals);

    lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(beta.this, orderpage.class);
        intent.putExtra("id",arr.get(position).getId());
        cnt++;
        intent.putExtra("hash", dateTime);
        int tmp=Integer.parseInt(arr.get(position).getFoodPrice());
        intent.putExtra("mealname", arr.get(position).getFoodName());
        intent.putExtra("mealprice",Integer.parseInt(arr.get(position).getFoodPrice()));
        File outputFile = new File(prjDir, "tmp.jpg");

        try {
          FileOutputStream fos = new FileOutputStream(prjDir+"/tmp.jpg");
          fos.write(arr.get(position).getImage());
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        startActivity(intent);
      }
    });
    lvMainMeals.setAdapter(cursuradapter);
}}