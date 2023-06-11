package com.example.h071211075_finalmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowService {
    @GET("tv/popular")
    Call<TvResponse> getAiringTodayTV(
            @Query("api_key") String apiKey
    );
}
