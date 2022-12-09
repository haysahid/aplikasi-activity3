package com.example.aplikasiactivity.activity;

import static com.example.aplikasiactivity.room.AppApplication.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasiactivity.R;
import com.example.aplikasiactivity.room.AppDatabase;
import com.example.aplikasiactivity.room.Mahasiswa;
import com.example.aplikasiactivity.util.PreferencesHelper;

public class EditUserActivity extends AppCompatActivity {

    private EditText etNama;
    private EditText etNim;
    private EditText etKejuruan;
    private EditText etAlamat;
    private Button saveData;
    Mahasiswa mahasiswa;
//    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

//        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        int indeks = getIntent().getIntExtra("indeks", 0);

        mahasiswa = new Mahasiswa();

        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        etKejuruan = findViewById(R.id.etKejuruan);
        etAlamat = findViewById(R.id.etAlamat);
        saveData = findViewById(R.id.btSave);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mahasiswa")
                .allowMainThreadQueries().build();
        mahasiswa = db.userDao().getAll().get(indeks);

        int id = mahasiswa.getId();
        etNama.setText(mahasiswa.getNama());
        etNim.setText(mahasiswa.getNim());
        etKejuruan.setText(mahasiswa.getKejuruan());
        etAlamat.setText(mahasiswa.getAlamat());

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etAlamat.getText().toString().isEmpty() &&
                        !etKejuruan.getText().toString().isEmpty() &&
                        !etNama.getText().toString().isEmpty() &&
                        !etNim.getText().toString().isEmpty()) {
                    mahasiswa = new Mahasiswa();
                    mahasiswa.setId(id);
                    mahasiswa.setNama(etNama.getText().toString());
                    mahasiswa.setNim(etNim.getText().toString());
                    mahasiswa.setKejuruan(etKejuruan.getText().toString());
                    mahasiswa.setAlamat(etAlamat.getText().toString());

                    String cekId = "Idnya = " + mahasiswa.getId();

                    // Cek update
                    Log.e("Update", cekId);
                    Log.e("Update", mahasiswa.getNama());
                    Log.e("Update", mahasiswa.getNim());
                    Log.e("Update", mahasiswa.getKejuruan());
                    Log.e("Update", mahasiswa.getAlamat());

                    // Update data in database
                    db.userDao().update(mahasiswa);
//                    startActivity(new Intent(EditUserActivity.this, UserActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}