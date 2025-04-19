package com.example.deremate_android.data.service;

import com.example.deremate_android.data.model.DeliveryHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeliveryHistoryService {
    @GET("delivery-history-list")
    Call<List<DeliveryHistory>> getDeliveryHistory(@Query("deliveryId") String deliveryId);
}

