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

/**
 * A main activity for displaying a screen to the user.
 */
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
        //Start the service to broadcast the speed data, this will be removed after HAL implementation
        startService(new Intent(this, VehicleService.class));
        //Start the dispatcher to call the upload service every 5 seconds.
        VehicleDataDispatcher.getInstance().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: unbind service");
        //Stop service
        stopService(new Intent(this, VehicleService.class));
        //Stop the dispatcher.
        VehicleDataDispatcher.getInstance().stop();
        //Close any opened connection
        NetworkProviderHandler.getInstance().closeConnection();
    }
}