package com.example.deremate_android.data.model;

public class ResetPasswordResponse {
    private String mensaje;

    public ResetPasswordResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;

    }

    public String getMensaje() {
        return mensaje;
    }
}


