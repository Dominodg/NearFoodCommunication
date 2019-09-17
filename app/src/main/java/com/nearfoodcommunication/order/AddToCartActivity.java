package com.nearfoodcommunication.order;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.main.R;

import java.util.List;

public class AddToCartActivity extends AppCompatActivity  {

    ListView foodListView;
    Button btnBuy;
    TextView finalPrice;
    Database db;
    Double price;
    int i;
    Handler handler;
    List<Order> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);

        setContentView(R.layout.activity_add_to_cart);
        foodListView = findViewById(R.id.foodlist);

        foodList = db.getCarts();

        OrderAdapter adapter = new OrderAdapter(this, R.layout.add_to_cart_layout, foodList);
        foodListView.setAdapter(adapter);

        if(foodList.size() > 0)
        {
            Toast.makeText(AddToCartActivity.this, "Scan the tag on the table to send the order", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(AddToCartActivity.this, "Not hungry?", Toast.LENGTH_LONG).show();

        }

        finalPrice = findViewById(R.id.finalPrice);
        handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                price = 0.0;
                for (i = 0; i < foodList.size(); i++) {
                    Double a = foodList.get(i).getFoodPrice();
                    Integer b = foodList.get(i).getFoodQuantity();
                    price = price + a * b;
                }
                finalPrice.setText("Preț: " + price.toString());
                handler.postDelayed(this,100); // set time here to refresh textView
            }
        });

    }

}
