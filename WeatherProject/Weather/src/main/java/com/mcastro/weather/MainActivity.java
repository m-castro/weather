package com.mcastro.weather;



//The main activity that we will be using to display weather data

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    public UserLocationManager mMyPersonalLocationManager;
    public PopulateAWeatherDataObject myWeatherDataPopulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goGetUserLocation();

    }

    private void updateDisplay(){
        //check to see if we have some weather data, and
        //update the screen to reflect that new data
    }

    //
    //  The following two methods are used to start other asynchronous
    // processes outside of this activity.
    //

    public void goGetWeatherData(Location location){
        //this method could also take two double parameters for lat and
        //long depending on how you want to move the data around.
        myWeatherDataPopulator = new PopulateAWeatherDataObject(this);
        myWeatherDataPopulator.execute(location);
    }

    public void goGetUserLocation(){
        mMyPersonalLocationManager = new UserLocationManager(this);

    }

    //
    //  The two methods below receive data into the class
    //

    public void receiveUserLocation(){
        // In this method I'm receiving the user location
        // I need to call a method that takes that location and  makes a request
        // to the Forecast API.
    }

    public void receiveWeatherData(){
        //I got weather data, now do something with it
        updateDisplay();  //update the display to show the new info

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
