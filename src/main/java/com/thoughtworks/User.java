package com.thoughtworks;

public class User {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private int count;
    private int lockStatement;

    public User(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLockStatement() {
        return lockStatement;
    }

    public int getCount() {
        return count;
    }
}
