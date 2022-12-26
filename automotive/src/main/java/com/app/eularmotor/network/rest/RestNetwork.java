package com.app.eularmotor.network.rest;

import android.util.Log;

import com.app.eularmotor.network.NetworkCallback;
import com.app.eularmotor.network.NetworkProviderHandler;
import com.app.eularmotor.network.api.EularAPIClient;
import com.app.eularmotor.network.NetworkProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is responsible to send data to server using HTTPS rest API.
 */
public class RestNetwork extends NetworkProviderHandler {
    private static final String TAG = "RestNetwork";

    @Override
    public void publishSpeedData(String data, NetworkCallback callback) {
        Call<Object> apiCall = EularAPIClient.getINSTANCE().getEularAPI().postData(data);
        apiCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse: " + response.message() + ", " + response.body());
                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "onFailure: exception :", t);
                callback.onFailure();
            }
        });
    }
}
