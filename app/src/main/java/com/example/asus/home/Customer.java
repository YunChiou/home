package com.example.asus.home;

public class Customer extends User {

    private int customerID;
    private String condition;

    public Customer() {
    }

    public Customer(int customerID, String account, String password, String name, String condition) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.customerID = customerID;
        this.condition = condition;
    }

    public String getCondition() {return condition;}

    public void setCondition(String condition) {this.condition = condition;}
}
