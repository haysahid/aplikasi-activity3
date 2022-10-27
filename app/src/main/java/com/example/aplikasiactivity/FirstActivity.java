package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplikasiactivity.util.PreferencesHelper;

public class FirstActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());
        TextView txtFirst = findViewById(R.id.textFirstActivity);
        btnLogout = findViewById(R.id.btnLogout);

//        Intent intent = getIntent();
//        String nama = intent.getStringExtra("nama");

        txtFirst.setText(preferencesHelper.getnama());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(FirstActivity.this, HomeActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                preferencesHelper.setLogin(false);
                preferencesHelper.setNama("");
                startActivity(logoutIntent);
            }
        });
    }
}