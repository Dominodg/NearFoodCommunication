package com.example.goiad.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String user = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    ///int i = user.indexOf('@');
       /// user = user.substring(0,(user.lastIndexOf("@")));
        TextView textView = findViewById(R.id.editText);
        textView.setText(user);
    }
}
