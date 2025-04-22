package com.example.deremate_android.data.model;

public class AvailableRoute {

    private String address;
    private String client_name;
    private String client_lastname;
    private String client_email;
    private String package_sector;
    private int package_estante;
    private int package_columna_estante;

    public AvailableRoute(String address, String client_name, String clientLastName, String client_email, String package_sector, int package_estante, int package_columna_estante) {
        this.address = address;
        this.client_name = client_name;
        this.client_lastname = clientLastName;
        this.client_email = client_email;
        this.package_sector = package_sector;
        this.package_estante = package_estante;
        this.package_columna_estante = package_columna_estante;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String clientFirstName) {
        this.client_name = clientFirstName;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public void setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getPackage_sector() {
        return package_sector;
    }

    public void setPackage_sector(String package_sector) {
        this.package_sector = package_sector;
    }

    public int getPackage_estante() {
        return package_estante;
    }

    public void setPackage_estante(int package_estante) {
        this.package_estante = package_estante;
    }

    public int getPackage_columna_estante() {
        return package_columna_estante;
    }

    public void setPackage_columna_estante(int package_columna_estante) {
        this.package_columna_estante = package_columna_estante;
    }
}
