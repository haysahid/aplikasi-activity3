package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.aplikasiactivity.activity.AddUserActivity;
import com.example.aplikasiactivity.util.PreferencesHelper;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String TAG = "SplashScreen";
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        int splashInterval = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!preferencesHelper.isLogin()) {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity(homeIntent);
                } else {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, FirstActivity.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity(homeIntent);
                }

//                Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
//                Intent firstIntent = new Intent(SplashScreenActivity.this, FirstActivity.class);
//
//                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//                firstIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//
//                startActivity(preferencesHelper.isLogin() ? homeIntent : firstIntent);
            }
        }, splashInterval);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}