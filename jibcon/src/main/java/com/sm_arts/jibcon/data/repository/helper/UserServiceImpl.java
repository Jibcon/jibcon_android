package com.sm_arts.jibcon.data.repository.helper;

import android.util.Log;
import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.repository.helper.network.UserNetwork;
import com.sm_arts.jibcon.data.repository.helper.network.UserNetworkImpl;


/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class UserServiceImpl implements UserService {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private static UserService sInstance;
    private UserNetwork mUserNetwork;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (sInstance == null) {
            synchronized(UserService.class) {
                if (sInstance == null) {
                    sInstance = new UserServiceImpl();
                }
            }
        }
        return sInstance;
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
