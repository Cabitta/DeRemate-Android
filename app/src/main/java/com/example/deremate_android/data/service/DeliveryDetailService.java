package com.example.deremate_android.data.service;

import com.example.deremate_android.data.model.DeliveryDetailItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface DeliveryDetailService {
    // Endpoint para obtener detalles de una entrega específica

    @GET("delivery-details/{routeId}")
    Call<DeliveryDetailItem> getDeliveryDetail(@Path("routeId") String routeId);
}

