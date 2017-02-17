package com.example.asus.home;

public class Staff extends User{

    private String staffID;
    private String restaurantName;

    public Staff(String account, String password, String name, String staffID, String restaurantName) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.staffID = staffID;
        this.restaurantName = restaurantName;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

}
