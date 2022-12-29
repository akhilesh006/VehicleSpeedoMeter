package com.app.halsystemapp;

import android.app.Application;
import android.util.Log;

public class EularMotorApplication extends Application {

    private static final String TAG = "EularMotorApplication";
    public static EularMotorApplication context;

    public static EularMotorApplication getApplication() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Log.d(TAG, "onCreate: ...");
    }

    @Override
    public void onTerminate() {
        context = null;
        Log.d(TAG, "onTerminate: ...");
        super.onTerminate();
    }
}
