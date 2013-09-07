package com.mcastro.weather;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by spawrks on 8/30/13.
 */

//  This class wraps all the code we will use to manage getting updates on the location

public class UserLocationManager implements LocationListener{

    private PopulateDataTask myFriendPopulateDataTask;
    public LocationManager lm;

//    What we want to do: send UserLocation info to PopulateDataTask class

//  Step 4
    public UserLocationManager (PopulateDataTask x){
        super();
        Log.e("Look", "Step 3 works");
        myFriendPopulateDataTask = x;
        //Setup getting the user location information, however that might be needed.
//        LocationManager lm = (LocationManager) myFriendPopulateDataTask.myFriendDisplayWeatherActivity.getSystemService(Context.LOCATION_SERVICE);
        lm = (LocationManager) myFriendPopulateDataTask.myFriendDisplayWeatherActivity.getSystemService(Context.LOCATION_SERVICE);
// need try/catch block
//        request location data update
//        (LocationManager.GPS_Provider,0,0,this)
        try{

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    Step 5
    @Override
    public void onLocationChanged(Location location) {
        Log.e("Look", "Step 4 works");
        //I'll be told the location here.
//        myFriendDisplayWeatherActivity.receiveUserLocation();
        lm.removeUpdates(this);
        lm = null;
        myFriendPopulateDataTask.receiveUserLocation(location);
//        After the location code is written, the method above needs to take the parameters of the new location.
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