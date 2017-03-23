package com.example.asus.home;

public class User {

    public int id;
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

    public int getID() {
        return id;
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

    public void setID(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
