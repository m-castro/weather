package com.mcastro.weather;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.i("Location Coordinates", "Latitude: 30.2715, Longitude: -97.7417");

//        Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

//        Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener () {
            public void onLocationChanged(Location location) {
//                Called when a new location is found by the network location provider
//                location.getLatitude - convert it from double to String, then put String into Log
                double myLatitude = location.getLatitude();
                double myLongitude = location.getLongitude();
                String myLatString = String.valueOf(myLatitude);
                String myLongString = String.valueOf(myLongitude);
                Log.i("Latitude", myLatString);
                Log.i("Longitude", myLongString);

            }


            public void onStatusChanged(String provider, int status, Bundle extras){}

            public void onProviderEnabled(String provider){}

            public void onProviderDisabled(String provider){}
        };
        

//Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
