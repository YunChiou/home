package com.example.asus.home;

/**
 * Created by User on 2017/3/14.
 */

public class Model {

    User user;
    static Model instance = new Model();

    protected Model() {}

    public static Model getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
