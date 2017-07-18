package com.sm_arts.jibcon.login.user.service.network;

import android.util.Log;

import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class UserNetworkImpl implements UserNetwork {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private static UserNetwork sInstance;
    private ApiService mApiService;


    private UserNetworkImpl() {
        mApiService = RetrofiClients.getInstance().getService(ApiService.class);
    }

    public static UserNetwork getInstance() {
        if (sInstance == null) {
            sInstance = new UserNetworkImpl();
        }

        return sInstance;
    }

    @Override
    public void getSampleUserInfoFromServerAsynchronisely(final onSuccessListener listener) {
        Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely: Call.enqueue");

        Call<User> c = mApiService.getSampleUser();
        try {
            c.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User result = response.body();
                    Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely/onResponse: "+ result.toString());
                    listener.onSuccessGetSampleUserInfoFromServerAsynchronisely(result);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely/onFailure: ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getSampleUserInfoFromServerSynchronisely() {
        Log.d(TAG, "getSampleUserInfoFromServerSynchronisely: Call.execute");

        Call<User> c = mApiService.getSampleUser();
        try {
            User result = c.execute().body();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
