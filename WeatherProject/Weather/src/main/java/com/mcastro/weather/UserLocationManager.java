package com.mcastro.weather;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Manny on 8/30/13.
 */
//added this java file at the end of the day in class on Friday, 8/30/13

public class UserLocationManager implements LocationListener{

    private MainActivity myFriendDisplayWeatherActivity;

    public UserLocationManager (MainActivity a){
//        Set up getting the user location information, however that may be needed.
        myFriendDisplayWeatherActivity = a;

        LocationManager locationManager = (LocationManager) myFriendDisplayWeatherActivity.getSystemService(Context.LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, weatherListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
//        I'll be told the location here
        myFriendDisplayWeatherActivity.receiveUserLocation();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
