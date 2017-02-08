package com.example.asus.home;

public class Customer {
    private String id;
    private String name;
    private String age;

    public Customer(String customerID, String name, String age) {
        this.id = customerID;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
