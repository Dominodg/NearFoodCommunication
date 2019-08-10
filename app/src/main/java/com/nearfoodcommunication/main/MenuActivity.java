package com.nearfoodcommunication.main;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    ListView foodtypesList;
    Integer[] imgid = {R.drawable.pizza, R.drawable.drinks,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza,R.drawable.pizza};
    String[] foodname = {"pizza", "drinks","pizza","pizza","pizza","pizza","pizza","pizza","pizza","pizza"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        foodtypesList = (ListView) findViewById(R.id.listFoodTypes);
        CustomFoodTypesList customFoodTypesList = new CustomFoodTypesList(this, imgid,foodname);
        foodtypesList.setAdapter(customFoodTypesList);

        foodtypesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    
}
