package com.nearfoodcommunication.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.MenuActivity;
import com.nearfoodcommunication.register.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.nearfoodcommunication.register.SaveSharedPreference.getTableNumber;


public class OrderPlacedActivity extends AppCompatActivity {

    //private static final String ORDER_BASE_URL = "http://ec2-3-15-158-123.us-east-2.compute.amazonaws.com:8080/order";
    private static final String ORDER_BASE_URL = "http://ec2-18-217-70-227.us-east-2.compute.amazonaws.com:8080/order";

    Context context;
    Button btn1;
    Database db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_loader_sending_order);

        db = new Database(context);


        int tableNumber = getTableNumber(context);


        sendOrder(tableNumber);
    }

    private void sendOrder(int tableNumber){

        try {
            List<OrderLine> foodList = db.getCarts();
            long propertyId = SaveSharedPreference.getPropertyIdNFC(OrderPlacedActivity.this);
            Order order = new Order(tableNumber, propertyId, foodList);
            String orderString = new Gson().toJson(order);

            JSONObject orderJSONObject = new JSONObject(orderString);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ORDER_BASE_URL, orderJSONObject,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            orderPlacedSuccessfully();
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(OrderPlacedActivity.this, "There was an error placing your order.Please try again.", Toast.LENGTH_LONG).show();

                            goToCart();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(OrderPlacedActivity.this, "There was an error placing your order.Please try again.", Toast.LENGTH_LONG).show();

            goToCart();
        }
    }

    private void orderPlacedSuccessfully() {
        Toast.makeText(OrderPlacedActivity.this, "ORDER SAVED", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_order_placed);

        db.cleanCart();

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderPlacedActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToCart() {
        Intent intent = new Intent(OrderPlacedActivity.this, AddToCartActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }
}
