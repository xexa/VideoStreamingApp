package com.example.videostreamingapp;

import com.example.videostreamingapp.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("myvideos-android-app/master/data.json")
    Call<ApiResponse> getDataFromServer();
}
