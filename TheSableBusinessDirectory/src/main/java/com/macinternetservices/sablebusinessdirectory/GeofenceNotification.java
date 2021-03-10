package com.macinternetservices.sablebusinessdirectory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.Geofence;

import java.util.Random;

public class GeofenceNotification {
    public static final int NOTIFICATION_ID = 20;

    protected Context context;

    protected NotificationManager notificationManager;
    protected Notification notification;

    public GeofenceNotification(Context context) {
        this.context = context;

        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }
    protected void buildNotificaction(SimpleGeofence simpleGeofence,
                                      int transitionType) {
        Object[] notificationTextParams = new Object[] { simpleGeofence.getId() };
        String notificationText = "";
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                notificationText = "You are near " +simpleGeofence.getId();
                transitionDwellNotification(context, notificationText);
                break;

            case Geofence.GEOFENCE_TRANSITION_ENTER:
                notificationText = "You are 5 miles away from " +simpleGeofence.getId();
                transitionEnterNotification(context, notificationText);
                break;

            case Geofence.GEOFENCE_TRANSITION_EXIT:
                notificationText = String.format(
                        context.getString(R.string.geofence_exit),
                        notificationTextParams);
                break;
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.mipmap.sable_logo_black)
                .setContentTitle(context.getString(R.string.app_name))
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                                .bigText(notificationText)).setAutoCancel(true);

        notification = notificationBuilder.build();
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
    }

    public void displayNotification(SimpleGeofence simpleGeofence,
                                    int transitionType) {
        buildNotificaction(simpleGeofence, transitionType);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
    public static final String CHANNEL_ID = "Transition Channel";
    private void createNotificationChannel(final Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Transition Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = mContext.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    private void transitionEnterNotification(final Context mContext,final String message){
        createNotificationChannel(mContext);
        Intent notificationIntent = new Intent(mContext, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle("Black Owned Business Alert")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true) // set this to show and vibrate only once
                .build();
        NotificationManager notifManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(new Random().nextInt(), notification);
    }

    private void transitionDwellNotification(final Context mContext,final String message){
        createNotificationChannel(mContext);
        Intent notificationIntent = new Intent(mContext, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle("Black Owned Business Alert")
                .setContentText(message)
                .setSmallIcon(R.mipmap.sable_logo_black)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true) // set this to show and vibrate only once
                .build();
        NotificationManager notifManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(new Random().nextInt(), notification);
    }
}
