package com.example.aplikasiactivity.activity;

import static com.example.aplikasiactivity.room.AppApplication.db;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.aplikasiactivity.DialogBox;
import com.example.aplikasiactivity.R;
import com.example.aplikasiactivity.room.AppDatabase;
import com.example.aplikasiactivity.room.Mahasiswa;
import com.example.aplikasiactivity.util.PreferencesHelper;

import java.util.List;

public class RecyclerviewUserAdapter extends RecyclerView.Adapter<RecyclerviewUserAdapter.MyViewHolder> {
    private Context mContext;
    private List<Mahasiswa> myList;
    private Mahasiswa mahasiswa;
    PreferencesHelper preferencesHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama, tvNim, tvKejuruan, tvAlamat;
        private CardView cvItem;
        final RecyclerviewUserAdapter mAdapter;

        public MyViewHolder(@NonNull View itemView, RecyclerviewUserAdapter adapter) {
            super(itemView);

            preferencesHelper = PreferencesHelper.getInstance(itemView.getContext());

            tvNama = itemView.findViewById(R.id.tvNama);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            cvItem = itemView.findViewById(R.id.cvItem);

            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            int indeks = getLayoutPosition();
//
//            preferencesHelper.setIndeks(indeks);
//
//            Intent editIntent = new Intent(view.getContext(), EditUserActivity.class);
//            view.getContext().startActivity(editIntent);
//        }

//        @Override
//        public boolean onLongClick(View view) {
//
////            Toast.makeText(view.getContext(), "Hold press true", Toast.LENGTH_SHORT).show();
//
//            int indeks = getLayoutPosition();
//
//            db = Room.databaseBuilder(view.getContext(), AppDatabase.class, "mahasiswa")
//                    .allowMainThreadQueries().build();
//            mahasiswa = db.userDao().getAll().get(indeks);
//
//            int id = mahasiswa.getId();
//
//            mahasiswa = new Mahasiswa();
//            mahasiswa.setId(id);
//
//            showDialog(view);
//
////            openDialog();
//
//            return false;
//        }

        public void openDialog() {
            DialogBox dialog = new DialogBox();
            dialog.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "delete dialog");
        }

        public void showDialog(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Yakin akan dihapus?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.userDao().deleteUsers(mahasiswa);
//                            dialogInterface.dismiss();

                            view.getContext().startActivity(new Intent(view.getContext(), UserActivity.class));
//                            listener.onYesClicked();
                        }
                    }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
                        }
                    });
            // Creating dialog box
            AlertDialog alert = builder.create();
            // Setting the title manually
            alert.setTitle("Perhatian!");
            alert.show();
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

        return new MyViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewUserAdapter.MyViewHolder myViewHolder, int i) {
        final Mahasiswa album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvNim.setText(album.getNim());
        myViewHolder.tvKejuruan.setText(album.getKejuruan());
        myViewHolder.tvAlamat.setText(album.getAlamat());

//        myViewHolder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        myViewHolder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int indeks = myViewHolder.getLayoutPosition();

//                preferencesHelper.setIndeks(indeks);

                Intent editIntent = new Intent(view.getContext(), EditUserActivity.class);
                editIntent.putExtra("indeks", indeks);
                view.getContext().startActivity(editIntent);
            }
        });

        myViewHolder.cvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Toast.makeText(view.getContext(), "Hold press true", Toast.LENGTH_SHORT).show();

                int indeks = myViewHolder.getLayoutPosition();

                db = Room.databaseBuilder(view.getContext(), AppDatabase.class, "mahasiswa")
                        .allowMainThreadQueries().build();
                mahasiswa = db.userDao().getAll().get(indeks);

                int id = mahasiswa.getId();

                mahasiswa = new Mahasiswa();
                mahasiswa.setId(id);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setMessage("Yakin akan dihapus?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.userDao().deleteUsers(mahasiswa);
                                myList.remove(indeks);
                                notifyItemRemoved(indeks);
                                dialogInterface.dismiss();

//                                view.getContext().startActivity(new Intent(view.getContext(), UserActivity.class));
//                            listener.onYesClicked();
                            }
                        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
                            }
                        });
                // Creating dialog box
                AlertDialog alert = builder.create();
                // Setting the title manually
                alert.setTitle("Perhatian!");
                alert.show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
