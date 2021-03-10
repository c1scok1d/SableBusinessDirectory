package com.macinternetservices.sablebusinessdirectory;

import com.google.android.gms.location.Geofence;

public class SimpleGeofence {
    private final String id;
    private final String logo;
    private final double latitude;
    private final double longitude;
    private final float radius;
    private long expirationDuration;
    private int transitionType;
    private int loiteringDelay = 60000;

    public SimpleGeofence(String geofenceId, double latitude, double longitude,
                          float radius, long expiration, String logo, int transition) {
        this.id = geofenceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.expirationDuration = expiration;
        this.transitionType = transition;
        this.logo = logo;
    }

    public Geofence toGeofence() {
        Geofence g = new Geofence.Builder()
                .setExpirationDuration(expirationDuration)
                .setRequestId(id)
                .setLoiteringDelay(loiteringDelay)
                .setTransitionTypes(transitionType)
                .setCircularRegion(latitude,longitude,radius)
                .build();
        return g;
    }

    float getRadius() {
        return radius;
    }

    double getLongitude() {
        return longitude;
    }

    String getId() {
        return id;
    }

    double getLatitude() {
        return latitude;
    }

    String getLogo(){return logo;}
}