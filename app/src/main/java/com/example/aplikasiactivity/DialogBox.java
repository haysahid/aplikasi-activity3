package com.example.aplikasiactivity;

import static com.example.aplikasiactivity.room.AppApplication.db;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.aplikasiactivity.activity.UserActivity;

public class DialogBox extends AppCompatDialogFragment {
    private DialogBoxListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Perhatian!")
                .setMessage("Yakin akan dihapus?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onYesClicked();
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

    public interface DialogBoxListener {
        void onYesClicked();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (DialogBoxListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + "must impelement DialogBoxListener");
//        }
//
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener = (DialogBoxListener) getParentFragmentManager();
    }
}
