package com.example.aplikasiactivity.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Button;

import androidx.annotation.Nullable;

public class TimeServices extends Service {

    public static final String BROADCAST_ACTION = "com.example.aplikasiactivity";
    private long timeInMiliseconds = 0L;
    private Intent intent;
    private Handler handler = new Handler();
    private long initial_time;

    private Runnable sendUpdatesToUi = new Runnable() {
        @Override
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 1000);
        }
    };

    private void DisplayLoggingInfo() {
        timeInMiliseconds = SystemClock.uptimeMillis() - initial_time;

        int timer = (int) (timeInMiliseconds / 1800);
        int mins = timer / 60;
        int secs = timer % 60;

        intent.putExtra("mins", mins);
        intent.putExtra("secs", secs);

        sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initial_time = SystemClock.uptimeMillis();
        intent = new Intent(BROADCAST_ACTION);
        handler.removeCallbacks(sendUpdatesToUi);
        handler.postDelayed(sendUpdatesToUi, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(sendUpdatesToUi);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
