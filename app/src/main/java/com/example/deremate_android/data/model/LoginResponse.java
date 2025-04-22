package com.example.deremate_android.data.model;

public class LoginResponse {
    private String token;
    private String deliveryId;
    private String message;

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return deliveryId;
    }

    public String getMessage() {
        return message;
    }
}
