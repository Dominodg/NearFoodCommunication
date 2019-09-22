package com.nearfoodcommunication.register;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.menu.MenuActivity;
import com.nearfoodcommunication.order.OrderPlacedActivity;

import java.io.UnsupportedEncodingException;

public class NfcRouterActivity extends AppCompatActivity {

    public static final String NFC_PARAM_PROPERTYID = "propertyId";
    public static final String NFC_PARAM_TABLE_NUMBER = "tableNumber";

    Database db;
    Context context = this;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(context);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
        }

        readFromIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[]{tagDetected};
    }

    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String nfcTagData = "";
        String[] nfcTagDataPieces;
//        String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
//        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            nfcTagData = new String(payload, 0, payload.length, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        nfcTagDataPieces = nfcTagData.split("/");

        if (nfcTagDataPieces.length == 1) {
            Intent intent = new Intent(NfcRouterActivity.this, MenuActivity.class);

            long propertyId = Long.parseLong(nfcTagDataPieces[0]);
            SaveSharedPreference.setPropertyIdNFC(context,propertyId);

            startActivity(intent);
        } else {
            if (db.getCarts().size() > 0) {
                Intent intent = new Intent(NfcRouterActivity.this, OrderPlacedActivity.class);

                int tableNumber = Integer.parseInt(nfcTagDataPieces[1]);
                SaveSharedPreference.setTableNumber(context,tableNumber);

                startActivity(intent);
            } else {
                Intent intent = new Intent(NfcRouterActivity.this, MenuActivity.class);

                long propertyId = Long.parseLong(nfcTagDataPieces[0]);
                SaveSharedPreference.setPropertyIdNFC(context,propertyId);

                startActivity(intent);
            }
        }
    }
}
