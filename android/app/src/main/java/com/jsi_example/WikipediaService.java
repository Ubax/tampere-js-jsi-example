package com.jsi_example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WikipediaService {
        @GET("page/summary/{name}")
        Call<WikipediaResponse> getPageSummary(@Path("name") String name);
}