package com.mcastro.weather;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private Double[] mDailyHighTemp;
    private Double[] mDailyLowTemp;
    private String mDayOfTheWeek;
    private String mRefreshTime;
    private String mDailyHiTemp;
    private String mDailyLoTemp;

    public String getmRefreshTime() {
        return mRefreshTime;
    }

    public String getmRefreshTimeString(){
        return mRefreshTime.toString();
    }

    public void setmRefreshTime(String mRefreshTime) {
        this.mRefreshTime = mRefreshTime;
    }

    public Double getmCurrentTemp() {
        return mCurrentTemp;
    }

    public String getmCurrentTempString(){
        return String.valueOf(BigDecimal.valueOf(mCurrentTemp).setScale(0, RoundingMode.HALF_UP));
    }

    public void setmCurrentTemp(Double mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public Double getmCurrentPrecipPercent() {
        return mCurrentPrecipPercent;
    }

    public String getmCurrentPrecipPercentString(){
        return String.valueOf(BigDecimal.valueOf(mCurrentPrecipPercent).setScale(0, RoundingMode.HALF_UP));
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

    public Double[] getmDailyHighTemp() {
        return mDailyHighTemp;
    }

    public void setmDailyHighTemp(Double[] mDailyHighTemp) {
        this.mDailyHighTemp = mDailyHighTemp;
    }

    public String getmDailyHighTempString() {
        return mDailyHighTemp.toString();
    }

    public String getmDailyHiTemp() {
        return mDailyHiTemp;
    }

    public void setmDailyHiTemp(String mDailyHiTemp) {
        this.mDailyHiTemp = mDailyHiTemp;
    }

    public Double[] getmDailyLowTemp() {
        return mDailyLowTemp;
    }

    public void setmDailyLowTemp(Double[] mDailyLowTemp) {
        this.mDailyLowTemp = mDailyLowTemp;
    }

    public String getmDailyLoTemp() {
        return mDailyLoTemp;
    }

    public void setmDailyLoTemp(String mDailyLoTemp) {
        this.mDailyLoTemp = mDailyLoTemp;
    }

    public String getmDayOfTheWeek() {
        return mDayOfTheWeek;
    }

    public void setmDayOfTheWeek(String mDayOfTheWeek) {
        this.mDayOfTheWeek = mDayOfTheWeek;
    }


}
