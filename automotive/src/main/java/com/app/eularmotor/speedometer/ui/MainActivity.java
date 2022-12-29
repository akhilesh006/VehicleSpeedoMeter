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
import com.app.eularmotor.speedometer.SpeedManager;
import com.app.eularmotor.speedometer.ui.main.MainFragment;

/**
 * A main activity for displaying a screen to the user.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private IVehicleHAL vehicleService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: service connected.....");
            vehicleService = IVehicleHAL.Stub.asInterface(iBinder);
            try {
                vehicleService.startVehicleService(new IVehicleSpeedListener.Stub() {
                    @Override
                    public void deliverCurrentSpeed(int speed) throws RemoteException {
                        Log.d(TAG, "deliverCurrentSpeed: " + speed);
                        SpeedManager.getINSTANCE().setCurrentSpeed(speed);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            vehicleService = null;
        }
    };

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
        //Start the service to broadcast the speed data, this will be removed after HAL implementation
        //bind service
        initServiceConnection();
        Log.d(TAG, "onStart: bind service");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: unbind service");
        //unbind the service.
        unbindService(serviceConnection);
    }

    private void initServiceConnection() {
        Log.d(TAG, "initServiceConnection: " + vehicleService);
        if (vehicleService == null) {
            Intent intent = new Intent();
            intent.setAction("service_vehicle");
            intent.setPackage("com.app.halsystemapp");
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        }
    }

}