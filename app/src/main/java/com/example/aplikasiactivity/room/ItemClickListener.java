package com.example.aplikasiactivity.room;

import androidx.annotation.Nullable;

public interface ItemClickListener<Mahasiswa> {
    void onClick(Mahasiswa model);
    boolean onLongClick(@Nullable Mahasiswa model);
}
