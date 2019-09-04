package com.nearfoodcommunication.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nearfoodcommunication.main.DisplayMessageActivity;
import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.MenuActivity;
import com.nearfoodcommunication.menu.MenuInMenuActivity;


public class OrderPlacedActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

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
