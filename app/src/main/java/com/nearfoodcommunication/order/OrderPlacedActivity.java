package com.nearfoodcommunication.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.main.DisplayMessageActivity;
import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.MenuActivity;
import com.nearfoodcommunication.register.NfcRouterActivity;

import java.util.List;

import static com.nearfoodcommunication.register.SaveSharedPreference.getPropertyId;


public class OrderPlacedActivity extends AppCompatActivity {

    Context context;
    Button btn1;
    Button btn2;
    Database db;
    List<OrderLine> foodList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_order_placed);

        db = new Database(context);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int tableNumber = bundle.getInt(NfcRouterActivity.NFC_PARAM_TABLE_NUMBER);

            foodList = db.getCarts();

            Order order = new Order(tableNumber, getPropertyId(OrderPlacedActivity.this), foodList);

            btn1 = findViewById(R.id.btn1);
            btn2 = findViewById(R.id.btn2);

            db.cleanCart();

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(OrderPlacedActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(OrderPlacedActivity.this, DisplayMessageActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }
}
