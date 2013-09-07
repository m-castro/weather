package com.mcastro.weather;

import android.location.Location;

/**
 * Created by Manny on 9/3/13.
 */
public class ForecastAPIRequestObject {

    private Double mLatitude;
    private Double mLongitude;
    private String mURL = "https://api.forecast.io/forecast";
    private String mAPI_KEY = "9d68e5f5835e431ccbd8c2b2c80613d9";
    public Location myLocation;


    public ForecastAPIRequestObject(Location myLocation){
        this.setMyLocation(myLocation);
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
        mLatitude = myLocation.getLatitude();
        mLongitude = myLocation.getLongitude();

    }

    public String getAssembledURL() {
        String myURL = mURL + "/" + mAPI_KEY + "/" + mLatitude.toString() + "," + mLongitude.toString();
        return myURL;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }

    public String getmAPI_KEY() {
        return mAPI_KEY;
    }

    public void setmAPI_KEY(String mAPI_KEY) {
        this.mAPI_KEY = mAPI_KEY;
    }
}
