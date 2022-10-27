package com.example.aplikasiactivity.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static PreferencesHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    private PreferencesHelper(Context context) {
        sharedPreferences = context
                .getApplicationContext()
                .getSharedPreferences("com.example.aplikasiactivity", Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper(context);
        }
        return INSTANCE;
    }

    public SharedPreferences preferences() {
        return sharedPreferences;
    }

    // isLogin
    public void setLogin(Boolean isLogin) {
        sharedPreferences.edit().putBoolean("isLogin", isLogin).apply();
    }

    public Boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    // nama
    public void setNama(String nama) {
        sharedPreferences.edit().putString("nama", nama).apply();
    }

    public String getnama() {
        return sharedPreferences.getString("nama", "John Doe");
    }

    public void logout() {
        sharedPreferences.edit().clear().apply();
    }
}
