package com.project.techupdate.model.dto;


public class Token {
    private String token;
    private String username;

    public Token(String token) {
        this.token = token;
    }
    public Token(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
