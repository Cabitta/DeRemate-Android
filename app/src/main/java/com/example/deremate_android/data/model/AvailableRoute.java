package com.example.deremate_android.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AvailableRoute {

    private Long id; // TODO: ver si eliminar

    private String address;

    // private String delivery; // TODO: String cambiar por CLASE

    private String client; // TODO: String cambiar por CLASE

    private String paquete; // TODO: String cambiar por CLASE

    public AvailableRoute(Long id, String address, String client, String paquete) {
        this.id = id;
        this.address = address;
        this.client = client;
        this.paquete = paquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
