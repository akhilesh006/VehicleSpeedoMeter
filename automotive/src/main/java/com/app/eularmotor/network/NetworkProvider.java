package com.app.eularmotor.network;

public interface NetworkProvider {

    void publishSpeedData(String data, NetworkCallback callback);
}
