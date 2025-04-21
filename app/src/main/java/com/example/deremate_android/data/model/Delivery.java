package com.example.deremate_android.data.model;

import com.google.gson.annotations.SerializedName;

public class Delivery {
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("confirmPassword")
    private String confirmPassword;

    public Delivery(String firstName, String lastname, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastname(){
        return lastname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
