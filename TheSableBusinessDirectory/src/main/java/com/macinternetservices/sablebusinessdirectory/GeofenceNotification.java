package com.macinternetservices.sablebusinessdirectory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.Geofence;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class GeofenceNotification {
    public static final int NOTIFICATION_ID = 20;
    protected Context context;

    protected NotificationManager notificationManager;
    protected Notification notification;
    String notificationText = "";
    String notificationText2 = "";

    public GeofenceNotification(Context context) {
        this.context = context;


        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }
    protected void buildNotificaction(SimpleGeofence simpleGeofence,
                                      int transitionType, int near) {
            switch (transitionType) {
                case Geofence.GEOFENCE_TRANSITION_ENTER:
                    if(near > 0){
                        if(!MainActivity.isLoggedIn) {
                            notificationText = "Good news!";
                        } else {
                            notificationText = "Good news "+ MainActivity.firstName+"!";
                        }
                        notificationText2 = "There are " + near + " black owned businesses within 5 miles of you!";
                    } else {
                        if(!MainActivity.isLoggedIn) {
                            notificationText = "Oh no!";
                        } else {
                            notificationText = "Oh no "+ MainActivity.firstName+"!";
                        }
                        notificationText2 = "There are no businesses listed with us near you! Tap to add a listing now";
                    }
                    transitionEnterNotification(context, notificationText, notificationText2);
                    break;
                case Geofence.GEOFENCE_TRANSITION_DWELL:
                    notificationText = "You are near " + simpleGeofence.toGeofence().getRequestId();
                    notificationText2 = "Support black business.  Stop in and say 'Hi!'";
                    transitionDwellNotification(context, notificationText, notificationText2, simpleGeofence.getLogo());
                    break;

                case Geofence.GEOFENCE_TRANSITION_EXIT:
                    notificationText = "Support black business. You are near " + simpleGeofence.toGeofence().getRequestId();
                    notificationText2 = "Why not stop in and say 'Hi!'";
                    transitionEnterNotification(context, notificationText, notificationText2);
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
                                    int transitionType, int near) {
        //Geofence geo = simpleGeofence.toGeofence();
        buildNotificaction(simpleGeofence, transitionType, near);
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
    private void transitionEnterNotification(final Context mContext,final String message, final String message2){
        createNotificationChannel(mContext);
        Intent notificationIntent = new Intent(mContext, MainActivity.class);

        PendingIntent notificationTapIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSubText("Black Owned Business Alert")
                .setContentText(message2)
                .setContentTitle(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(notificationTapIntent) // notification tap action
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notifManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(new Random().nextInt(), notification);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    private void transitionDwellNotification(final Context mContext,final String message, final String message2, String url){
        createNotificationChannel(mContext);
        Intent notificationIntent = new Intent(mContext, MainActivity.class);

        PendingIntent notificationTapIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle(message2)
                .setContentText(message)
                .setSubText("Black Owned Business Alert")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(getBitmapFromURL(url))
                .setStyle(new NotificationCompat.BigPictureStyle()
                       .bigPicture(getBitmapFromURL(url))
                        .bigLargeIcon(null))
                .setContentIntent(notificationTapIntent) // notification tap action
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notifManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(new Random().nextInt(), notification);
    }
}
