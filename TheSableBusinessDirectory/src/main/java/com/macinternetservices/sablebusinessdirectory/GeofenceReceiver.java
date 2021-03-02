package com.macinternetservices.sablebusinessdirectory;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.Date;
import java.util.List;

public class GeofenceReceiver extends IntentService {
    public static final int NOTIFICATION_ID = 1;

    public GeofenceReceiver() {
        super("GeofenceReceiver");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geoEvent = GeofencingEvent.fromIntent(intent);
        if (geoEvent.hasError()) {
            //Log.d("Geofence", "Error GeofenceReceiver.onHandleIntent");
        } else {
            //Log.d("Geofence", "GeofenceReceiver : Transition -> "
                   // + geoEvent.getGeofenceTransition());

            int transitionType = geoEvent.getGeofenceTransition();
            int i =0;

            if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER
                    || transitionType == Geofence.GEOFENCE_TRANSITION_DWELL
                    || transitionType == Geofence.GEOFENCE_TRANSITION_EXIT) {
                List<Geofence> triggerList = geoEvent.getTriggeringGeofences();

                for (Geofence geofence : triggerList) {
                    SimpleGeofence sg = SimpleGeofenceStore.getInstance()
                            .getSimpleGeofences().get(geofence.getRequestId());

                    String transitionName = "";
                    switch (transitionType) {
                        case Geofence.GEOFENCE_TRANSITION_DWELL:
                            transitionName = "dwell";
                            i++;
                            break;

                        case Geofence.GEOFENCE_TRANSITION_ENTER:
                            transitionName = "enter";
                            i++;
                            break;

                        case Geofence.GEOFENCE_TRANSITION_EXIT:
                            transitionName = "exit";
                            i++;
                            break;
                    }
                   /* String date = DateFormat.format("yyyy-MM-dd hh:mm:ss",
                            new Date()).toString();
                    EventDataSource eds = new EventDataSource(
                            getApplicationContext());
                    eds.create(transitionName, date, geofence.getRequestId());
                    eds.close();*/

                    GeofenceNotification geofenceNotification = new GeofenceNotification(
                            this);
                    geofenceNotification
                            .displayNotification(sg, transitionType);
                }
                if(i > 0){
                    Toast.makeText(getApplicationContext(), "There are " +i+ " black owned businesses within 5 miles of you", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(), "There are no black owned businesses near you", Toast.LENGTH_LONG).show();

                }
            }
        }
    }
}
