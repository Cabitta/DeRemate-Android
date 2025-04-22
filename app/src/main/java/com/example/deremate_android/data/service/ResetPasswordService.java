package com.example.deremate_android.data.service;
import com.example.deremate_android.data.model.ResetPasswordRequest;
import com.example.deremate_android.data.model.ResetPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ResetPasswordService {
    @POST("api/resetpassword")
        // Ajusta la ruta según tu backend
    Call<ResetPasswordResponse> resetearpassword(@Body ResetPasswordRequest resetpasswordrequest);
}
