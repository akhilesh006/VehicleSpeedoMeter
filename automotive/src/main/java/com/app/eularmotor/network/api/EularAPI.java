package com.app.eularmotor.network.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * EularAPI class contains the API
 */
public interface EularAPI {
    public String BASE_URL = "http://192.168.1.5:8080/";

    @POST("postSpeed")
    Call<Object> postData(@Body String body);
}
