package com.project.berthaproject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable  {

    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("pm25")
    private double pm25;
    @SerializedName("pm10")
    private double pm10;
    @SerializedName("co2")
    private double co2;
    @SerializedName("o3")
    private double o3;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("temperature")
    private String temperature;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("userId")
    private String userId;

    public Data() {
    }

    public Data(int deviceId, double pm25, double pm10, double co2, double o3, String pressure, String temperature, String humidity, String userId) {
        this.deviceId = deviceId;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.co2 = co2;
        this.o3 = o3;
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;
        this.userId = userId;

    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public int getDeviceId() {
        return deviceId;
    }


    public double getPm25() {
        return pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public double getCo2() {
        return co2;
    }

    public double getO3() {
        return o3;
    }

    public String getPressure() {
        return pressure;
    }


    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "deviceId='" + deviceId+ '\'' +
                '}';
    }
}
