package com.example.deremate_android.data.model;
import com.google.gson.annotations.SerializedName;

public class DeliveryDetailItem {
    // Campos que vienen del JSON del backend
    @SerializedName("orderId")
    private String orderId;

    @SerializedName("clientName")
    private String clientName;

    @SerializedName("clientLastname")
    private String clientLastname;

    @SerializedName("clientFullName")
    private String clientFullName;

    @SerializedName("address")
    private String address;

    @SerializedName("status")
    private String status;

    @SerializedName("qrCode")
    private String qrCode;

    @SerializedName("packageLocation")
    private PackageLocation packageLocation;

    @SerializedName("initDateTime")
    private String initDateTime;

    @SerializedName("endDateTime")
    private String endDateTime;

    @SerializedName("deliveryTime")
    private String deliveryTime;

    // Clase interna para la ubicación del paquete
    public static class PackageLocation {
        @SerializedName("sector")
        private String sector;

        @SerializedName("shelf")
        private String shelf;

        @SerializedName("column")
        private String column;

        // Getters
        public String getSector() { return sector; }
        public String getShelf() { return shelf; }
        public String getColumn() { return column; }
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getClientName() { return clientName; }
    public String getClientLastname() { return clientLastname; }
    public String getClientFullName() { return clientFullName; }
    public String getAddress() { return address; }
    public String getStatus() { return status; }
    public String getQrCode() { return qrCode; }
    public PackageLocation getPackageLocation() { return packageLocation; }
    public String getInitDateTime() { return initDateTime; }
    public String getEndDateTime() { return endDateTime; }
    public String getDeliveryTime() { return deliveryTime; }
}