package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Nav_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        hideItem();
    }
    //針對顧客或店家隱藏部分item
    private void hideItem()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(Model.getInstance().getUser().getType().equals("c")){
            nav_Menu.findItem(R.id.store_info).setVisible(true);
            nav_Menu.findItem(R.id.layout_edit).setVisible(false);
            nav_Menu.findItem(R.id.layout).setVisible(false);
            nav_Menu.findItem(R.id.viewTables).setVisible(false);
            nav_Menu.findItem(R.id.map).setVisible(true);
            nav_Menu.findItem(R.id.qrcode_scan_cus).setVisible(false);
            nav_Menu.findItem(R.id.coupon).setVisible(true);
            nav_Menu.findItem(R.id.store).setVisible(true);
            nav_Menu.findItem(R.id.all__restaurants).setVisible(true);
            nav_Menu.findItem(R.id.give_coupon).setVisible(true);
            nav_Menu.findItem(R.id.profile).setVisible(true);
            nav_Menu.findItem(R.id.logout).setVisible(true);
        }

        else{
            nav_Menu.findItem(R.id.store_info).setVisible(true);
            nav_Menu.findItem(R.id.layout_edit).setVisible(true);
            nav_Menu.findItem(R.id.layout).setVisible(true);
            nav_Menu.findItem(R.id.viewTables).setVisible(true);
            nav_Menu.findItem(R.id.map).setVisible(true);
            nav_Menu.findItem(R.id.qrcode_scan_cus).setVisible(true);
            nav_Menu.findItem(R.id.coupon).setVisible(false);
            nav_Menu.findItem(R.id.store).setVisible(false);
            nav_Menu.findItem(R.id.all__restaurants).setVisible(true);
            nav_Menu.findItem(R.id.give_coupon).setVisible(false);
            nav_Menu.findItem(R.id.profile).setVisible(true);
            nav_Menu.findItem(R.id.logout).setVisible(true);
            nav_Menu.findItem(R.id.profit).setVisible(true);
            nav_Menu.findItem(R.id.response).setVisible(true);
    }
    }
    //用來extend的
    public void addContentView(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null, false);
        drawer.addView(contentView, 0);
    }
    @Override

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.qrcode_scan_cus) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, QRcode_Scanner.class);
            startActivity(intent);
        }
        else if (id == R.id.store) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, Select_Restaurants.class);
            startActivity(intent);
        }
        else if (id == R.id.store_info) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, RestaurantData.class);
            startActivity(intent);
        }
        else if (id == R.id.profile) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, CustomerData.class);
            startActivity(intent);
        }
        else if (id == R.id.logout) {
            MainActivity.prefs.edit().clear().commit();
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.give_coupon) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, customer_selectoffer.class);
            startActivity(intent);
        }
        else if (id == R.id.coupon) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, SelectOffer.class);
            startActivity(intent);
        }
        else if (id == R.id.layout) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, TableLayout.class);
            startActivity(intent);
        }
        else if (id == R.id.layout_edit) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, TableEditor.class);
            startActivity(intent);
        }
        else if (id == R.id.all__restaurants) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, GetAllRestaurants.class);
            startActivity(intent);
        }
        else if (id == R.id.viewTables) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, TableMessaging.class);
            startActivity(intent);
        }
        else if (id == R.id.profit) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, ProfitCounter.class);
            startActivity(intent);
        }
        else if (id == R.id.response) {
            Intent intent = new Intent();
            intent.setClass(Nav_drawer.this, ResponseRequest.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

