package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomePage extends Nav_drawer implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.activity_home_page);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(25.038342, 121.509633);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("餐廳A"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        MarkerOptions one = new MarkerOptions();
        one.position(new LatLng(25.034871, 121.507356));
        one.title("餐廳B");
        one.snippet("每周二公休");
        one.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        one.draggable(false);
        one.visible(true);
        mMap.addMarker(one);

        MarkerOptions two = new MarkerOptions();
        two.position(new LatLng(25.003396, 121.513303));
        two.title("餐廳C");
        two.snippet("假日營業時間為整天");
        two.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        two.draggable(false);
        two.visible(true);
        mMap.addMarker(two);

        //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true); // 右下角的放大縮小功能
        mMap.getUiSettings().setCompassEnabled(true); // 左上角的指南針，要兩指旋轉才會出現
        mMap.getUiSettings().setMapToolbarEnabled(true); // 右下角的導覽及開啟 Google Map功能
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}
