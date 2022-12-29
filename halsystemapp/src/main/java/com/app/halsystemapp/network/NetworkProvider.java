package com.app.halsystemapp.network;

public interface NetworkProvider {

    void publishSpeedData(String data, NetworkCallback callback);
}
