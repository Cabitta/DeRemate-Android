package com.example.deremate_android.data.service;
import com.example.deremate_android.data.model.ForgotPasswordRequest;
import com.example.deremate_android.data.model.ForgotPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ForgotPasswordService {
    @POST("api/forgotpassword")
        // Ajusta la ruta según tu backend
    Call<ForgotPasswordResponse> forgotpassword(@Body ForgotPasswordRequest forgotPasswordRequest);
}
