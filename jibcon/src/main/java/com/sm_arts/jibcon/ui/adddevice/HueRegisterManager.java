package com.sm_arts.jibcon.ui.adddevice;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionReq;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionRes;
import com.sm_arts.jibcon.utils.consts.UrlUtils;
import com.sm_arts.jibcon.utils.network.GsonUtils;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-11-12.
 */

public class HueRegisterManager {
    private static HashMap<String, HueBulb> bulbMap;
    public String internalAddress = "http://";
    public String internalUsername;

    private static final String TAG = HueRegisterManager.class.getName();
    public static HueRegisterManager obj = null;

    public static HueRegisterManager getInstance() {
        if (obj == null) {
            synchronized (HueRegisterManager.class) {
                if (obj == null) {
                    bulbMap = new HashMap<>();
                    obj = new HueRegisterManager();
                }

            }
        }
        return obj;
    }


    public void getInternalAddress() {

        InternalAddressService service = RetrofitClients.getInstance().getService(InternalAddressService.class);
        Call<List<Hub>> c = service.getInternalAddress();
        HashMap hashmap = new HashMap();
        hashmap.keySet();
        c.enqueue(new Callback<List<Hub>>() {
            @Override
            public void onResponse(Call<List<Hub>> call, Response<List<Hub>> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body().size() == 0 )
                {
                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(),"Philips Hue와 같은 Wifi를 선택해 주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "onResponse: " + response.body().get(0).internalipaddress);
                UrlUtils.setUrls("internalAddress", response.body().get(0).internalipaddress);
                internalAddress +=response.body().get(0).internalipaddress;

                getInternalUsername();

            }

            @Override
            public void onFailure(Call<List<Hub>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public  void getInternalUsername() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(internalAddress)

                // mentoring
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                .build();
        Hue_Internal service = client.create(Hue_Internal.class);

        Call<List<ConnectionRes>> c = service.getHueUserName(new ConnectionReq("my_hue_app"));
        c.enqueue(new Callback<List<ConnectionRes>>() {
            @Override
            public void onResponse(Call<List<ConnectionRes>> call, Response<List<ConnectionRes>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "response error", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().get(0).error != null && !TextUtils.isEmpty(response.body().get(0).error.type)) {
                    Log.d(TAG, "onResponse: " + response.body().get(0).error.toString());
                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "링크 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onResponse: " + response.body().get(0).success.username);
                    internalUsername = response.body().get(0).success.username;

                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Success" + response.body().get(0).success.username, Toast.LENGTH_SHORT).show();
                    getLights();
                }

            }

            @Override
            public void onFailure(Call<List<ConnectionRes>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    public  void getLights() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(internalAddress)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        Hue_Internal service = client.create(Hue_Internal.class);


        Call<HashMap<String, Object>> c = service.getLights(internalUsername);
        c.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                HashMap<String, Object> result = response.body();
                Log.d(TAG, "onResponse: ");
                for (String key :
                        result.keySet()) {
                    Object item = result.get(key);
                    Log.d(TAG, "onResponse: item=" + item.toString());
                    HueBulb hueBulb = ConvertUtils.convertHueBulb((LinkedTreeMap<String, Object>) item);
                    hueBulb.ID = key;
                    bulbMap.put(hueBulb.name, hueBulb);
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public static void changeBulbState(String id, boolean b) {
        HueBulb hueBulb = bulbMap.get(id);
        Hue_Internal hueInternal = RetrofitClients.getInstance().getService(Hue_Internal.class);
        ConnectionReq connectionReq = new ConnectionReq();
        connectionReq.on = true;
        Call<List<ConnectionRes>> c = hueInternal.changeBulbState(hueBulb.internalUsername, hueBulb.ID, connectionReq);
        c.enqueue(new Callback<List<ConnectionRes>>() {
            @Override
            public void onResponse(Call<List<ConnectionRes>> call, Response<List<ConnectionRes>> response) {
                Log.d(TAG, "onResponse: turnOnRestroom");
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<ConnectionRes>> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: turnOnRestroom");

            }
        });
    }

    public static void sendLights() {
        HueBulb hueBulb;
        List<HueBulb> bulbsList = new ArrayList<>();

        for (String key :
                bulbMap.keySet()) {
            hueBulb = bulbMap.get(key);
            bulbsList.add(hueBulb);

        }
        Hue_Server hueServer = RetrofitClients.getInstance().getService(Hue_Server.class);
        Call<Void> c = hueServer.sendLights(bulbsList);
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
}
