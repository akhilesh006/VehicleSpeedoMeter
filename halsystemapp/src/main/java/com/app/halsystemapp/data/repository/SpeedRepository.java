package com.app.halsystemapp.data.repository;

import android.app.Application;
import android.util.Log;

import com.app.halsystemapp.data.dao.SpeedDao;
import com.app.halsystemapp.data.db.VehicleDatabase;
import com.app.halsystemapp.data.model.SpeedModel;
import com.app.halsystemapp.executor.DefaultThreadExecutorSupplier;

import java.util.List;

/**
 * A repository class which is responsible to CRUD operation on DB.
 */
public class SpeedRepository {

    private static final String TAG = "SpeedRepository";
    private static SpeedRepository INSTANCE;
    private SpeedDao dao;

    private SpeedRepository(Application application) {
        VehicleDatabase database = VehicleDatabase.getInstance(application);
        dao = database.dao();
    }

    public synchronized static SpeedRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new SpeedRepository(application);
        }
        return INSTANCE;
    }

    public void insert(SpeedModel speedModel) {
        Log.d(TAG, "insert: " + speedModel.getSpeed());
        DefaultThreadExecutorSupplier.getExecutor().submit(() -> dao.insert(speedModel));
    }

    public void delete(SpeedModel speedModel) {
        Log.d(TAG, "delete: " + speedModel);
        DefaultThreadExecutorSupplier.getExecutor().execute(() -> dao.delete(speedModel));
    }

    public void deleteAll() {
        Log.d(TAG, "deleteAll: ");
        DefaultThreadExecutorSupplier.getExecutor().execute(() -> dao.deleteAllSpeedData());
    }

    public void delete(int limit) {
        Log.d(TAG, "deleteAll: ");
        DefaultThreadExecutorSupplier.getExecutor().execute(() -> dao.deleteSpeedData(limit));
    }

    public List<SpeedModel> getAllSpeedData() {
        return dao.getAllSpeedData();
    }

    public List<SpeedModel> getSpeedData(int count) {
        return dao.getSpeedData(count);
    }

}
