package com.example.deremate_android.data.model;

public class UserModel {
    private String _id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    // Otros campos del usuario según tu modelo

    public String getId() {
        return _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
