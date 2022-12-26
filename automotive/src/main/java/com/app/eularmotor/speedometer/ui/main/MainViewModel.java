package com.app.eularmotor.speedometer.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.eularmotor.speedometer.SpeedManager;
import com.app.eularmotor.speedometer.model.VehicleModel;

public class MainViewModel extends ViewModel {

    public LiveData<VehicleModel> getCurrentSpeed(){
        return SpeedManager.getINSTANCE().getCurrentSpeed();
    }

}