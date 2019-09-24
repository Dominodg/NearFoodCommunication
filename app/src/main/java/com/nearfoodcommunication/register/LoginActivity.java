package com.nearfoodcommunication.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nearfoodcommunication.main.DisplayMessageActivity;
import com.nearfoodcommunication.main.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.nearfoodcommunication.register.SaveSharedPreference.setUserName;

public class LoginActivity extends AppCompatActivity {

    //public static final String LOGIN_BASE_URL = "http://ec2-3-15-158-123.us-east-2.compute.amazonaws.com:8080/login/";
    public static final String LOGIN_BASE_URL = "http://ec2-18-217-70-227.us-east-2.compute.amazonaws.com:8080/login/";


    private EditText emailAddress;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);

        emailAddress = (EditText) findViewById(R.id.etEmailAddress);
        password = (EditText) findViewById(R.id.etPasswordSU);
        login = (Button) findViewById(R.id.btnLogin);
        signup = (Button) findViewById(R.id.btnSignupSU);

        info = (TextView) findViewById(R.id.InfoSU);
        info.setText("");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = emailAddress.getText().toString();
                String userPassword = password.getText().toString();

                performLogin(userEmail, userPassword);
            }
        });
    }

    private void performLogin(String userEmail, String userPassword) {

        if (isValidLoginData(userEmail, userPassword)) {
            info.setText("");

            getUserAccount(userEmail, userPassword);

        } else {
            info.setText("Your email or password is incorrect.");

        }
    }

    private void getUserAccount(String userEmailAdress, String userPassword) {

        String url = LOGIN_BASE_URL + userEmailAdress + "/" + userPassword;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

//                        Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_LONG).show();
                        try {
                            Account account = parseData(response);

                            if (account != null && account.getIdUser() != null) {

                                setUserName(LoginActivity.this, account.getEmail());
                                Intent intent = new Intent(LoginActivity.this, DisplayMessageActivity.class);
                                startActivity(intent);
                            } else {
                                info.setText("Your email or password is incorrect.");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    private Account parseData(JSONObject response) throws JSONException {
                        JSONObject propertyInfo = response;

                        Account account;

                        if (propertyInfo.has("idUser")) {
                            Long idUser = propertyInfo.getLong("idUser");
                            String firstName = propertyInfo.getString("firstName");
                            String lastName = propertyInfo.getString("lastName");
                            String emailAdressDB = propertyInfo.getString("email");
                            String passwordDB = propertyInfo.getString("password");
                            String type = propertyInfo.getString("type");
                            account = new Account(idUser, firstName, lastName, emailAdressDB, passwordDB, type);
                        } else {
                            account = null;
                        }

                        return account;
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(LoginActivity.this, "NOT OK", Toast.LENGTH_LONG).show();
                        info.setText("There was an error. Please try again");

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private boolean isValidLoginData(String userEmailAdress, String userPassword) {

        return (userEmailAdress != null && !userEmailAdress.trim().isEmpty()
                && userPassword != null && !userPassword.trim().isEmpty());
    }

    private void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }
}
