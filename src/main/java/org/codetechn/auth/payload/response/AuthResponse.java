package org.codetechn.auth.payload.response;

import org.codetechn.auth.entity.Role;

import java.util.Set;

public class AuthResponse {
    private String email;
    private String number;
    private String accessToken;
    private String roles;


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AuthResponse() { }
    public AuthResponse(String email,String number, String role,String accessToken) {
        this.email = email;
        this.number= number;
        this.roles=role;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
