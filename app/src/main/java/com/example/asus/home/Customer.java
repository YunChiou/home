package com.example.asus.home;

public class Customer extends User {

    private int customerID;

    public Customer() {

    }

    public Customer(int customerID, String account, String password, String name) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
