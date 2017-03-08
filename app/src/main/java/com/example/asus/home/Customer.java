package com.example.asus.home;

public class Customer{

    private int customerID;
    private String account;
    private String password;
    private String name;

    public Customer() {

    }

    public Customer(int customerID, String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
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

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
