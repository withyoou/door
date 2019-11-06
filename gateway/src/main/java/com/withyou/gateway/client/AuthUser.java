package com.withyou.gateway.client;


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

    @Override
    public String toString() {
        return "AuthUser{" +
                "user='" + user + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
