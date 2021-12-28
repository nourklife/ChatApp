package com.example.firstapp;

public class MapesDesc {
    double latitude;
    double longitude;
    String tital;
    String snaptuite;
    int iconRsID;

    public MapesDesc(double latitude, double longitude, String tital, String snaptuite, int iconRsID) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tital = tital;
        this.snaptuite = snaptuite;
        this.iconRsID = iconRsID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTital() {
        return tital;
    }

    public String getSnaptuite() {
        return snaptuite;
    }

    public int getIconRsID() {
        return iconRsID;
    }
}
