package com.example.deremate_android.data.service;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AvailableRoutesService {
    @GET("available-routes")
    Call<List<AvailableRoute>> getAvailableRoutes(@Query("deliveryId") String deliveryId);
}
