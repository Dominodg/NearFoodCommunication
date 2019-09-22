package com.nearfoodcommunication.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    private static final String PREF_USER_NAME= "username";
    private static final String PREF_PROPERTY_ID = "propertyId";
    private static final String PREF_PROPERTY_ID_NFC = "propertyIdNFC";
    private static final String PREF_TABLE_NUMBER = "propertyTableNumber";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    public static void setPropertyId(Context ctx, Long propertyId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putLong(PREF_PROPERTY_ID, propertyId);
        editor.commit();
    }

    public static Long getPropertyId(Context ctx)
    {
        return getSharedPreferences(ctx).getLong(PREF_PROPERTY_ID,0L );
    }

    public static void setPropertyIdNFC(Context ctx, long propertyIdNFC)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putLong(PREF_PROPERTY_ID_NFC, propertyIdNFC);
        editor.commit();
    }

    public static long getPropertyIdNFC(Context ctx)
    {
        return getSharedPreferences(ctx).getLong(PREF_PROPERTY_ID_NFC,0 );
    }

    public static void setTableNumber(Context ctx, int tableNumber)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_TABLE_NUMBER, tableNumber);
        editor.commit();
    }

    public static int getTableNumber(Context ctx)
    {
        return getSharedPreferences(ctx).getInt(PREF_TABLE_NUMBER,0 );
    }
}
