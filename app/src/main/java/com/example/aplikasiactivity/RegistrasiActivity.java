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

public class RegistrasiActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegistrasi;
    TextView tvLogin;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegistrasi = findViewById(R.id.btnRegistrasi);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().matches("") ||
                        etPassword.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Username dan Password tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                preferencesHelper.setUser(etUsername.getText().toString(), etPassword.getText().toString());
                preferencesHelper.setNama(etUsername.getText().toString());
                preferencesHelper.setLogin(true);

                Intent registerIntent = new Intent(RegistrasiActivity.this, FirstActivity.class);
                registerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(registerIntent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegistrasiActivity.this, HomeActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}