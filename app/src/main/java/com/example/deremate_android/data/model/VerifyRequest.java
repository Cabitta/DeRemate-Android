// VerifyRequest.java
package com.example.deremate_android.data.model;

public class VerifyRequest {
    private String email;
    private String code;

    public VerifyRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}