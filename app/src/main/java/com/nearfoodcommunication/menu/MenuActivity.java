package com.nearfoodcommunication.menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.adapter.FoodTypeListAdapter;
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.menu.model.FoodType;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ListView foodtypesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        foodtypesList = findViewById(R.id.listFoodTypes);

        List<Food> pizzaList = new ArrayList<>();
        Food pizza1 = new Food(R.drawable.pizza, "pizza", "e buna", "12");
        pizzaList.add(pizza1);
        List<Food> drinksList = new ArrayList<>();
        Food drinks1 = new Food(R.drawable.drinks, "drinks", "e si mai buna", "34");
        drinksList.add(drinks1);

        FoodType pizza = new FoodType(R.drawable.pizza, "pizza", "1", pizzaList);
        FoodType drinks = new FoodType(R.drawable.drinks, "drinks", "2", drinksList);
        FoodType drinks2 = new FoodType(R.drawable.drinks, "drinks", "3", drinksList);
        FoodType drinks3 = new FoodType(R.drawable.drinks, "drinks", "4", drinksList);
        FoodType drinks4 = new FoodType(R.drawable.drinks, "drinks", "5", drinksList);
        FoodType drinks5 = new FoodType(R.drawable.drinks, "drinks", "6", drinksList);
        FoodType drinks6 = new FoodType(R.drawable.drinks, "drinks", "7", drinksList);
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(pizza);
        foodTypeList.add(drinks);
        foodTypeList.add(drinks2);
        foodTypeList.add(drinks3);
        foodTypeList.add(drinks4);
        foodTypeList.add(drinks5);
        foodTypeList.add(drinks6);

        FoodTypeListAdapter adapter = new FoodTypeListAdapter(this, R.layout.menu_layout, foodTypeList);
        foodtypesList.setAdapter(adapter);


        foodtypesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MenuActivity.this, MenuInMenuActivity.class);
                FoodType selectedItem = (FoodType) foodtypesList.getItemAtPosition(position);

                intent.putExtra("FoodName", selectedItem);
                startActivity(intent);
            }
        });
    }

}
