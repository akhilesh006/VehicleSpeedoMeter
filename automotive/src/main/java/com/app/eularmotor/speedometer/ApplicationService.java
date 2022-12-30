package com.app.eularmotor.speedometer;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.app.eularmotor.IVehicleHAL;
import com.app.eularmotor.IVehicleSpeedListener;

/**
 * This service is responsible for starting system service and receiving speed data from system service every 500ms.
 */
public class ApplicationService extends Service {

    private static final String TAG = "ApplicationService";
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
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ApplicationService: created...");
        //bind service
        initServiceConnection();
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
        Log.d(TAG, "onDestroy: ...");
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