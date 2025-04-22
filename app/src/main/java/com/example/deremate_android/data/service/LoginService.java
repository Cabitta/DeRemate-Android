package com.example.deremate_android.data.service;
import com.example.deremate_android.data.model.LoginRequest;
import com.example.deremate_android.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
   @POST("api/login")
      // Ajusta la ruta según tu backend
   Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
