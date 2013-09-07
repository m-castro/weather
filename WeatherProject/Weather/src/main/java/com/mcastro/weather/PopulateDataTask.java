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
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

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

//                The next 4 lines are the long way to code what is coded below
//                Double currentTemp = currentlyJSON.getDouble("temperature");

//                and put that data in the AppWeatherData object
//                myData.setmCurrentTemp(currentTemp);

                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));
                myData.setmCurrentPrecipPercent(currentlyJSON.getDouble("precipProbability"));


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

        myFriendDisplayWeatherActivity.receiveWeatherData(appWeatherData);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
