package com.app.eularmotor.speedometer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;

import com.app.eularmotor.R;
import com.app.eularmotor.network.NetworkProviderHandler;
import com.app.eularmotor.speedometer.dispatcher.VehicleDataDispatcher;
import com.app.eularmotor.services.VehicleService;
import com.app.eularmotor.speedometer.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: bind service");
        startService(new Intent(this, VehicleService.class));
        VehicleDataDispatcher.getInstance().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: unbind service");
        stopService(new Intent(this, VehicleService.class));
        VehicleDataDispatcher.getInstance().stop();
        NetworkProviderHandler.getInstance().closeConnection();
    }
}