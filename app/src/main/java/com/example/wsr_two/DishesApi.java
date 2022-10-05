package com.example.wsr_two;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DishesApi {

    @GET("dishes")
    Call<List<Dishes>> getDishes(@Query("version") String version);

}
