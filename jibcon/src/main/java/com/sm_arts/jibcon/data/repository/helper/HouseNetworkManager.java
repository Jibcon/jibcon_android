package com.sm_arts.jibcon.data.repository.helper;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.HouseInfo;
import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.repository.network.api.HouseService;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-11-30.
 */

public class HouseNetworkManager {

    private static final String TAG = "HouseNetworkManager";
    private static HouseNetworkManager sobj = null;

    public static HouseNetworkManager getInstance() {
        if (sobj == null) {
            synchronized (HouseNetworkManager.class) {
                if (sobj == null)
                    sobj = new HouseNetworkManager();

            }

        }
        return sobj;
    }

    public void addHouse(HouseInfo houseInfo) {

        HouseService service = RetrofitClients.getInstance().getService(HouseService.class);
        Call<HouseInfo> c = service.addHouse(JibconLoginManager.getInstance().getUserId(),
                houseInfo);
        c.enqueue(new Callback<HouseInfo>() {
            @Override
            public void onResponse(Call<HouseInfo> call, Response<HouseInfo> response) {
                Log.d(TAG, "onResponse: ");
                HouseInfo result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                    JibconHouseManager.getInstance().addNewHouse(result);
                    JibconHouseManager.getInstance().setmCurrentHouse(result);

                }
            }

            @Override
            public void onFailure(Call<HouseInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void getMyHouse() {
        HouseService service = RetrofitClients.getInstance().getService(HouseService.class);
        Call<List<HouseInfo>> c = service.getMyHouses(JibconLoginManager.getInstance().getUserId());
        c.enqueue(new Callback<List<HouseInfo>>() {
            @Override
            public void onResponse(Call<List<HouseInfo>> call, Response<List<HouseInfo>> response) {
                Log.d(TAG, "onResponse: ");
                List<HouseInfo> result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                    JibconHouseManager.getInstance().setMyHouseList(result);

                }

            }

            @Override
            public void onFailure(Call<List<HouseInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void changeCurrentHouse(HouseInfo houseInfo) {
        HouseService service = RetrofitClients.getInstance().getService(HouseService.class);
        Call<User> c = service.changeCurrentHouse(JibconLoginManager.getInstance().getUserId(),
                                                    houseInfo.house_id);
        c.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void getCurrentHouse() {
        HouseService service = RetrofitClients.getInstance().getService(HouseService.class);
        Call<HouseInfo> c = service.getCurrentHouse(JibconLoginManager.getInstance().getCurrentHouseId());
        c.enqueue(new Callback<HouseInfo>() {
            @Override
            public void onResponse(Call<HouseInfo> call, Response<HouseInfo> response) {
                HouseInfo result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                    JibconHouseManager.getInstance().setmCurrentHouse(result);
                }
                JibconHouseManager.getInstance().setmCurrentHouse(result);
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<HouseInfo> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: ");
            }
        });

    }

}
