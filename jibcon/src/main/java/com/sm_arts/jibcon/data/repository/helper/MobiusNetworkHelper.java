package com.sm_arts.jibcon.data.repository.helper;

import android.util.Log;

import com.sm_arts.jibcon.data.models.mobius.dto.RequestAe;
import com.sm_arts.jibcon.data.models.mobius.dto.RequestSub;
import com.sm_arts.jibcon.data.models.mobius.dto.ResponseAe;
import com.sm_arts.jibcon.data.models.mobius.dto.ResponseSub;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusAeService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusSubService;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.consts.UrlUtils;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 8/2/17.
 */

public class MobiusNetworkHelper {
    private static final String TAG = "MobiusNetworkHelper";

    private static MobiusNetworkHelper sInstance;
    private int mRequestNumber = 0;

    public static MobiusNetworkHelper getInstance() {
        if (sInstance == null) {
            synchronized (MobiusNetworkHelper.class) {
                if (sInstance == null) {
                    sInstance = new MobiusNetworkHelper();
                }
            }
        }

        return sInstance;
    }

    public void createAe(Consumer<ResponseAe> finished) {
        MobiusAeService service = RetrofiClients.getInstance().getService(MobiusAeService.class);

        RequestAe requestAe = new RequestAe();
        requestAe.m2mae.rn = Configs.AE.Name;
        requestAe.m2mae.api = Configs.AE.Aid;
        requestAe.m2mae.rr = true;

        Call<ResponseAe> call = service.postAe(
                Configs.CSE.Name,
                "application/json",
                requestIdGenerate(),
                Configs.AE.Aid,
                "application/vnd.onem2m-res+json; ty=2",
                requestAe
        );

        call.enqueue(new Callback<ResponseAe>() {
            @Override
            public void onResponse(Call<ResponseAe> call, Response<ResponseAe> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "createAe/onResponse: code=[" + response.code() + "]");
                    Log.d(TAG, "createAe/onResponse: body = [" + response.body() + "]");
                    try {
                        finished.accept(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "createAe/onResponse: code=[" + response.code() + "] message=[" + response.message()+ "]");
                    try {
                        finished.accept(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseAe> call, Throwable t) {
                Log.d(TAG, "createAe/onFailure: " + t);
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void retrieveAe(Consumer<ResponseAe> finished) {
        MobiusAeService service = RetrofiClients.getInstance().getService(MobiusAeService.class);

        RequestAe requestAe = new RequestAe();
        requestAe.m2mae.rn = Configs.AE.Name;
        requestAe.m2mae.api = Configs.AE.Aid;
        requestAe.m2mae.rr = true;

        Call<ResponseAe> call = service.getAe(
                Configs.CSE.Name,
                Configs.AE.Name,
                "application/json",
                requestIdGenerate(),
                Configs.AE.Aid
        );

        call.enqueue(new Callback<ResponseAe>() {
            @Override
            public void onResponse(Call<ResponseAe> call, Response<ResponseAe> response) {
                Log.d(TAG, "retrieveAe/onResponse: response.code()=[" + response.code() + "]");
                Log.d(TAG, "retrieveAe/onResponse: body = [" + response.body() + "]");
                try {
                    finished.accept(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseAe> call, Throwable t) {
                Log.d(TAG, "retrieveAe/onFailure: " + t);
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createSub(String deviceAe, String deviceCnt, Consumer<ResponseSub> finished) {
        MobiusSubService service = RetrofiClients.getInstance().getService(MobiusSubService.class);

        RequestSub requestSub = new RequestSub();
        requestSub.m2msub.rn = getDeviceSub();
        requestSub.m2msub.enc.net.add(1);
        requestSub.m2msub.enc.net.add(3);
        requestSub.m2msub.enc.net.add(4);
        requestSub.m2msub.nu.add(
                "mqtt://" + Configs.Mobius.Host + "/" + requestSub.m2msub.rn
        );

        Call<ResponseSub> call = service.postSub(
                Configs.CSE.Name,
                deviceAe,
                deviceCnt,
                "application/json",
                requestIdGenerate(),
                Configs.AE.Aid,
                "application/vnd.onem2m-res+json; ty=23",
                requestSub
        );

        call.enqueue(new Callback<ResponseSub>() {
            @Override
            public void onResponse(Call<ResponseSub> call, Response<ResponseSub> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "createSub/onResponse: code=[" + response.code() + "]");
                    Log.d(TAG, "createSub/onResponse: body = [" + response.body() + "]");
                    try {
                        finished.accept(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "createSub/onResponse: code=[" + response.code() + "] message=[" + response.message()+ "]");
                    try {
                        finished.accept(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseSub> call, Throwable t) {
                Log.d(TAG, "createSub/onFailure: " + t);
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void retrieveSub(String deviceAe, String deviceCnt, Consumer<ResponseSub> finished) {
        MobiusSubService service = RetrofiClients.getInstance().getService(MobiusSubService.class);

        String deviceSub = getDeviceSub();
        Call<ResponseSub> call = service.getSub(
                Configs.CSE.Name,
                deviceAe,
                deviceCnt,
                deviceSub,
                "application/json",
                requestIdGenerate(),
                Configs.AE.Aid
        );

        call.enqueue(new Callback<ResponseSub>() {
            @Override
            public void onResponse(Call<ResponseSub> call, Response<ResponseSub> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "retrieveSub/onResponse: code=[" + response.code() + "]");
                    Log.d(TAG, "retrieveSub/onResponse: body = [" + response.body() + "]");
                    try {
                        finished.accept(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "retrieveSub/onResponse: code=[" + response.code() + "] message=[" + response.message()+ "]");
                    try {
                        finished.accept(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSub> call, Throwable t) {
                Log.d(TAG, "retrieveSub/onFailure: " + t);
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getDeviceSub() {
        return Configs.AE.Name + "_sub";
    }

    private String requestIdGenerate() {
        String requestId = "req" + mRequestNumber;
        mRequestNumber++;

        return requestId;
    }
}
