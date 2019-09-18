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
import com.nearfoodcommunication.menu.model.Restaurant;
import com.nearfoodcommunication.order.AddToCartActivity;
import com.nearfoodcommunication.register.AccountManagementActivity;
import com.nearfoodcommunication.register.NfcRouterActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.nearfoodcommunication.register.SaveSharedPreference.setPropertyId;

public class MenuActivity extends AppCompatActivity {

    private static final String MENU_BASE_URL = "http://ec2-3-15-158-123.us-east-2.compute.amazonaws.com:8080/food-categories/";

    Context context = this;

    ListView foodTypesListView;

    ImageView ivRestaurant;
    TextView tvRestaurantName;
    Toolbar toolBar;
    String urlPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            int propertyId = bundle.getInt(NfcRouterActivity.NFC_PARAM_PROPERTYID);

            String url = MENU_BASE_URL + propertyId;


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(MenuActivity.this, "OK", Toast.LENGTH_LONG).show();
                            try {
                                Restaurant restaurant = parseData(response);

                                setContentView(R.layout.activity_menu);

                                toolBar = findViewById(R.id.toolbar);
                                setSupportActionBar(toolBar);
                                getSupportActionBar().setTitle("");

                                tvRestaurantName = findViewById(R.id.tvRestaurantName);
                                tvRestaurantName.setText(restaurant.getPropertyName());

                                ivRestaurant = (ImageView) findViewById(R.id.ImageView);
                                Picasso.with(context).load(urlPicture).into(ivRestaurant);

                                setPropertyId(context, restaurant.getPropertyId());

                                FoodTypeListAdapter adapter = new FoodTypeListAdapter(context, R.layout.menu_layout, restaurant.getFoodCategories());
                                foodTypesListView = findViewById(R.id.listFoodTypes);
                                foodTypesListView.setAdapter(adapter);

                                foodTypesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        Intent intent = new Intent(MenuActivity.this, MenuInMenuActivity.class);
                                        FoodType selectedItem = (FoodType) foodTypesListView.getItemAtPosition(position);

                                        intent.putExtra("FoodName", selectedItem);
                                        startActivity(intent);
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        private Restaurant parseData(JSONObject response) throws JSONException {

                            JSONObject propertyInfo = response.getJSONObject("property");

                            String propertyName = propertyInfo.getString("propertyName");
                            Long propertyId = propertyInfo.getLong("propertyId");
                            String propertyAdress = propertyInfo.getString("propertyAdress");
                            Integer noTables = propertyInfo.getInt("noTables");

                            Restaurant restaurant = new Restaurant();
                            restaurant.setPropertyId(propertyId);
                            restaurant.setPropertyName(propertyName);
                            restaurant.setPropertyAdress(propertyAdress);
                            restaurant.setNoTables(noTables);

                            List<FoodType> foodCategories = new ArrayList<>();
                            JSONArray foodTypesArray = response.getJSONArray("foodCategories");

                            for (int i = 0; i < foodTypesArray.length(); i++) {

                                JSONObject foodTypesObj = foodTypesArray.getJSONObject(i);

                                long foodTypesId = foodTypesObj.getLong("id");
                                String foodTypesName = foodTypesObj.getString("name");
                                String foodTypesPictureUrl = foodTypesObj.getString("pictureUrl");
                                Integer foodTypesIdProperty = foodTypesObj.getInt("idProperty");
                                JSONArray foodItemsArray = foodTypesObj.getJSONArray("foodItemInfos");
                                List<Food> foodItemsList = new ArrayList<>();

                                for (int j = 0; j < foodItemsArray.length(); j++) {
                                    JSONObject foodItemsObj = foodItemsArray.getJSONObject(j);
                                    long foodItemsId = foodItemsObj.getLong("id");
                                    String foodItemsName = foodItemsObj.getString("name");
                                    Double foodItemsPrice = foodItemsObj.getDouble("price");
                                    String foodItemsDescription = foodItemsObj.getString("description");
                                    Double foodItemsWeight = foodItemsObj.getDouble("weight");
                                    String foodItemsPictureUrl = foodItemsObj.getString("pictureUrl");

                                    Food foodItem = new Food(foodItemsId, foodItemsName, foodItemsPrice, foodItemsDescription, foodItemsWeight, foodItemsPictureUrl);
                                    foodItemsList.add(foodItem);
                                }

                                FoodType foodType = new FoodType(foodTypesId, foodTypesName, foodTypesPictureUrl, foodTypesIdProperty, foodItemsList);
                                foodCategories.add(foodType);
                            }

                            restaurant.setFoodCategories(foodCategories);

                            return restaurant;
                        }

                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    Toast.makeText(MenuActivity.this, "There was an error loading the menu.Please try again.", Toast.LENGTH_LONG).show();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.cart) {
            Intent intent = new Intent(MenuActivity.this, AddToCartActivity.class);
            startActivity(intent);
        } else if (res_id == R.id.account) {
            Intent intent = new Intent(MenuActivity.this, AccountManagementActivity.class);
            startActivity(intent);
        }
        return true;
    }


    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

}
