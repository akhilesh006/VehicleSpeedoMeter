package com.app.eularmotor.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.eularmotor.data.dao.SpeedDao;
import com.app.eularmotor.data.model.SpeedModel;

@Database(entities = {SpeedModel.class}, version = 2)
public abstract class VehicleDatabase extends RoomDatabase {

    private static VehicleDatabase database;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DatabaseAsyncTask(database).execute();
        }
    };

    public abstract SpeedDao dao();

    public static synchronized VehicleDatabase getInstance(Context context){

        if (database==null){
            database = Room.databaseBuilder(context.getApplicationContext(),VehicleDatabase.class,"vehicle_db")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }


    private static class DatabaseAsyncTask extends AsyncTask<Void,Void,Void> {
        public DatabaseAsyncTask(VehicleDatabase database) {
            SpeedDao dao = database.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
