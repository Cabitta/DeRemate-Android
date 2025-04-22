package com.example.deremate_android.data.model;

import java.util.Date;

public class AvailableRoute {

    private String address;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String packageSector;
    private int packageEstante;
    private int packageColumnaEstante;

    public AvailableRoute(String address, String clientFirstName, String clientLastName, String clientEmail, String packageSector, int packageEstante, int packageColumnaEstante) {
        this.address = address;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.packageSector = packageSector;
        this.packageEstante = packageEstante;
        this.packageColumnaEstante = packageColumnaEstante;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getPackageSector() {
        return packageSector;
    }

    public void setPackageSector(String packageSector) {
        this.packageSector = packageSector;
    }

    public int getPackageEstante() {
        return packageEstante;
    }

    public void setPackageEstante(int packageEstante) {
        this.packageEstante = packageEstante;
    }

    public int getPackageColumnaEstante() {
        return packageColumnaEstante;
    }

    public void setPackageColumnaEstante(int packageColumnaEstante) {
        this.packageColumnaEstante = packageColumnaEstante;
    }
}
