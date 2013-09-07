package com.mcastro.weather;

/**
 * Created by Manny on 9/3/13.
 */
public class AppWeatherData {
    private Double mCurrentTemp;
    private Double mCurrentPrecipPercent;
    private Double[] mHourlyTemp;
    private Double[] mHourlyPrecipPercent;
    private Double mHighTemp;
    private Double mLowTemp;

    public Double getmCurrentTemp() {
        return mCurrentTemp;
    }

    public String getmCurrentTempString(){
        return mCurrentTemp.toString();
    }

    public void setmCurrentTemp(Double mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public Double getmCurrentPrecipPercent() {
        return mCurrentPrecipPercent;
    }

    public void setmCurrentPrecipPercent(Double mCurrentPrecipPercent) {
        this.mCurrentPrecipPercent = mCurrentPrecipPercent;
    }

    public Double[] getmHourlyTemp() {
        return mHourlyTemp;
    }

    public void setmHourlyTemp(Double[] mHourlyTemp) {
        this.mHourlyTemp = mHourlyTemp;
    }

    public Double[] getmHourlyPrecipPercent() {
        return mHourlyPrecipPercent;
    }

    public void setmHourlyPrecipPercent(Double[] mHourlyPrecipPercent) {
        this.mHourlyPrecipPercent = mHourlyPrecipPercent;
    }

    public Double getmHighTemp() {
        return mHighTemp;
    }

    public void setmHighTemp(Double mHighTemp) {
        this.mHighTemp = mHighTemp;
    }

    public Double getmLowTemp() {
        return mLowTemp;
    }

    public void setmLowTemp(Double mLowTemp) {
        this.mLowTemp = mLowTemp;
    }
}
