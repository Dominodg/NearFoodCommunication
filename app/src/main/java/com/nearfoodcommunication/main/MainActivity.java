package com.nearfoodcommunication.main;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";

    public static String TAG = "NFCLINK" + MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "getLink");

        setContentView(R.layout.activity_main);
    }

    NfcAdapter mNfcAdapter;

    public void turnOnNfcBeam(){

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

    public void login(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String email = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, email);
        startActivity(intent);
    }
}
