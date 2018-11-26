package com.example.madi.workhard2.interfaces;

import com.example.madi.workhard2.Objects.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDB {
    @GET("3/movie/top_rated")
    Call<Result> getData(@Query("api_key") String key,
                         @Query("language") String lang,
                         @Query("page") int page);
}