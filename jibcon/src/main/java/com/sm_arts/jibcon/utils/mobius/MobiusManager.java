package com.sm_arts.jibcon.utils.mobius;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestSubscription;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusSubService;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
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
        Log.d(TAG, "addSubscription: ");
        RequestSubscription requestSubscription = new RequestSubscription();
        // TODO: 2017-10-16 requestSub에 정보 채우기
        JibconLoginManager.getInstance().getUserFcmToken();
        requestSubscription.setInfo(
                deviceItem.getAeName(),
                deviceItem.getCntName(),
                Configs.SubName.subName,
                JibconLoginManager.getInstance().getUserFcmToken(),
                Configs.SubscriptionTopic.topic

        );


        MobiusSubService service = RetrofitClients.getInstance().getService(MobiusSubService.class);
        Call<Void> c = service.addSub(requestSubscription);

        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteSubscription(DeviceItem deviceItem) {
        RequestSubscription requestSubscription = new RequestSubscription();
        // TODO: 2017-10-16 requestSub에 정보 채우기
        JibconLoginManager.getInstance().getUserFcmToken();
        requestSubscription.setInfo(
                deviceItem.getAeName(),
                deviceItem.getCntName()+"_res",
                Configs.SubName.subName,
                JibconLoginManager.getInstance().getUserFcmToken(),
                Configs.SubscriptionTopic.topic

        );


        MobiusSubService service = RetrofitClients.getInstance().getService(MobiusSubService.class);
        Call<Void> c = service.deleteSub(requestSubscription);
        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
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
