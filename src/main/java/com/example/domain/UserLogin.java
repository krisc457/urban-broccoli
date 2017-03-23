package com.example.domain;

public class UserLogin {
    public final String username;
    public final String password;
    public final long userId;

    public UserLogin(String userName, String password, long userId) {
        this.username = userName;
        this.password = password;
        this.userId = userId;
    }
}
