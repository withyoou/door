package com.withyou.services.demo.domain;


public class AuthUser {

    private String user;
    private String roles;

    public AuthUser() {}

    public AuthUser(String user, String roles) {
        this.user = user;
        this.roles = roles;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
