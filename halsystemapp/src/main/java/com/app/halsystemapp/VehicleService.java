package com.app.halsystemapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.app.eularmotor.IVehicleHAL;
import com.app.eularmotor.IVehicleSpeedListener;
import com.app.halsystemapp.data.model.SpeedModel;
import com.app.halsystemapp.data.repository.SpeedRepository;
import com.app.halsystemapp.dispatcher.AppDataDispatcher;
import com.app.halsystemapp.utils.TaskScheduler;
import com.app.halsystemapp.dispatcher.BackendDataDispatcher;
import com.app.halsystemapp.network.NetworkProviderHandler;

/**
 * This service is responsible for sending data to application every 500ms.
 */
public class VehicleService extends Service {

    private static final String TAG = "VehicleService";
    final IVehicleHAL.Stub iVehicleHALBinder = new IVehicleHAL.Stub() {
        @Override
        public void startVehicleService(IVehicleSpeedListener listener) throws RemoteException {
            Log.d(TAG, "startVehicleService: vehicle service start command received...");
            //Start the dispatcher to call the application every 500 milliseconds.
            AppDataDispatcher.getInstance().start(listener);
            //Start the dispatcher to call the upload service every 5 seconds.
            BackendDataDispatcher.getInstance().start();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "VehicleService: created...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: intent :" + intent);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iVehicleHALBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Stop the dispatcher.
        AppDataDispatcher.getInstance().stop();
        //Stop the dispatcher.
        BackendDataDispatcher.getInstance().stop();
        //Close any opened connection
        NetworkProviderHandler.getInstance().closeConnection();
    }
}