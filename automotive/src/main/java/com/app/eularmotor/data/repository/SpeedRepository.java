package com.app.eularmotor.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.app.eularmotor.data.dao.SpeedDao;
import com.app.eularmotor.data.db.VehicleDatabase;
import com.app.eularmotor.data.model.SpeedModel;
import com.app.eularmotor.executor.DefaultThreadExecutorSupplier;

import java.util.List;

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
        Log.d(TAG, "insert: "+speedModel.getSpeed());
        DefaultThreadExecutorSupplier.getExecutor().submit(() -> dao.insert(speedModel));
    }

    public void delete(SpeedModel speedModel) {
        Log.d(TAG, "delete: "+speedModel);
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

    public List<SpeedModel> getAllSpeedData(){
        return  dao.getAllSpeedData();
    }

}
