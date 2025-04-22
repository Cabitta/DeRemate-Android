// VerifyAccountService.java
package com.example.deremate_android.data.service;

import com.example.deremate_android.data.model.VerifyRequest;
import com.example.deremate_android.data.model.VerifyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VerifyAccountService {
    @POST("verify")
    Call<VerifyResponse> verifyAccount(@Body VerifyRequest verifyRequest);
}