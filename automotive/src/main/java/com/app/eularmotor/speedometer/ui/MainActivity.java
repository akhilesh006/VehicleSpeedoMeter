package com.app.eularmotor.speedometer.ui;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.app.eularmotor.IVehicleHAL;
import com.app.eularmotor.IVehicleSpeedListener;
import com.app.eularmotor.R;
import com.app.eularmotor.speedometer.ApplicationService;
import com.app.eularmotor.speedometer.SpeedManager;
import com.app.eularmotor.speedometer.ui.main.MainFragment;

/**
 * A main activity for displaying a screen to the user.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
        //Start the service to keep getting the speed data from HAL service
        startService(new Intent(this, ApplicationService.class));
        Log.d(TAG, "onStart: bind service");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: unbind service");
        stopService(new Intent(this, ApplicationService.class));
    }

}