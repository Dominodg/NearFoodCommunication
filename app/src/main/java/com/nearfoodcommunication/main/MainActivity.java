//mlc

package com.nearfoodcommunication.main;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nearfoodcommunication.register.LoginActivity;
import com.nearfoodcommunication.register.SaveSharedPreference;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "NFCLINK" + MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "getLink");

        if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
            startActivity(intent);
        }

        turnOnNfcBeam();

        finish();

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

}
