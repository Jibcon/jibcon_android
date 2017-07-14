package com.sm_arts.jibcon.network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-04-10.
 */

public class Repo {
    public ApiService getService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.142.130/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        if(service!=null)
            Log.d("service","할당");
        else
            Log.d("service","실패");
        return service;
    }

    public static ApiService getStaticService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.142.130/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        if(service!=null)
            Log.d("service","할당");
        else
            Log.d("service","실패");
        return service;
    }

    public static MobiusService getMobiusService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.78.111.146:7579/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MobiusService service = retrofit.create(MobiusService.class);

        if(service != null)
            Log.d("service","할당");
        else
            Log.d("service","실패");

        return service;
    }
}
