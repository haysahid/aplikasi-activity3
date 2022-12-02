package com.example.aplikasiactivity.activity;

import static com.example.aplikasiactivity.room.AppApplication.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.aplikasiactivity.R;
import com.example.aplikasiactivity.room.AppDatabase;
import com.example.aplikasiactivity.room.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerviewUserAdapter recyclerAdapter;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recycleView);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        // Just checking data from db
        for (int i = 0; i < listMahasiswas.size(); i++) {
            Log.e("Aplikasi", listMahasiswas.get(i).getNama() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getNim() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getKejuruan() + i);
            Log.e("Aplikasi", listMahasiswas.get(i).getAlamat() + i);
        }
        Log.e("cek list", "" + listMahasiswas.size());
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerAdapter = new RecyclerviewUserAdapter(this, listMahasiswas);
    }

    private void setAdapter() {
        recyclerView.setAdapter(recyclerAdapter);
    }
}