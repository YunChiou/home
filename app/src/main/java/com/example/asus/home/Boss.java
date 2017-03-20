package com.example.asus.home;

public class Boss extends User {

    private int bossID;
    private String address;
    private String phone;
    private String storename;

    public Boss() {

    }

    public Boss(int bossID, String account, String password, String name, String address, String phone, String storename) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.bossID = bossID;
        this.address = address;
        this.phone = phone;
        this.storename = storename;

    }

    public int getBossID() {
        return bossID;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getStorename() {return storename; }

    public void setBossID(int bossID) {
        this.bossID = bossID;
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
}
