package com.example.asus.home;

/**
 * Created by User on 2017/3/14.
 */

public class Model {

    Boss boss;
    static Model instance = new Model();

    protected Model() {}

    public static Model getInstance() {
        return instance;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

}
