package com.macinternetservices.sablebusinessdirectory;

import android.app.IntentService;
import android.content.Intent;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.Date;
import java.util.List;

import static com.macinternetservices.sablebusinessdirectory.MainActivity.accessToken;
import static com.macinternetservices.sablebusinessdirectory.MainActivity.firstName;
import static com.macinternetservices.sablebusinessdirectory.MainActivity.isLoggedIn;
import static com.macinternetservices.sablebusinessdirectory.MainActivity.ivLoading;
import static com.macinternetservices.sablebusinessdirectory.MainActivity.tvLoading;

public class GeofenceReceiver extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    public static int near =0;

    public GeofenceReceiver() {
        super("GeofenceReceiver");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geoEvent = GeofencingEvent.fromIntent(intent);
        if (geoEvent.hasError()) {
        } else {
            //Log.d("Geofence", "GeofenceReceiver : Transition -> "
                   // + geoEvent.getGeofenceTransition());

            int transitionType = geoEvent.getGeofenceTransition();

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
                            near++;
                            break;

                        case Geofence.GEOFENCE_TRANSITION_ENTER:
                            transitionName = "enter";
                            near++;
                            break;

                        case Geofence.GEOFENCE_TRANSITION_EXIT:
                            transitionName = "exit";
                            near++;
                            break;
                    }

                    GeofenceNotification geofenceNotification = new GeofenceNotification(
                            this);
                    geofenceNotification
                            .displayNotification(sg, transitionType);
                }
                if(near > 0){
                    /*Animation imgAnimationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

                    ivLoading.setVisibility(View.VISIBLE);
                    ivLoading.setAnimation(imgAnimationIn);
                    tvLoading.setVisibility(View.VISIBLE);
                    tvLoading.setAnimation(imgAnimationIn);
                    isLoggedIn = accessToken != null && !accessToken.isExpired();
                    if(isLoggedIn && firstName != null) {
                        String name = "<font color='#4FC1E9'>" +firstName+"</font>";
                        tvLoading.setText(Html.fromHtml(("Thanks for your patience " + name + "<br>There are " +near+ " black owned businesses within 5 miles of you")));
                    } else {
                        tvLoading.setText("Thank you for waiting while we search our directory for black owned businesses near you.");
                    } */
                    Toast.makeText(getApplicationContext(), "There are " +near+ " black owned businesses within 5 miles of you", Toast.LENGTH_LONG).show();
                    //MainActivity.tvLoading.setText("It looks like there are " +near+ " black owned businesses within 5 miles of you");
                } else{
                    Toast.makeText(getApplicationContext(), "There are no black owned businesses near you", Toast.LENGTH_LONG).show();

                }
            }
        }
    }
}
