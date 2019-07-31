package com.nearfoodcommunication.main;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";

    public static String TAG = "NFCLINK" + MainActivity.class.getSimpleName();

    private EditText emailAddress;
    private EditText password;
    private TextView info;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "getLink");

        setContentView(R.layout.activity_main);

        emailAddress = (EditText)findViewById(R.id.etEmailAddress);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.Info);
        login = (Button)findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidate(emailAddress.getText().toString(), password.getText().toString());
            }
        });
    }

    NfcAdapter mNfcAdapter;

    public void turnOnNfcBeam () {

        if(mNfcAdapter == null)
        {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        }
        if(mNfcAdapter == null || !mNfcAdapter.isEnabled())
        {
            mNfcAdapter = null;
            Toast.makeText(this, "NFC is not available. Try the QR code instead.", Toast.LENGTH_LONG);
        }

    }

    private void loginValidate(String userEmailAddress, String userPassword)
    {
        if ((userEmailAddress.equals("mlc@gmail.com")) && (userPassword.equals("12345"))) {
            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
            startActivity(intent);
        } else {
            info.setText("Your email or your password is incorrect");
        }

    }
}
