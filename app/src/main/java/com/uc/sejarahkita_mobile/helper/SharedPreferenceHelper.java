package com.uc.sejarahkita_mobile.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper {
    private static final String PREFS = "pref";
    private static SharedPreferenceHelper instance;
    private SharedPreferences prefs;
    private String KEY_ID = "id";

    private SharedPreferenceHelper(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceHelper(context);
        }
        return instance;
    }

    public void saveAccessToken(String token) {
        prefs.edit().putString(PREFS, token).apply();
    }

    public void saveRefreshToken(String token) {
        prefs.edit().putString(PREFS, token).apply();
    }

    public String getAccessToken() {
        return prefs.getString(PREFS, "");
    }

    public void saveId(String id) {
        prefs.edit().putString(KEY_ID, id).apply();
    }

    public String getId() {
        return prefs.getString(KEY_ID, "");
    }

    public void clearPref() {
        prefs.edit().clear().apply();
    }
}