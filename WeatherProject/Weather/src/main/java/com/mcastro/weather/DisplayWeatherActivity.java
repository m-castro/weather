package com.mcastro.weather;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
//The main activity that we will be using to display weather data


public class DisplayWeatherActivity extends Activity {

    public PopulateDataTask myFriendPopulateDataTask;
    public UserLocationManager mMyPersonalLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("look","it runs");
        goGetWeatherData();
    }

//    private void updateDisplay(){
////        check to see if we have some weather data, and
////        update the screen to reflect that new data
//
//
//    }

    //
    //  The following two methods are used to start other asynchronous
    // processes outside of this activity.
    //

//    public void goGetWeatherData(Location location){
        //this method could also take two double parameters for lat and
        //long depending on how you want to move the data around.
//        myWeatherDataPopulator = new PopulateAWeatherDataObject(this);
//        myWeatherDataPopulator.execute(location);
//    }

//    private void goGetUserLocation(){
//        mMyPersonalLocationManager = new UserLocationManager(this);
//
//    }

//    9/13/13: would like to get the location from the GeoNames API here and display the data
//    public class GeoLocation extends AsyncTask<>

//    Step 1
    public void goGetWeatherData(){
        Log.e("Look", "Step 1 works");
        myFriendPopulateDataTask = new PopulateDataTask(this);
    }

    //
    //  The two methods below receive data into the class
    //

//    public void receiveUserLocation(){
////        In this method I'm receiving the user location
////        I need to call a method that takes that location and  makes a request
////        to the API.
//    }

//    Data type = AppWeatherDate; name can be whatever, but make sure it matches the name when it is called,
//    for example, like it is in log below.


//    Step 8
      public void receiveWeatherData(AppWeatherData myData){
          Log.e("look", "Step 8 works");
//          updateDisplay();

          TextView textview = (TextView) findViewById(R.id.currentTempFromJSON);
          textview.setText(myData.getmCurrentTempString() + (char) 0x00B0 + "F");

          TextView textViewPrecip = (TextView) findViewById(R.id.percentPrecipFromJSON);
          textViewPrecip.setText(myData.getmCurrentPrecipPercentString()+"%");

          TextView textViewUpdatedTimeDisplay = (TextView) findViewById(R.id.updatedDateTime);
          textViewUpdatedTimeDisplay.setText(myData.getmRefreshTime());

          TextView textviewFifthDayofWeek = (TextView) findViewById(R.id.fifthDayWeekly);
          textviewFifthDayofWeek.setText(myData.getmDayOfTheWeek());

          TextView textviewFifthDayHighTemp = (TextView) findViewById(R.id.fifthDayHigh);
          textviewFifthDayHighTemp.setText(myData.getmDailyHiTemp());

          TextView textviewFifthDayLowTemp = (TextView) findViewById(R.id.fifthDayLow);
          textviewFifthDayLowTemp.setText(myData.getmDailyLoTemp());

          TextView textViewLaunchHourlyData = (TextView) findViewById(R.id.detailedHourlyInfo);
          textViewLaunchHourlyData.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent i = new Intent(getApplicationContext(),Hourly.class);
                  startActivity(i);
                  finish();
              }
          });


//    public void receiveWeatherData(AppWeatherData myDataObject){
//    public void receiveWeatherData(){
//        Log to make sure we are accessing data and displaying it correctly

//        I got weather data, now do something with it
//            updateDisplay();  //update the display to show the new info
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
