package com.app.eularmotor.speedometer.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.eularmotor.speedometer.SpeedManager;
import com.app.eularmotor.speedometer.ui.model.VehicleModel;

public class MainViewModel extends ViewModel {

    /**
     * Return current speed for vehicle.
     *
     * @return LiveData<VehicleModel> Live observable data.
     */
    public LiveData<VehicleModel> getCurrentSpeed() {
        return SpeedManager.getINSTANCE().getCurrentSpeed();
    }

}