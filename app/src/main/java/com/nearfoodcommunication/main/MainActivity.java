//mlc

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

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.register.SignUpActivity;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "NFCLINK" + MainActivity.class.getSimpleName();

    private EditText emailAddress;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "getLink");

        setContentView(R.layout.activity_main);

        emailAddress = (EditText)findViewById(R.id.etEmailAddress);
        password = (EditText)findViewById(R.id.etPasswordSU);
        info = (TextView)findViewById(R.id.InfoSU);
        login = (Button)findViewById(R.id.btnLogin);
        signup = (Button)findViewById(R.id.btnSignupSU);

        cleanDB();

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
        info.setText(" ");
        if ((userEmailAddress.equals("a")) && (userPassword.equals("a"))) {
            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
            startActivity(intent);
        } else {
            info.setText("Your email or your password is incorrect.");
        }

    }

    private void signUp()
    {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void cleanDB(){
        Database db=new Database(this);
        db.cleanCart();
    }
}
