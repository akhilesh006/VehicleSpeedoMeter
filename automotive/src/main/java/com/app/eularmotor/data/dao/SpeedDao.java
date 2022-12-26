package com.app.eularmotor.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.eularmotor.data.model.SpeedModel;

import java.util.List;

/**
 * A Dao class responsible for CRUD queries.
 */
@androidx.room.Dao
public interface SpeedDao {

    @Insert
    void insert(SpeedModel speedModel);

    @Delete
    void delete(SpeedModel speedModel);

    @Query("SELECT * FROM speed_table")
    List<SpeedModel> getAllSpeedData();

    @Query("DELETE FROM speed_table")
    void deleteAllSpeedData();

    @Query("DELETE FROM speed_table WHERE id IN (SELECT id FROM speed_table limit :limit)")
    void deleteSpeedData(int limit);

}
