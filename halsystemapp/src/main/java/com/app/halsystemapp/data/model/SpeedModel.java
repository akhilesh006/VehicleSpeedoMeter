package com.app.halsystemapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A model class which is used in ROOM db as table name.
 *
 * @author akhilesh.dhardubey
 */
@Entity(tableName = "speed_table")
public class SpeedModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Speed")
    private String speed;

    public SpeedModel(String speed) {
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return speed;
    }
}
