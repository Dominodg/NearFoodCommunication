package com.nearfoodcommunication.menu;


import android.content.Context;
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
import com.nearfoodcommunication.register.AccountManagementActivity;
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
    String urlPicture;

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);





   //    String url = "https://jsonplaceholder.typicode.com/photos";
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        //String url = "https://localhost:8080/food-categories/3";




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(MenuActivity.this, "OK", Toast.LENGTH_LONG).show();
//                        Toast.makeText(MenuActivity.this, response.toString(), Toast.LENGTH_LONG).show();
//                        parseData(response, foodTypesList);

                        setContentView(R.layout.activity_menu);

                        toolBar = findViewById (R.id.toolbar);
                        setSupportActionBar(toolBar);
                        getSupportActionBar().setTitle("");

                        ivRestaurant =(ImageView) findViewById(R.id.ImageView);
                        tvRestaurantName = findViewById(R.id.tvRestaurantName);
                        Picasso.with(context).load(urlPicture).into(ivRestaurant);
                        tvRestaurantName.setText("nume");

                        foodtypesList = findViewById(R.id.listFoodTypes);
                        foodTypesList = new ArrayList<>();


                        List<Food> pizzaList = new ArrayList<>();
                        Food pizza1 = new Food(1L,"pizza", 10.0, "e buna", 100.0, "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg");
                        Food pizza2 = new Food(2L,"pizza2", 10.0, "e buna2", 1640.0, "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg");
                        pizzaList.add(pizza1);
                        pizzaList.add(pizza2);
                        List<Food> drinksList1 = new ArrayList<>();
                        Food drinks11 = new Food(3L,"drinks", 5.0, "e si mai buna", 5.0,"https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg");
                        drinksList1.add(drinks11);
                        FoodType pizza = new FoodType(4L,"pizza","https://img.buzzfeed.com/thumbnailer-prod-us-east-1/dc23cd051d2249a5903d25faf8eeee4c/BFV36537_CC2017_2IngredintDough4Ways-FB.jpg", 2134, pizzaList);
                        FoodType drinks = new FoodType(5L,"drinks","https://www.medicalnewstoday.com/content//images/articles/320/320669/whiskey-glass.jpg", 346, drinksList1);
                        foodTypesList.add(pizza);
                        foodTypesList.add(drinks);


                        FoodTypeListAdapter adapter = new FoodTypeListAdapter(context, R.layout.menu_layout, foodTypesList);
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

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MenuActivity.this, "NOT OK", Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


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
        else if(res_id==R.id.account){
            Intent intent = new Intent(MenuActivity.this, AccountManagementActivity.class);
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

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

}
