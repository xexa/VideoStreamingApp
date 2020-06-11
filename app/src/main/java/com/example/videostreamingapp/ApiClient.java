package com.example.videostreamingapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://raw.githubusercontent.com/bikashthapa01/";

    private static ApiClient apiClient;
    private Retrofit retrofit;


    private ApiClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null)
            apiClient = new ApiClient();
        return apiClient;
    }

    public APIInterface getApi(){
        return retrofit.create(APIInterface.class);
    }

}
