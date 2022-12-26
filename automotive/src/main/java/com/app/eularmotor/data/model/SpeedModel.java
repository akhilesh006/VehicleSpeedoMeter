package com.app.eularmotor.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="speed_table")
public class SpeedModel {

    public SpeedModel(String speed){
        this.speed=speed;
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Speed")
    private String speed;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return speed;
    }
}
