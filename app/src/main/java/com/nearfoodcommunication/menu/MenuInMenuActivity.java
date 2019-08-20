package com.nearfoodcommunication.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.adapter.FoodListAdapter;
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.menu.model.FoodType;

import java.util.ArrayList;
import java.util.List;


public class MenuInMenuActivity extends AppCompatActivity {

    ListView foodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_in_menu);

        foodlist = findViewById(R.id.listFood);
        List<Food> foodList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            FoodType foodtype = (FoodType) bundle.get("FoodName");

            foodList = foodtype.getFooditems();


        }

        FoodListAdapter adapter = new FoodListAdapter(this, R.layout.menu_in_menu_layout, foodList);
        foodlist.setAdapter(adapter);
    }
}
