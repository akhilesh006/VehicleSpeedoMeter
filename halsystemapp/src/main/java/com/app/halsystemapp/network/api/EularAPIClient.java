package com.app.halsystemapp.network.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An API provider for HTTPS client.
 */
public class EularAPIClient {
    private static final EularAPIClient INSTANCE = new EularAPIClient();
    private static EularAPI eularAPI;

    private EularAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(eularAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eularAPI = retrofit.create(EularAPI.class);
    }

    public static EularAPIClient getINSTANCE() {
        return INSTANCE;
    }

    public EularAPI getEularAPI() {
        return eularAPI;
    }
}
