package com.app.eularmotor.speedometer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.eularmotor.speedometer.SpeedManager;

public class SpeedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int speed = intent.getIntExtra("speed",-1);
            SpeedManager.getINSTANCE().setCurrentSpeed(speed);

        }
    }
