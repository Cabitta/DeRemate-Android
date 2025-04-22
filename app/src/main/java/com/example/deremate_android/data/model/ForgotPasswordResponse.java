package com.example.deremate_android.data.model;

public class ForgotPasswordResponse {
    private String mensaje;

    public ForgotPasswordResponse(String Email){
        this.mensaje = mensaje;
    }
    public String getEmail(){
        return mensaje;
    }
    public void setEmail(String nuevo){
        mensaje = nuevo;
    }
}

