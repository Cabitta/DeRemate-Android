package com.example.deremate_android.data.repository;

import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.model.Delivery;
import com.example.deremate_android.data.model.RegisterResponse;
import com.example.deremate_android.data.service.RegisterService;

import retrofit2.Callback;

public class DeliveryRepository {
    private final RegisterService registerService;

    public DeliveryRepository() {
        this.registerService = ApiClient.getRegisterService();
    }

    public void registerDelivery(Delivery delivery, Callback<RegisterResponse> callback) {
        registerService.registerDelivery(delivery).enqueue(callback);
    }
}
