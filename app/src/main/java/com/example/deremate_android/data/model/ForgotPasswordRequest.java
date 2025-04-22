package com.example.deremate_android.data.model;

public class ForgotPasswordRequest {
    private String email;

    public ForgotPasswordRequest(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String nuevo){
        email = nuevo;
    }

}
