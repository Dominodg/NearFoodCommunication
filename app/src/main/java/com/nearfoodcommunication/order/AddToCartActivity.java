package com.nearfoodcommunication.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.main.MainActivity;
import com.nearfoodcommunication.main.R;

import com.nearfoodcommunication.order.OrderAdapter;
import com.nearfoodcommunication.register.SignUpActivity;

import java.util.Iterator;
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



        btnBuy = findViewById(R.id.buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(price!=0.0){
                    db.cleanCart();
                    finish();
                    Intent intent = new Intent(AddToCartActivity.this, OrderPlacedActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddToCartActivity.this, "Your cart is empty.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }




}
