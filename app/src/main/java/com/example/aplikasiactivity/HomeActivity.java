package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiactivity.util.PreferencesHelper;

public class HomeActivity extends AppCompatActivity {

    private Button btnActivity, btnFragment;
    private EditText etNama;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        btnActivity = findViewById(R.id.btnActivity);
        btnFragment = findViewById(R.id.btnFragment);
        etNama = findViewById(R.id.etNama);

        btnActivity.setOnClickListener(view -> {
            preferencesHelper.setLogin(true);
            if (etNama.getText().toString().matches("")) {
                Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            preferencesHelper.setNama(etNama.getText().toString());

            Intent homeIntent = new Intent(HomeActivity.this, FirstActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//            homeIntent.putExtra("nama", etNama.getText().toString());
            startActivity(homeIntent);
        });

        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent = new Intent(HomeActivity.this, SecondActivity.class);
                startActivity(secondIntent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent homeIntent = new Intent(HomeActivity.this, FirstActivity.class);
    }
}