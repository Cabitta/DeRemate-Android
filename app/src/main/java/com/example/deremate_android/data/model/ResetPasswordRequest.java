package com.example.deremate_android.data.model;

public class ResetPasswordRequest {
    private String code;
    private String newpassword;
    private String confirmPassword;

    public ResetPasswordRequest(String code, String newPassword, String confirmPassword){
        this.code = code;
        this.newpassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public void setCode(String code){
        this.code = code;
    }
    public void setNewPassword(String newPassword) {
        this.newpassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

    public String getCode() {
        return code;
    }

    public String getNewPassword(){
        return newpassword;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }
}
