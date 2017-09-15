package com.sm_arts.jibcon.utils.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by admin on 2017-09-15.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d("Notification", "onTokenRefresh");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Notification",token);
    }
}
