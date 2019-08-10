package com.nearfoodcommunication.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisplayMessageActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterMenu();
            }
        });

    }

    private void enterMenu()
    {
        Intent intent = new Intent(DisplayMessageActivity.this, MenuActivity.class);
        startActivity(intent);
    }


}
