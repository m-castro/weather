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
import java.util.ArrayList;
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
        super();
        Log.e("Look", "Step 2 works");
        myFriendDisplayWeatherActivity = act;
        myFriendUserLocationManager = new UserLocationManager(this);
    }

//    Step 6
    public void receiveUserLocation(Location loc) {
        Log.e("Look","Step 5 works");
        ForecastAPIRequestObject thisAPIRequest = new ForecastAPIRequestObject(loc);
        thisAPIRequest.setMyLocation(loc);
        this.execute(thisAPIRequest);
        Log.e("Look", "Step 6 works");
    }

//somewhere in doInBackground, we will...
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



//                10/21/13: hashmap example for hourly data
                // Hashmap for ListView
//                ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
//                JSONObject hourlyJSON1 = rootJSON.getJSONObject("hourly");
//                JSONArray hourlyJSONdata1 = hourlyJSON1.getJSONArray("data");
//                for (int w = 0; w < hourlyJSONdata1.length(); w++){
//                    String value1 = String.valueOf(hourlyJSONdata1.getJSONObject(w).getLong("time"));
//                    String name1 = String.valueOf(hourlyJSONdata1.getJSONObject(w));
////                            String.valueOf(BigDecimal.valueOf(name.getDouble("temperature")).setScale(0, RoundingMode.HALF_UP))
//                // creating new HashMap
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put(value1, name1);
//                contactList.add(map);
//                Log.e("10/21/13 test - PopulateDataTask hourly temp", value1);
//                Log.e("10/21/13 test - PopulateDataTask hourly temp", name1);
//                }
//                10/21/13: Set up ArrayList next time I work on this project


//            9/9/13: Getting hourly temp and hourly precip %
                JSONObject hourlyJSON = rootJSON.getJSONObject("hourly");
                JSONArray hourlyJSONdata = hourlyJSON.getJSONArray("data");

//              9/9/13: Using an hourly hashmap to pull hourly data
                HashMap<String, JSONObject> hourlyHashMap = new HashMap<String, JSONObject>();

                for (int i = 0; i < hourlyJSONdata.length(); i++){
                    Long hourlyTime = hourlyJSONdata.getJSONObject(i).getLong("time");
                    JSONObject name = hourlyJSONdata.getJSONObject(i);
                    Double HourlyTemperature = name.getDouble("temperature");
                    Date dailyDate = new Date(hourlyTime * 1000);
                    DateFormat dailyDateDisplay = new SimpleDateFormat("h a");
                    String value = dailyDateDisplay.format(dailyDate);
                    hourlyHashMap.put(value, name);
//                    Rounding of temperature to nearest degree (no decimals)
                    Log.e("PopulateDataTask rounding of hourly temp", String.valueOf(BigDecimal.valueOf(HourlyTemperature).setScale(0, RoundingMode.HALF_UP)));
                    Log.e("PopulateDataTask hourly temp", value);
                    Log.e("PopulateDataTask hourly temp", HourlyTemperature.toString());
                }

////            9/9/13: End of hourly hashmap

//              9/13/13: Use a daily hashmap to pull daily data
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
                    String weatherDay = dailyMainDisplayTwo.format(dailyDate);
                    Log.e("PopulateDataTask Day of the Week", dailyMainDisplayTwo.format(dailyDate));
                    Log.e("Shorten to", weatherDay);
                    myData.setmDayOfTheWeek(weatherDay);
                    dailyTemperatureMin[d] = dailyName.getDouble("temperatureMin");
                    String dailyMinTempDisplay = String.valueOf(BigDecimal.valueOf(dailyTemperatureMin[d]).setScale(0, RoundingMode.HALF_UP));
                    Log.e("check out daily min temp", dailyMinTempDisplay);
                    myData.setmDailyLoTemp(dailyMinTempDisplay);
                    dailyTemperatureMax[d] = dailyName.getDouble("temperatureMax");
                    String dailyMaxTempDisplay = String.valueOf(BigDecimal.valueOf(dailyTemperatureMax[d]).setScale(0, RoundingMode.HALF_UP));
                    Log.e("check out daily max temp", dailyMaxTempDisplay);
                    myData.setmDailyHiTemp(dailyMaxTempDisplay);

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
//                myData.setmDayOfTheWeek(dailyWeekday);
                myData.setmDailyLowTemp(dailyTemperatureMin);
                myData.setmDailyHighTemp(dailyTemperatureMax);

//              9/10/13: Getting the time/date updated data and send it to the main activity to display
                Long epochLongDate = new Long(currentlyJSON.getLong("time"));
                Date updatedDate = new Date(epochLongDate * 1000);
                DateFormat df = new SimpleDateFormat("MM/dd/yy");
                DateFormat hourMainDisplay = new SimpleDateFormat("HH:MM a");
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
