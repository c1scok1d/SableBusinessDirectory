package com.macinternetservices.sablebusinessdirectory;

import com.google.android.gms.location.Geofence;

public class SimpleGeofence {
    private final String id;
    private final double latitude;
    private final double longitude;
    private final float radius;
    private long expirationDuration;
    private int transitionType;
    private int loiteringDelay = 60000;

    public SimpleGeofence(String geofenceId, double latitude, double longitude,
                          float radius, long expiration, int transition) {
        this.id = geofenceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.expirationDuration = expiration;
        this.transitionType = transition;
    }

    public Geofence toGeofence() {
        Geofence g = new Geofence.Builder().setRequestId(getId())
                .setTransitionTypes(transitionType)
                .setCircularRegion(getLatitude(), getLongitude(), getRadius())
                .setExpirationDuration(expirationDuration)
                .setLoiteringDelay(loiteringDelay).build();
        return g;
    }

    float getRadius() {
        return 0;
    }

    double getLongitude() {
        return 0;
    }

    String getId() {
        return null;
    }

    double getLatitude() {
        return 0;
    }
}