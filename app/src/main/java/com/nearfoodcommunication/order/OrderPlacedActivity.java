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


public class OrderPlacedActivity extends AppCompatActivity {

    Context context;
    Button btn1;
    Button btn2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_order_placed);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        Database db = new Database(context);
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

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
