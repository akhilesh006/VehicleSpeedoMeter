package com.app.eularmotor.network;

import com.app.eularmotor.network.realtime.RealTimeNetwork;
import com.app.eularmotor.network.rest.RestNetwork;

/**
 * A network handler class which can be used for real time network communications based on web socket or HTTPS based communication
 */
public class NetworkProviderHandler implements NetworkProvider {

//    private static NetworkProviderHandler INSTANCE = new RestNetwork();
    private static NetworkProviderHandler INSTANCE = new RealTimeNetwork();

    public static NetworkProviderHandler getInstance() {
        return INSTANCE;
    }

    /**
     * publish the provided data to the server.
     * @param data
     * @param callback
     */
    @Override
    public void publishSpeedData(String data, NetworkCallback callback) {
        INSTANCE.publishSpeedData(data, callback);
    }

    public void closeConnection() {
        INSTANCE.closeConnection();
    }
}
