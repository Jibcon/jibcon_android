package com.sm_arts.jibcon.utils.mobius;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestSubscription;
import com.sm_arts.jibcon.data.models.mobius.dto.response.ResponseSub;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusSubService;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-10-16.
 */

public class MobiusManager {
    private static final String TAG = MobiusManager.class.getName();
    private static MobiusManager obj = null;

    private MobiusManager() {

    }

    public void addSubscription(DeviceItem deviceItem) {
        MobiusSubService service = RetrofitClients.getInstance().getService(MobiusSubService.class);
        Call<ResponseSub> c =  service.addSub();
        c.enqueue(new Callback<ResponseSub>() {
            @Override
            public void onResponse(Call<ResponseSub> call, Response<ResponseSub> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<ResponseSub> call, Throwable t) {

            }
        });
    }

    public void deleteSubscription(DeviceItem deviceItem) {
        RequestSubscription requestSubscription;
        // TODO: 2017-10-16 requestSub에 정보 채우기 
        
        MobiusSubService service = RetrofitClients.getInstance().getService(MobiusSubService.class);
        Call<ResponseSub> c =  service.deleteSub();
        c.enqueue(new Callback<ResponseSub>() {
            @Override
            public void onResponse(Call<ResponseSub> call, Response<ResponseSub> response) {

            }

            @Override
            public void onFailure(Call<ResponseSub> call, Throwable t) {

            }
        });
    }

    public static MobiusManager getInstance() {
        if (obj == null) {
            obj = new MobiusManager();

        }
        return obj;
    }
}
