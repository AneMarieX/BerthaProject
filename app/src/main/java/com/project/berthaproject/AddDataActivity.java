package com.project.berthaproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;


import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Application application = getApplication();
        Geocoder geocoder = new Geocoder(application);
        int maxResults = 4;
    }

    public void addData2(View view) {

        String latitudeString = ((EditText) findViewById(R.id.add_data_latitude_edittext)).getText().toString();
        int latitude  = Integer.parseInt(latitudeString);
        String longitudeString = ((EditText) findViewById(R.id.add_data_longitude_edittext)).getText().toString();
        int longitude  = Integer.parseInt(longitudeString);
        /*String pm25String = ((EditText) findViewById(R.id.add_data_pm25_edittext)).getText().toString();
        double pm25 = Double.parseDouble(pm25String);
        String pm10String = ((EditText) findViewById(R.id.add_data_pm10_edittext)).getText().toString();
        double pm10 = Double.parseDouble(pm10String);
        String co2String = ((EditText) findViewById(R.id.add_data_co2_edittext)).getText().toString();
        double co2 = Double.parseDouble(co2String);
        String o3String = ((EditText) findViewById(R.id.add_data_o3_edittext)).getText().toString();
        double o3 = Double.parseDouble(o3String);
        String pressure = ((EditText) findViewById(R.id.add_data_pressure_edittext)).getText().toString();
        String temperature = ((EditText) findViewById(R.id.add_data_temperature_edittext)).getText().toString();
        String humidity = ((EditText) findViewById(R.id.add_data_humidity_edittext)).getText().toString();*/



        //String jsonDocument =
        //        "{\"Pm25\":\"" + pm25 + "\", \"Pm10\":\"" + pm10 + "\", \"Co2\":" + co2 + \"Pressure\":" + pressure+ \"Temperature\":" + temperature + \"Humidity\":" + humidity"}";

        TextView messageView = findViewById(R.id.add_data_message_textview);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Latitude", latitude);
            jsonObject.put("Longitude", longitude);
           /* jsonObject.put("Pm25", pm25);
            jsonObject.put("Pm10", pm10);
            jsonObject.put("Co2", co2);
            jsonObject.put("O3", o3);
            jsonObject.put("Pressure", pressure);
            jsonObject.put("Temperature", temperature);
            jsonObject.put("Humidity", humidity);*/
            String jsonDocument = jsonObject.toString();
            messageView.setText(jsonDocument);
            PostDataTask task = new PostDataTask();
            task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data ", jsonDocument);
        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }

    }
    private class PostDataTask extends AsyncTask<String, Void, CharSequence> {


        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocument = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocument);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("DATA", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("DATA", ex.getMessage());
                return ex.getMessage();
            }

        }

        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            TextView messageView = findViewById(R.id.add_data_message_textview);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.add_data_message_textview);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }
    }

}
