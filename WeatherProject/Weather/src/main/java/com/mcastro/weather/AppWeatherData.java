package com.mcastro.weather;

/**
 * Created by Manny on 9/3/13.
 */
public class AppWeatherData {
    private Double mCurrentTemp;
    private Double mCurrentPrecipPercent;
    private Double mDailyHigh;
    private Double mDailyLow;
    private Double[] mHourlyTemp;
    private Double[] mHourlyPrecipPercent;
    private Double mHighTemp;
    private Double mLowTemp;
    private Long mRefreshTime;

    public Long getmRefreshTime() {
        return mRefreshTime;
    }

    public String getmRefreshTimeString(){
        return mRefreshTime.toString();
    }

    public void setmRefreshTime(Long mRefreshTime) {
        this.mRefreshTime = mRefreshTime;
    }

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

    public String getmCurrentPrecipPercentString(){
        return mCurrentPrecipPercent.toString();
    }

    public void setmCurrentPrecipPercent(Double mCurrentPrecipPercent) {
        this.mCurrentPrecipPercent = mCurrentPrecipPercent;
    }

    public Double getmDailyHigh() {
        return mDailyHigh;
    }

    public void setmDailyHigh(Double mDailyHigh) {
        this.mDailyHigh = mDailyHigh;
    }

    public Double getmDailyLow() {
        return mDailyLow;
    }

    public void setmDailyLow(Double mDailyLow) {
        this.mDailyLow = mDailyLow;
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
