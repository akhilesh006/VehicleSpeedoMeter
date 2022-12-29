package com.app.halsystemapp.dispatcher;

import android.util.Log;

import com.app.halsystemapp.EularMotorApplication;
import com.app.halsystemapp.data.model.SpeedModel;
import com.app.halsystemapp.data.repository.SpeedRepository;
import com.app.halsystemapp.network.NetworkCallback;
import com.app.halsystemapp.network.NetworkProviderHandler;
import com.app.halsystemapp.utils.TaskScheduler;

import java.util.List;

/**
 * A singleton class which is responsible for dispatching data to backend.
 */
public class BackendDataDispatcher {

    private static final String TAG = "VehicleDataDispatcher";
    private static final BackendDataDispatcher INSTANCE = new BackendDataDispatcher();
    private static final long SYNC_DURATION = 5000;
    private TaskScheduler scheduler;

    private BackendDataDispatcher() {
    }

    public static BackendDataDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Start the scheduler for every 5 seconds to upload the data to server
     * onSuccess: Flush the uploaded data from the database
     * onFailure: Do nothing
     */
    public void start() {
        scheduler = new TaskScheduler();
        scheduler.scheduleEvery(() -> {
            List<SpeedModel> speedData = SpeedRepository.getInstance(EularMotorApplication.getApplication()).
                    getSpeedData(10);// latest 10 record will be published to server.
            Log.d(TAG, "run: data: " + speedData.toString());
            NetworkProviderHandler.getInstance().publishSpeedData(speedData.toString(), new NetworkCallback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "onSuccess: ................");
                    SpeedRepository.getInstance(EularMotorApplication.getApplication()).delete(speedData.size());
                }

                @Override
                public void onFailure() {
                    Log.d(TAG, "onFailure: ...............");
                }
            });
        }, SYNC_DURATION);//every 5 sec.
    }

    public void stop() {
        if (scheduler != null)
            scheduler.cancel();
    }
}
