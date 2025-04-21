package com.example.deremate_android.data.service;

import com.example.deremate_android.data.model.Delivery;
import com.example.deremate_android.data.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("register")
    public Call<RegisterResponse> registerDelivery(@Body Delivery delivery);
}

