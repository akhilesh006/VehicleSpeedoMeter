package com.app.eularmotor.network;

import com.app.eularmotor.network.realtime.RealTimeNetwork;
import com.app.eularmotor.network.rest.RestNetwork;

public class NetworkProviderHandler implements NetworkProvider {

//    private static NetworkProviderHandler INSTANCE = new RestNetwork();
    private static NetworkProviderHandler INSTANCE = new RealTimeNetwork();

    public static NetworkProviderHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void publishSpeedData(String data, NetworkCallback callback) {
        INSTANCE.publishSpeedData(data, callback);
    }

    public void closeConnection() {
        INSTANCE.closeConnection();
    }
}
