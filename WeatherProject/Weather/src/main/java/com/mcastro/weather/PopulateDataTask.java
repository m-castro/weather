package com.mcastro.weather;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Manny on 9/3/13.
 */
public class PopulateDataTask extends AsyncTask<ForecastAPIRequestObject, Integer, AppWeatherData>{

    public DisplayWeatherActivity myFriendDisplayWeatherActivity;
    public UserLocationManager myFriendUserLocationManager;

//    Step 2 and 3
    public PopulateDataTask(DisplayWeatherActivity act) {
//        this is the constructor
        super();
        Log.e("Look", "Step 2 works");
        myFriendDisplayWeatherActivity = act;
        myFriendUserLocationManager = new UserLocationManager(this);
    }

//    Step 6
    public void receiveUserLocation(Location loc) {
        Log.e("Look","Step 5 works");
        ForecastAPIRequestObject thisAPIRequest = new ForecastAPIRequestObject(loc);
//        Tom does not have the line below in his code.
//        thisAPIRequest.setMyLocation(loc);
        this.execute(thisAPIRequest);
        Log.e("Look", "Step 6 works");
    }



//somewhere in doInBackground, we will
    @Override
    protected AppWeatherData doInBackground(ForecastAPIRequestObject... forecastAPIRequestObjects) {
//        get the information that we need from the API

        //        create an AppWeatherData object
        AppWeatherData myData = new AppWeatherData();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet g = new HttpGet(forecastAPIRequestObjects[0].getAssembledURL());
            HttpResponse httpResponse = httpClient.execute(g);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();

//        parse the data from the API using JSON Object methods

                JSONObject rootJSON = new JSONObject(responseString);
                JSONObject currentlyJSON = rootJSON.getJSONObject("currently");

////            9/9/13: Getting hourly temp and hourly precip %
                JSONObject hourlyJSON = rootJSON.getJSONObject("hourly");
                JSONArray hourlyJSONdata = hourlyJSON.getJSONArray("data");

//              9/9/13: Using an hourly hashmap to pull hourly data
                HashMap<Long, JSONObject> hourlyHashMap = new HashMap<Long, JSONObject>();

                for (int i = 0; i < hourlyJSONdata.length(); i++){
                    Long value = hourlyJSONdata.getJSONObject(i).getLong("time");
                    JSONObject name = hourlyJSONdata.getJSONObject(i);
                    hourlyHashMap.put(value, name);
                    Double HourlyTemperature = name.getDouble("temperature");
//                    Rounding of temperature to nearest degree (no decimals)
                    Log.e("PopulateDataTask rounding of hourly temp", String.valueOf(BigDecimal.valueOf(HourlyTemperature).setScale(0, RoundingMode.HALF_UP)));
                    Log.e("PopulateDataTask hourly temp", HourlyTemperature.toString());
                }
////            9/9/13: End of hourly hashmap

//              9/13/13: Trying to use a daily hashmap to pull daily data
                Log.e("look", rootJSON.toString());
                JSONObject dailyJSON = rootJSON.getJSONObject("daily");
                Log.e("look", dailyJSON.toString());
                JSONArray dailyJSONdata = dailyJSON.getJSONArray("data");
                Log.e("look", dailyJSONdata.toString());

//              9/9/13: Using a daily hashmap to pull daily data
                HashMap<Long, JSONObject> dailyHashMap = new HashMap<Long, JSONObject>();

                Long[] dailyWeekday = new Long[5];
                Double[] dailyTemperatureMin = new Double[5];
                Double[] dailyTemperatureMax = new Double[5];

                for (int d = 0; d < 5; d++){
                    Long dailyValue = dailyJSONdata.getJSONObject(d).getLong("time");
                    JSONObject dailyName = dailyJSONdata.getJSONObject(d);
                    dailyHashMap.put(dailyValue, dailyName);
                    Log.e("look", "getting the hashmap daily data");
                    dailyWeekday[d] = dailyName.getLong("time");
                    Date dailyDate = new Date(dailyWeekday[d] * 1000);
                    DateFormat dailyMainDisplayTwo = new SimpleDateFormat("E");
                    Log.e("PopulateDataTask Day of the Week", dailyMainDisplayTwo.format(dailyDate));
//                    Log.e("PopulateDataTask Day of the Week", dailyMainDisplayTwo.format(dailyWeekday[d]));
//                    Log.e("PopulateDataTask current day", dailyWeekday.toString());
//                    Log.e("PopulateDataTask current day", String.valueOf(dailyWeekday));
//                    Log.e("PopulateDataTask current day", String.valueOf(dailyWeekday[d]));
//                    Log.e("PopulateDataTask current day", dailyWeekday[d].toString());
                    dailyTemperatureMin[d] = dailyName.getDouble("temperatureMin");
//                    DecimalFormat decFormat = new DecimalFormat("#");
                    String dailyMinTempDisplay = String.valueOf(BigDecimal.valueOf(dailyTemperatureMin[d]).setScale(0, RoundingMode.HALF_UP));
//                    Log.e("PopulateDataTask daily min temp", dailyTemperatureMin.toString());
//                    Log.e("daily min temp", String.valueOf(dailyTemperatureMin));
                    Log.e("check out daily min temp", dailyMinTempDisplay);
                    dailyTemperatureMax[d] = dailyName.getDouble("temperatureMax");
                    String dailyMaxTempDisplay = String.valueOf(BigDecimal.valueOf(dailyTemperatureMax[d]).setScale(0, RoundingMode.HALF_UP));
                    Log.e("check out daily max temp", dailyMaxTempDisplay);
//                    Log.e("PopulateDataTask daily max temp", dailyTemperatureMax.toString());
                }
//              9/9/13: End of daily hashmap

//              9/9/13: Start parsing JSON data for daily weather info that will go on Main Activity
//                      (DisplayWeatherActivity.java)
//                JSONObject dailyJSON = rootJSON.getJSONObject("daily");
//                JSONArray dailyJSONData = dailyJSON.getJSONArray("data");

//                JSONObject hourlyJSONTemp = hourlyJSONdata.getJSONObject("temperature");

//                The next 4 lines are the long way to code what is coded below
//                Double currentTemp = currentlyJSON.getDouble("temperature");

//                and put that data in the AppWeatherData object
//                myData.setmCurrentTemp(currentTemp);

                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));
                myData.setmCurrentPrecipPercent(currentlyJSON.getDouble("precipProbability"));

//              9/13/13: Daily data
                myData.setmDayOfTheWeek(dailyWeekday);
                myData.setmDailyLowTemp(dailyTemperatureMin);
                myData.setmDailyHighTemp(dailyTemperatureMax);

//              9/10/13: Getting the time/date updated data and send it to the main activity to display
//                Date epochDate = new Date(currentlyJSON.getLong("time"));
                Long epochLongDate = new Long(currentlyJSON.getLong("time"));
//                Log.e("Look", (epochDate.toString()));
//                Log.e("Look (epochLongDate.toString())", (epochLongDate.toString()));
                Date updatedDate = new Date(epochLongDate * 1000);
                DateFormat df = new SimpleDateFormat("MM/dd/yy");
//                Log.e("Hey df.format(updatedDate)", df.format(updatedDate));
                DateFormat hourMainDisplay = new SimpleDateFormat("HH:MM a");
//                Log.e("Hey hourMainDisplay.format(updatedDate)", hourMainDisplay.format(updatedDate));
                String timeString = "Last updated: " + df.format(updatedDate) + " at " + hourMainDisplay.format(updatedDate);
                myData.setmRefreshTime(timeString);
                Log.e("Look timeString", timeString);


//                9/13/13: Getting the daily weather data
//                DateFormat dailyMainDisplay = new SimpleDateFormat("E");
//                Log.e("PopulateDataTask Day of the Week", dailyMainDisplay.format(updatedDate));


//                long date=System.currentTimeMillis(); //current android time in epoch
////Converts epoch to "dd/MM/yyyy HH:mm:ss" dateformat
//                String NormalDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(date));


//                myData.setmRefreshTime(currentlyJSON.getLong("time"));
//                myData.setmRefreshTime(currentlyJSON.getLong("time"));
//                hourlyHashMap.put(hourlyJSONdata.getLong("time"), "temperature");
//                hourlyHashMap.put(time, "temperature");



            } else {
                httpResponse.getEntity().getContent().close();
            }
        } catch (Exception e) {
        }

        return myData;//return an AppWeatherData object
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

//    Step 7
    @Override
    protected void onPostExecute(AppWeatherData appWeatherData) {
        super.onPostExecute(appWeatherData);
//        gets the result of doInBackground as an AppWeatherData object


//        put the data somewhere else to display it.

//        this is getting the data ready to send to the main activity (DisplayWeatherActivity)
        Log.e("Look","Step 7 works");

        myFriendDisplayWeatherActivity.receiveWeatherData(appWeatherData);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
