package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplikasiactivity.util.PreferencesHelper;

public class RegistrasiActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegistrasi;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegistrasi = findViewById(R.id.btnRegistrasi);

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesHelper.setUser(etUsername.getText().toString(), etPassword.getText().toString());
                preferencesHelper.setNama(etUsername.getText().toString());
                preferencesHelper.setLogin(true);
                Intent registerIntent = new Intent(RegistrasiActivity.this, FirstActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}