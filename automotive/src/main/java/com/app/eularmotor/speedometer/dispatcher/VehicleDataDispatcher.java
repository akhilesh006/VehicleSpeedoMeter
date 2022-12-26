package com.app.eularmotor.speedometer.dispatcher;

import android.util.Log;

import com.app.eularmotor.EularMotorApplication;
import com.app.eularmotor.data.model.SpeedModel;
import com.app.eularmotor.data.repository.SpeedRepository;
import com.app.eularmotor.network.NetworkCallback;
import com.app.eularmotor.network.NetworkProviderHandler;
import com.app.eularmotor.scheduler.TaskScheduler;

import java.util.List;

public class VehicleDataDispatcher {

    private static final String TAG = "VehicleDataDispatcher";
    private static final VehicleDataDispatcher INSTANCE = new VehicleDataDispatcher();
    private static final long SYNC_DURATION = 5000;
    private TaskScheduler scheduler;

    private VehicleDataDispatcher() {
    }

    public static VehicleDataDispatcher getInstance() {
        return INSTANCE;
    }

    public void start() {
        scheduler = new TaskScheduler();
        scheduler.scheduleEvery(() -> {
                List<SpeedModel> speedData = SpeedRepository.getInstance(EularMotorApplication.getApplication()).
                        getAllSpeedData();
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
