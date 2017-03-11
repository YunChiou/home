package com.example.asus.home;

public class Boss{

    private int bossID;
    private String account;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String storename;

    public Boss() {

    }

    public Boss(int bossID, String account, String password, String name, String address, String phone, String storename) {
        this.bossID = bossID;
        this.account = account;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.storename = storename;

    }

    public int getBossID() {
        return bossID;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getStorename() {
        return storename;
    }
    public String getName() {
        return name;
    }

    public void setBossID(int bossID) {
        this.bossID = bossID;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setStorename(String storename) {
        this.storename = storename;
    }
    public void setName(String name) {
        this.name = name;
    }
}
