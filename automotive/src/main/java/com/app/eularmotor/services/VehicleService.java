package com.app.eularmotor.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.app.eularmotor.speedometer.receiver.SpeedReceiver;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This service is responsible for sending data to application every 500ms.
 */
public class VehicleService extends Service {

    private static final String TAG = "VehicleService";
    private Timer timer;

    public VehicleService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "VehicleService: created...");
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(VehicleService.this, SpeedReceiver.class);
                intent.setAction("com.app.eularmotor.receive_speed");
                int speed = (int) (Math.random() * 100);
                intent.putExtra("speed", speed);
                sendBroadcast(intent);
                Log.d(TAG, "run: Broadcast sent, speed : "+speed);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: intent :" + intent);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}