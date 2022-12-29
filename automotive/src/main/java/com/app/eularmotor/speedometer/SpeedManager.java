package com.app.eularmotor.speedometer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.eularmotor.speedometer.ui.model.VehicleModel;

/**
 * A manager class which is responsible for managing the speed data at application layer.
 */
public class SpeedManager {

    private static final SpeedManager INSTANCE = new SpeedManager();
    private final MutableLiveData<VehicleModel> currentSpeed = new MutableLiveData<>();

    private SpeedManager() {
    }

    public static SpeedManager getINSTANCE() {
        return INSTANCE;
    }

    public LiveData<VehicleModel> getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Update the mutable live data object, and write speed data on database.
     *
     * @param speed current speed
     */
    public synchronized void setCurrentSpeed(int speed) {
        //Display on UI
        currentSpeed.postValue(new VehicleModel(speed));
    }

}
