package com.macinternetservices.sablebusinessdirectory;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class SplashScreen extends AppCompatActivity {

    // wait time
    private final int SPLASH_DISPLAY_LENGTH = 500; // 3 seconds

    String[] appPermissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE

    };

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        //setContentView(R.layout.activity_splash);

        if (checkAndRequestPermissions()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }

    final int PERMISSIONS_INTERNET = 100;
    final int PERMISSION_ACCESS_FINE_LOCATION = 200;
    final int PERMISSION_ACCESS_COARSE_LOCATION = 300;
    final int PERMISSION_ACCESS_BACKGROUND_LOCATION = 400;
    final int PERMISSION_FOREGROUND_SERVICE = 500;
    final int PERMISSION_CALL_PHONE = 600;
    final int PERMISSION_WRITE_EXTERNAL_STORAGE = 700;
    final int PERMISSION_READ_EXTERNAL_STORAGE = 800;
    final int PERMISSION_CAMERA = 900;
    final int PERMISSION_NETWORK = 900;
    final int PERMISSION_FOO = 1000;



    public boolean checkAndRequestPermissions() {

        //Chech which permissions are granged

        if (appPermissions != null) {
            for (String permission : appPermissions) {
                if (permission.equals(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    Intent startIntent = new Intent(this, PermissionRationaleActivity.class);
                    startActivity(startIntent);
                } else {
                    if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{permission},
                                PERMISSION_FOO);
                    }
                }
            }
        }
        return true;
      /*  List<String> listPermissionsNeeded = new ArrayList<>();
       for (String perm : appPermissions) {
            // else {
                if (ActivityCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                    if (perm.equals(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                        Intent startIntent = new Intent(this, PermissionRationaleActivity.class);
                        startActivity(startIntent);
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{perm},
                                perm);
                        requestPermissions(
                                new String[]{perm},
                                String[]{perm});

                    }
                }
           // }
            //return false;
        }
        //App has all premissions. Proceed ahead
        return true; */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_FOO) {
            HashMap<String, Integer> permissionResults = new HashMap<>();
            //int denideCount = 0;

            //gather permission grant results
           // for (int i = 0; i < grantResults.length; i++) {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                            //Ask se to geo to settings and manually allow permissions
                            showDialog("", "You have denied some permissions.  Allow all permissions at [Settings] > [Permissions]",
                                    "Go to Settings",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            //Go to app settings
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                    Uri.fromParts("package", getPackageName(), null));
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },
                                    "No, Exit app", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            ;
                                            fileList();
                                        }
                                    }, false);
                        }
                    }
                }
            //}
      //  }
    //}
    public AlertDialog showDialog(String title, String msg, String positiveLabel, DialogInterface.OnClickListener positiveOnClick,
                                  String negativeLabel, DialogInterface.OnClickListener negativeOnClick, boolean isCancelAble){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setCancelable(isCancelAble);
    builder.setMessage(msg);
    builder.setPositiveButton(positiveLabel, positiveOnClick);
    builder.setNegativeButton(negativeLabel, negativeOnClick);

    AlertDialog alert = builder.create();
    alert.show();
    return alert;
    }
}
