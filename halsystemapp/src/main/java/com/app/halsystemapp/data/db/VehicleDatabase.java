package com.app.halsystemapp.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.halsystemapp.data.dao.SpeedDao;
import com.app.halsystemapp.data.model.SpeedModel;

/**
 * A database class which is responsible for database creation and db version change.
 */
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

    public static synchronized VehicleDatabase getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), VehicleDatabase.class, "vehicle_db")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract SpeedDao dao();

    private static class DatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        public DatabaseAsyncTask(VehicleDatabase database) {
            SpeedDao dao = database.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
