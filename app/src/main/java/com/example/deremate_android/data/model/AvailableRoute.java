package com.example.deremate_android.data.model;

import java.util.Date;

public class AvailableRoute {

    private String address;

    // private String delivery; // TODO: String cambiar por CLASE

    private String client; // TODO: String cambiar por CLASE

    private String paquete; // TODO: String cambiar por CLASE

    public AvailableRoute(String address, String client, String paquete) {
        this.address = address;
        this.client = client;
        this.paquete = paquete;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }
}
