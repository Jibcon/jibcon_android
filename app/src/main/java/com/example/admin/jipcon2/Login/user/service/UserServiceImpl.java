package com.example.admin.jipcon2.Login.user.service;

import android.util.Log;
import com.example.admin.jipcon2.Login.user.domain.User;
import com.example.admin.jipcon2.Login.user.service.network.UserNetwork;
import com.example.admin.jipcon2.Login.user.service.network.UserNetworkImpl;


/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class UserServiceImpl implements UserService {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private static UserService mInstance;
    private UserNetwork mUserNetwork;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (mInstance == null){
            mInstance = new UserServiceImpl();
        }
        return mInstance;
    }

    private UserNetwork getUserNetwork(){
        if(mUserNetwork == null) {
            mUserNetwork = UserNetworkImpl.getInstance();
        }
        return mUserNetwork;
    }

    @Override
    public void getSampleUserAsynchronisely(onSuccessListener listener) {
        Log.d(TAG, "getSampleUserAsynchronisely: ");
        final onSuccessListener callbackListener = listener;
        getUserNetwork().getSampleUserInfoFromServerAsynchronisely(new UserNetwork.onSuccessListener() {
            @Override
            public void onSuccessGetSampleUserInfoFromServerAsynchronisely(User sampleUser) {
                Log.d(TAG, "getSampleUserAsynchronisely/onSuccessGetSampleUserInfoFromServerAsynchronisely: result is " + sampleUser.toString());
                callbackListener.onSuccessGetSampleUserAsynchronisely(sampleUser);
            }
        });
    }

    @Override
    public User getSampleUserSynchronisely() {
        return getUserNetwork().getSampleUserInfoFromServerSynchronisely();
    }
}
