package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private Button btnclear;
    private ListView lvMainMeals;
    private ListViewAdapter adapter;
    private DatabaseHandler databaseHandler;
    private Button egg;
    private Button toast;
    private Button drink;
    private Button burger;

    private  String account;
    private int cnt=0;
    private Bundle bundle;

    private  Button rate;
    private List List;
    private File prjDir;
    private ArrayList<FoodItem> listFood = new ArrayList<>();

    private  final static String dateTime=LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);




        databaseHandler.open();
        rate=findViewById(R.id.btn_rate);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("signedin", false);
        if (!isLogin) {
            Intent intent = new Intent(MainActivity.this, signingActivity.class);
            startActivity(intent);
        }

        boolean isadmin = sharedPreferences.getBoolean("admin", false);
        account=sharedPreferences.getString("account","");

        if(isadmin){
             Intent intent=new Intent(MainActivity.this, beta.class);
            startActivity(intent);
        }
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
                else if(v.getId()==R.id.btn_rate){
                    Intent intent =new Intent(MainActivity.this,meal_rate_history.class);
                    sharedPreferences.edit().putString("hash",dateTime).apply();
                    sharedPreferences.edit().putString("acc", account).apply();
                    startActivity(intent);
                }
            }
        };
rate.setOnClickListener(onClickListener);
        burger.setOnClickListener(onClickListener);
        egg.setOnClickListener(onClickListener);
        toast.setOnClickListener(onClickListener);
        drink.setOnClickListener(onClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showAllMeals();
    }

    private void showAllMeals() {


        Cursor c = databaseHandler.getAllMeals();

            while (c.moveToNext()){
      //          Bitmap bitmap= BitmapFactory.decodeByteArray(c.getBlob(5),0,c.getBlob(5).length);
                listFood.add(new FoodItem(c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getBlob(5),c.getString(0)));}


        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);

        lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, orderpage.class);
               // intent.putExtra("img", new String(listFood.get(position).getImage().toString()));
                intent.putExtra("mealname", listFood.get(position).getFoodName());
                intent.putExtra("account", account);
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
        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, orderpage.class);
                intent.putExtra("id",arr.get(position).getId());
                intent.putExtra("account", account);
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
        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, orderpage.class);
                intent.putExtra("account", account);
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
        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, orderpage.class);
                intent.putExtra("account", account);
                intent.putExtra("account", account);
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
        cursuradapter cursuradapter = new cursuradapter(MainActivity.this, c, 0);
        lvMainMeals = findViewById(R.id.lv_main_meals);

        lvMainMeals.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, orderpage.class);
                intent.putExtra("id",arr.get(position).getId());
                intent.putExtra("account", account);
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

    }
}



