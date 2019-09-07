package com.nearfoodcommunication.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nearfoodcommunication.main.DisplayMessageActivity;
import com.nearfoodcommunication.main.R;

import static com.nearfoodcommunication.register.SaveSharedPreference.setUserName;

public class LoginActivity extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        emailAddress = (EditText)findViewById(R.id.etEmailAddress);
        password = (EditText)findViewById(R.id.etPasswordSU);
        info = (TextView)findViewById(R.id.InfoSU);
        login = (Button)findViewById(R.id.btnLogin);
        signup = (Button)findViewById(R.id.btnSignupSU);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidate(emailAddress.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void loginValidate(String userEmailAddress, String userPassword)
    {
        info.setText(" ");
        if ((userEmailAddress.equals("a")) && (userPassword.equals("a"))) {
            setUserName(LoginActivity.this, "a");
            Intent intent = new Intent(LoginActivity.this, DisplayMessageActivity.class);
            startActivity(intent);
        } else {
            info.setText("Your email or your password is incorrect.");
        }

    }

    private void signUp()
    {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
