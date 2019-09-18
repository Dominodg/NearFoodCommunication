package com.nearfoodcommunication.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nearfoodcommunication.main.R;

import static com.nearfoodcommunication.register.SaveSharedPreference.clearUserName;

public class AccountManagementActivity extends AppCompatActivity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (SaveSharedPreference.getUserName(AccountManagementActivity.this).length() == 0) {
            Intent intent = new Intent(AccountManagementActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_account_management);
            logoutButton = findViewById(R.id.logoutButton);

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearUserName(AccountManagementActivity.this);


                    Intent intent = new Intent(AccountManagementActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

        }

    }

}
