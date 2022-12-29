package com.app.eularmotor.speedometer.ui.model;

/**
 * An POJO class to keep the vehicle metadata.
 */
public class VehicleModel {

    private final int speed;

    public VehicleModel(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}
