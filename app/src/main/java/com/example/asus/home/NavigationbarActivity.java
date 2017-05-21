package com.example.asus.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class NavigationbarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.first) {
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, StorePage.class);
            startActivity(intent);
        }
        else if(id == R.id.second){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, CustomerData.class);
            startActivity(intent);
        }

        else if(id == R.id.third){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, TableLayout.class);
            startActivity(intent);
        }
        else if(id == R.id.tableScanner){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, TableEditor.class);
            startActivity(intent);
        }
        else if(id == R.id.fourth){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, QRcode_generator.class);
            startActivity(intent);
        }
        else if(id == R.id.fifth){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, QRcode_Scanner.class);
            startActivity(intent);
        }
        else if(id == R.id.sixth){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, QRcode_Scanner_Boss.class);
            startActivity(intent);
        }
        else if(id == R.id.seventh){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, RestaurantData.class);
            startActivity(intent);
        }
        else if(id == R.id.eighth){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, SelectOffer.class);
            startActivity(intent);
        }
        else if(id == R.id.ninth){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, All_Restaurants.class);
            startActivity(intent);
        }

        else if(id == R.id.logout){
            Intent intent = new Intent();
            intent.setClass(NavigationbarActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void ItemsToShow (String type) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if(type.equals("c")){
            nav_Menu.findItem(R.id.first).setVisible(false);
            nav_Menu.findItem(R.id.seventh).setVisible(false);
            nav_Menu.findItem(R.id.third).setVisible(false);
            nav_Menu.findItem(R.id.tableScanner).setVisible(false);
            nav_Menu.findItem(R.id.sixth).setVisible(false);
        }
        else if(type.equals("b")){
            nav_Menu.findItem(R.id.second).setVisible(false);
            nav_Menu.findItem(R.id.fourth).setVisible(false);
            nav_Menu.findItem(R.id.fifth).setVisible(false);
        }
    }
}
