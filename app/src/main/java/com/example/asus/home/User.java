package com.example.asus.home;

public class User {

    public String account;
    public String password;
    public String name;

    public User() {

    }

    public User(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
