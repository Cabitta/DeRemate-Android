// VerifyResponse.java
package com.example.deremate_android.data.model;

public class VerifyResponse {
    private String message;

    public VerifyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}