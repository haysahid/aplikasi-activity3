package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aplikasiactivity.util.PreferencesHelper;

public class FirstActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        TextView txtFirst = findViewById(R.id.textFirstActivity);

//        Intent intent = getIntent();
//        String nama = intent.getStringExtra("nama");

        txtFirst.setText(preferencesHelper.getnama());
    }
}