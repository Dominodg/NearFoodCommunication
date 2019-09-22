package com.nearfoodcommunication.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.adapter.FoodListAdapter;
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.menu.model.FoodType;
import com.nearfoodcommunication.order.AddToCartActivity;
import com.nearfoodcommunication.register.AccountManagementActivity;


import java.util.ArrayList;
import java.util.List;



public class MenuInMenuActivity extends AppCompatActivity {

    ListView foodlist;
    ImageView ivRestaurant;
    TextView tvFoodType;
    TextView tvRestaurantName;
    Button addtocart;
    Toolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_in_menu);

        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");


        ivRestaurant =(ImageView)findViewById(R.id.ImageView);
        tvFoodType = findViewById(R.id.tvFoodType);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        foodlist = findViewById(R.id.listFood);
        addtocart=findViewById(R.id.addtocart);

        ivRestaurant.setImageResource(R.drawable.restaurant);
        tvRestaurantName.setText("nume");

        List<Food> foodList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            FoodType foodtype = (FoodType) bundle.get("FoodName");

            foodList = foodtype.getFoodItems();

            tvFoodType.setText(foodtype.getFoodName());
        }

        FoodListAdapter adapter = new FoodListAdapter(this, R.layout.menu_in_menu_layout, foodList);
        foodlist.setAdapter(adapter);



    }



    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id==R.id.cart)
        {
            Intent intent = new Intent(MenuInMenuActivity.this, AddToCartActivity.class);
            startActivity(intent);
        }
        else if(res_id==R.id.account){
            Intent intent = new Intent(MenuInMenuActivity.this, AccountManagementActivity.class);
            startActivity(intent);
        }
        return true;
    }


}
