//mlc

package com.nearfoodcommunication.main;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.register.LoginActivity;
import com.nearfoodcommunication.register.SaveSharedPreference;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "NFCLINK" + MainActivity.class.getSimpleName();
    Database db;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "getLink");

        db = new Database(this);
        db.cleanCart();

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
