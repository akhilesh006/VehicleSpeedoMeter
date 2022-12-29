package com.app.halsystemapp.dispatcher;

import android.os.RemoteException;

import com.app.eularmotor.IVehicleSpeedListener;
import com.app.halsystemapp.EularMotorApplication;
import com.app.halsystemapp.data.model.SpeedModel;
import com.app.halsystemapp.data.repository.SpeedRepository;
import com.app.halsystemapp.utils.TaskScheduler;

/**
 * A singleton class which is responsible for dispatching data to backend.
 */
public class AppDataDispatcher {

    private static final String TAG = "VehicleDataDispatcher";
    private static final AppDataDispatcher INSTANCE = new AppDataDispatcher();
    private static final long SYNC_DURATION = 500;
    private TaskScheduler taskScheduler;

    private AppDataDispatcher() {
    }

    public static AppDataDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Start the scheduler for every 5 seconds to upload the data to server
     * onSuccess: Flush the uploaded data from the database
     * onFailure: Do nothing
     *
     * @param listener
     */
    public void start(IVehicleSpeedListener listener) {
        taskScheduler = new TaskScheduler();
        taskScheduler.scheduleEvery(() -> {
            int speed = (int) (Math.random() * 100);
            try {
                listener.deliverCurrentSpeed(speed);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            //Save on DB
            SpeedRepository.getInstance(EularMotorApplication.getApplication()).insert(new SpeedModel(String.valueOf(speed)));
        }, SYNC_DURATION);
    }

    public void stop() {
        if (taskScheduler != null)
            taskScheduler.cancel();
    }
}
