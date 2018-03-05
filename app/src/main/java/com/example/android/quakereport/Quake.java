package com.example.android.quakereport;

/**
 * Created by Mohammed on 1/30/2018.
 */

public class Quake {
    private double mag;
    private String location;
    private Long time; // for milliSecond time
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Quake(double mag, String location, Long time , String url) {
        this.mag = mag;
        this.location = location;
        this.time = time;
        this.url = url;

    }

    public double getMag() {
        return mag;
    }

    public String getLocation() {
        return location;
    }

    public Long getTime() {
        return time;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
