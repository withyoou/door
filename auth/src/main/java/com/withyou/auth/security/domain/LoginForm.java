package com.withyou.auth.security.domain;

/**
 * @Author admin
 * @Date 2019-10-08 16:13
 **/
public class LoginForm {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
