package com.app.eularmotor.speedometer.model;

/**
 * An POJO class to keep the vehicle metadata.
 */
public class VehicleModel {

    private int speed;

    public VehicleModel(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}
