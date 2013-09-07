package com.mcastro.weather;


import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Vic on 13/8/26.
 * ude@learnovatelabs.com
 */
public class Forecast {

    private class FetchDataAsync extends AsyncTask <String, Void, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(String... urls) {
            String link = urls[0];
            HttpGet request = new HttpGet(link);
            AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
            HttpResponse response = null;

            try {
                response = client.execute(request);
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    response.getEntity().writeTo(output);
                    output.close();

                    String result = output.toString();

                    data = new JSONObject(result);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        protected void onPostExecute(HttpResponse res){
            status = res.getStatusLine().getStatusCode();
            response = res;
        }
    }

    private final String API_URL = "https://api.forecast.io/forecast/";
    private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private String API_KEY = "9d68e5f5835e431ccbd8c2b2c80613d9";//    moved this line of code
    // from Forecast.java on 9/3/13; not sure if correct
//    moved API_KEY to Main Activity file on 9/3/13; not sure if correct

    protected int status;
    private HttpResponse response;
    protected JSONObject data;

    public Forecast(Double latitude, Double longitude, String API_KEY){
        this.API_KEY = API_KEY;
//        added line of code below on 9/2/13
        if (latitude != 0 && longitude != 0) {
//        added line of code above on 9/2/13
            String forecastUrl = buildForecastUrl(latitude, longitude);
            new FetchDataAsync().execute(forecastUrl);
//        added line of code below on 9/2/13
        }
//        added line of code above on 9/2/13
    }

    private String buildForecastUrl(Double latitude, Double longitude) {
        Locale[] locales = DateFormat.getAvailableLocales();
        Locale locale = (locales.length > 0) ? locales[0] : Locale.US;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, locale);
//        changed line of code below on 9/2/13
        sdf.setTimeZone(TimeZone.getTimeZone(locale.toString()));//changed from "UTC" to locale.toString()
//        changed line of code above on 9/2/13
//        changed line of code below on 9/2/13, removing the following: + "," + sdf.format(new Date());
//       return API_URL + API_KEY + "/" + latitude.toString() + "," + longitude.toString() + "," + sdf.format(new Date());
        return API_URL + API_KEY + "/" + latitude.toString() + "," + longitude.toString();// + "," + sdf.format(new Date());
//        changed line of code above on 9/2/13, removing the following: + "," + sdf.format(new Date());
    }

    public HttpResponse getResponse() {
        return response;
    }

    public JSONObject getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}