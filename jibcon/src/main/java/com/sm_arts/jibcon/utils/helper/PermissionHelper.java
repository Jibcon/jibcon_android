package com.sm_arts.jibcon.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class PermissionHelper {
    private static final String TAG = "PermissionHelper";

    public static final int ACCESSCOARSELOCATION_REQUEST_CODE = 123;

    public static void chkPermissions(List<String> permissions,
                                     Object activityOrFragment,
                                      int requestCode) {

        chkPermissions(permissions, activityOrFragment, requestCode, () -> {});
    }

    public static void chkPermissions(List<String> permissions,
                                      Object activityOrFragment,
                                      int requestCode,
                                      Action onAlreadyGranted) {

        List<String> requestPermissions = new ArrayList<>();
        Context context = null;
        Activity activity = null;
        Fragment fragment = null;

        if (activityOrFragment instanceof Activity) {
            activity = (Activity) activityOrFragment;
            context = activity;
        } else if (activityOrFragment instanceof Fragment ){
            fragment = (Fragment) activityOrFragment;
            context = fragment.getContext();
        }


        for (String permission :
                permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "chkPermissions: " + permission + " Already Granted.");
            } else {
                requestPermissions.add(permission);
            }
        }

        if (!requestPermissions.isEmpty()) {
            if (activity != null) {
                ActivityCompat.requestPermissions(activity,
                        requestPermissions.toArray(new String[0]), requestCode);
            } else if (fragment != null) {
                fragment.requestPermissions(
                        requestPermissions.toArray(new String[0]), requestCode);
            }
        } else {
            try {
                onAlreadyGranted.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void chkPermission(String permission,
                                     Object activityOrFragment,
                                     int requestCode) {

        chkPermission(permission, activityOrFragment, requestCode, () -> {});
    }

    public static void chkPermission(String permission,
                                     Object activityOrFragment,
                                     int requestCode,
                                     Action onAlreadyGranted) {

        List<String> permissions = Arrays.asList(permission);
        chkPermissions(permissions, activityOrFragment, requestCode, onAlreadyGranted);
    }
}
