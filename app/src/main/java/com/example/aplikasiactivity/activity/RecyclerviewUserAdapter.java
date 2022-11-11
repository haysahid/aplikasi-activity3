package com.example.aplikasiactivity.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiactivity.R;
import com.example.aplikasiactivity.room.Mahasiswa;

import java.util.List;

public class RecyclerviewUserAdapter extends RecyclerView.Adapter<RecyclerviewUserAdapter.MyViewHolder> {
    private Context mContext;
    private List<Mahasiswa> myList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama, tvNim, tvKejuruan, tvAlamat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
        }
    }

    public RecyclerviewUserAdapter(Context mContext, List<Mahasiswa> myList) {
        this.mContext = mContext;
        this.myList = myList;
    }

    @NonNull
    @Override
    public RecyclerviewUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mahasiswa, viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewUserAdapter.MyViewHolder myViewHolder, int i) {
        final Mahasiswa album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvNim.setText(album.getNim());
        myViewHolder.tvKejuruan.setText(album.getKejuruan());
        myViewHolder.tvAlamat.setText(album.getAlamat());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
