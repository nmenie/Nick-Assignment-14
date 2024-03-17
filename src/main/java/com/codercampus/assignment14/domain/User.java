package com.codercampus.assignment14.domain;

public class User {
    private Long userId;
    private String username;

    public User(String username) {
        // Default constructor
    }

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}