package com.example.asus.home;

/**
 * Created by atiff on 2017/3/23.
 */

public class Restaurant  {

    private int ID;
    private String address;
    private String phone;
    private String storename;

    public Restaurant() {

    }

    public Restaurant(int ID, String address, String phone, String storename) {
        this.ID = ID;
        this.address = address;
        this.phone = phone;
        this.storename = storename;

    }

    public int getID() {
        return ID;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getStorename() {return storename; }

    public void setID(int ID) {this.ID = ID; }

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
