package com.sm_arts.jibcon.utils.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by admin on 2017-09-15.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = FirebaseInstanceIDService.class.getName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG, "onTokenRefresh");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,token);
    }
}
