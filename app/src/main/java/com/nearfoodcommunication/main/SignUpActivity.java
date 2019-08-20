package com.nearfoodcommunication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText secondName;
    private EditText email;
    private EditText phoneNumber;
    private EditText password;
    private EditText confirmPassword;
    private TextView info;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = (EditText)findViewById(R.id.etFirstName);
        secondName = (EditText)findViewById(R.id.etSecondName);
        email = (EditText)findViewById(R.id.etEmail);
        phoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
        password = (EditText)findViewById(R.id.etPasswordSU);
        confirmPassword = (EditText)findViewById(R.id.etConfirmPasswordSU);
        info = (TextView)findViewById(R.id.InfoSU);
        signup = (Button)findViewById(R.id.btnSignupSU);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verfPassword(password.getText().toString(), confirmPassword.getText().toString());

            }
        });


        }

    private void verfPassword(String password1, String password2)
    {
        if(email.getText().length() == 0|| firstName.getText().length() == 0|| secondName.getText().length() == 0 || phoneNumber.getText().length() == 0 || password.getText().length() == 0 || confirmPassword.getText().length() == 0)
        {
            info.setText("Please fill all text boxes");
        }
        else
        if(!password1.equals(password2)) {
            info.setText("The passwords aren't the same.");
        }
        else {
            info.setText("You sign up succesfully.");
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            startActivity(intent);
        }

    }
}
