package com.example.asus.home;

public class Customer extends User{

    private String customerID;
    private String age;

    public Customer(String account, String password, String name, String customerID, String age) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.customerID = customerID;
        this.age = age;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getAge() {
        return age;
    }

}
