package com.example.asus.home;

public class Customer extends User {

    private int customerID;
    private String des;
    private String sale;

    public Customer() {

    }

    public Customer(int customerID, String account, String password, String name,String des,String sale) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.customerID = customerID;
        this.des = des;
        this.sale = sale;
    }

    public String getdes() {return des;}
    public String getsale() {return sale;}
    public int getCustomerID() {return customerID;}

    public void setdes(String des) {this.des =des;}
    public void setsale(String sale) {this.sale= sale;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
}
