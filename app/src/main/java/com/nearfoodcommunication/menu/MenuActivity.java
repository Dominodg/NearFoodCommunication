package com.nearfoodcommunication.menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.adapter.FoodTypeListAdapter;
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.menu.model.FoodType;
import com.nearfoodcommunication.order.AddToCartActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity{

    ListView foodtypesList;
    ImageView ivRestaurant;
    TextView tvRestaurantName;
    Toolbar toolBar;
    List<FoodType> foodTypesList;
    String url="https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        toolBar = findViewById (R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");

        ivRestaurant =(ImageView) findViewById(R.id.ImageView);
        Picasso.with(this).load(url).into(ivRestaurant);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvRestaurantName.setText("nume");

        foodtypesList = findViewById(R.id.listFoodTypes);

        foodTypesList = new ArrayList<>();

        //String url = "https://jsonplaceholder.typicode.com/photos";
//        String url = "https://jsonplaceholder.typicode.com/todos/1";
        String url = "https://localhost:8080/food-categories/3";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(MenuActivity.this, "OK", Toast.LENGTH_LONG).show();
//                        Toast.makeText(MenuActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        parseData(response, foodTypesList);


                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MenuActivity.this, "NOT OK", Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);



        /*List<Food> pizzaList = new ArrayList<>();
        Food pizza1 = new Food("pizza", 12346L, "e buna", 10.0, "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg");
        Food pizza2 = new Food("pizza", 12312L, "e buna2", 10.0, "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg");
        pizzaList.add(pizza1);
        pizzaList.add(pizza2);
        List<Food> drinksList1 = new ArrayList<>();
        Food drinks11 = new Food("drinks", 131L, "e si mai buna", 5.0,"https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg");
        drinksList1.add(drinks11);
        List<Food> drinksList2 = new ArrayList<>();
        Food drinks22 = new Food("drinks", 1331L, "e si mai buna", 5.0,"https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg");
        drinksList2.add(drinks22);

        FoodType pizza = new FoodType("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg", "pizza", 2134L, pizzaList);
        FoodType drinks1 = new FoodType("https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg", "drinks1", 34L, drinksList1);
        FoodType drinks2 = new FoodType("https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg", "drinks2", 344L, drinksList2);
        foodTypeList.add(pizza);
        foodTypeList.add(drinks1);
        foodTypeList.add(drinks2);*/


        FoodTypeListAdapter adapter = new FoodTypeListAdapter(this, R.layout.menu_layout, foodTypesList);
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
            Intent intent = new Intent(MenuActivity.this, AddToCartActivity.class);
            startActivity(intent);
        }
        return true;
    }


    public void parseData(JSONObject response, List<FoodType> foodTypesList) {

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            if (jsonObject.getString("status").equals("true")) {
                JSONArray foodTypesArray = jsonObject.getJSONArray("foodCategories");
                for (int i = 0; i < foodTypesArray.length(); i++) {

                    JSONObject foodTypesObj = foodTypesArray.getJSONObject(i);
                    long foodTypesId = foodTypesObj.getLong("id");
                    String foodTypesName = foodTypesObj.getString("name");
                    String foodTypesPictureUrl = foodTypesObj.getString("pictureUrl");
                    Integer foodTypesIdProperty = foodTypesObj.getInt("idProperty");
                    JSONArray foodItemsArray = foodTypesObj.getJSONArray("foodItemInfos");
                    List<Food> foodItemsList = new ArrayList<>();

                    for(int j = 0; j < foodItemsArray.length(); j++){
                        JSONObject foodItemsObj = foodItemsArray.getJSONObject(j);
                        long foodItemsId = foodItemsObj.getLong("id");
                        String foodItemsName = foodItemsObj.getString("name");
                        Double foodItemsPrice = foodItemsObj.getDouble("price");
                        String foodItemsDescription = foodItemsObj.getString("description");
                        Double foodItemsWeight = foodItemsObj.getDouble("weight");
                        String foodItemsPictureUrl = foodItemsObj.getString("pictureUrl");

                        Food foodItem = new Food(foodItemsId,foodItemsName,foodItemsPrice,foodItemsDescription,foodItemsWeight,foodItemsPictureUrl);
                        foodItemsList.add(foodItem);
                    }

                    FoodType foodType = new FoodType(foodTypesId,foodTypesName,foodTypesPictureUrl,foodTypesIdProperty,foodItemsList);
                    foodTypesList.add(foodType);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
