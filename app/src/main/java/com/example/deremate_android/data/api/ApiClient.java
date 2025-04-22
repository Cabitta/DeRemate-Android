package com.example.deremate_android.data.api;

import com.example.deremate_android.data.service.RegisterService;
import com.example.deremate_android.data.service.VerifyAccountService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RegisterService getRegisterService() {
        return getClient().create(RegisterService.class);
    }
    public static VerifyAccountService getVerifyAccountService() {
        return getClient().create(VerifyAccountService.class);
    }

}
