package com.example.deremate_android.data.model;

import com.google.gson.annotations.SerializedName;

public class DeliveryHistory {
    @SerializedName("id")
    private String routeId;

    @SerializedName("state")
    private String state;

    @SerializedName("end_date_time")
    private String end_date_time;

    @SerializedName("client_name")
    private String client_name;

    @SerializedName("client_lastname")
    private String client_lastname;

    @SerializedName("delivery_time")
    private String delivery_time;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public void setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }
}
