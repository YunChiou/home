package com.example.asus.home;

public class Staff extends User{

    private String staffID;
    private String restaurantName;
    private String restaurantAdd;
    private String restaurantPhone;

    public Staff(String account, String password, String name, String staffID, String restaurantName, String restaurantAdd , String restaurantPhone) {
        super.account = account;
        super.password = password;
        super.name = name;
        this.staffID = staffID;
        this.restaurantName = restaurantName;
        this.restaurantAdd = restaurantAdd;
        this.restaurantPhone = restaurantPhone;

    }

    public String getStaffID() {
        return staffID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getrestaurantAdd() {
        return restaurantAdd;
    }

    public String getRestaurantPhone(){return restaurantPhone;}

}
