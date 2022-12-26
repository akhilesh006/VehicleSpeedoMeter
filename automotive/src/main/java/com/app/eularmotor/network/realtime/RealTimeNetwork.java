package com.app.eularmotor.network.realtime;

import android.util.Log;

import com.app.eularmotor.network.NetworkCallback;
import com.app.eularmotor.network.NetworkProviderHandler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * This class is responsible to send data to server using a socket connection for real time communication.
 */
public class RealTimeNetwork extends NetworkProviderHandler {

    private static final String TAG = "RealTimeNetwork";
    private static final int NORMAL_CLOSE = 100;
    private final OkHttpClient client;
    private Request request;
    private WebSocket webSocket;

    public RealTimeNetwork() {
        client = new OkHttpClient();
        openSocket();
    }

    public void openSocket() {
        request = new Request.Builder().url("wss://echo.websocket.org").build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                Log.d(TAG, "onOpen: socket opened...");
                RealTimeNetwork.this.webSocket = webSocket;
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                webSocket.close(NORMAL_CLOSE, null);
                Log.d(TAG, "onClosing: socket closed...");
                RealTimeNetwork.this.webSocket = webSocket;
            }
        });
    }

    @Override
    public void publishSpeedData(String data, NetworkCallback callback) {
        Log.d(TAG, "publishSpeedData: publish :"+data);
        if (webSocket != null) {
            Log.d(TAG, "publishSpeedData: sending...");
            boolean send = webSocket.send(data);
            Log.d(TAG, "publishSpeedData: send: "+send);
            if (send) {
                callback.onSuccess();
            } else {
                callback.onFailure();
            }
        }
    }

    @Override
    public void closeConnection() {
        if (webSocket!=null) {
            webSocket.close(NORMAL_CLOSE,null);
            webSocket=null;
        }
    }
}
