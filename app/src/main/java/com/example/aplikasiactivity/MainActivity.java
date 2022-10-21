package com.example.aplikasiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText eUsername, ePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsername = findViewById(R.id.editTextUsername);
        ePassword = findViewById(R.id.editTextPassword);

    }

    public void toSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();

        eUsername.setText("Hallo");
    }
}