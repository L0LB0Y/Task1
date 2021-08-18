package com.example.task1.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("/photos")
    Call<List<Author>> getAllData();
}
