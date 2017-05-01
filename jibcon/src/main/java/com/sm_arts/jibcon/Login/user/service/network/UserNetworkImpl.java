package com.sm_arts.jibcon.Login.user.service.network;

import android.util.Log;

import com.sm_arts.jibcon.Login.user.domain.User;
import com.sm_arts.jibcon.network.repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class UserNetworkImpl implements UserNetwork {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private static UserNetwork mInstance;

    private UserNetworkImpl() {
    }


    public static UserNetwork getInstance() {
        if (mInstance == null){
            mInstance = new UserNetworkImpl();
        }
        return mInstance;
    }

    @Override
    public void getSampleUserInfoFromServerAsynchronisely(final onSuccessListener listener) {
        Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely: Call.enqueue");

        Call<User> c = repo.getStaticService().getSampleUser();
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

        Call<User> c = repo.getStaticService().getSampleUser();
        try {
            User result = c.execute().body();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
