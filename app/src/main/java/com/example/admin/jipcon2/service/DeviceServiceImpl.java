package com.example.admin.jipcon2.service;

import android.util.Log;
import android.widget.Toast;

import com.example.admin.jipcon2.Device.DeviceItem;
import com.example.admin.jipcon2.network.ApiService;
import com.example.admin.jipcon2.network.repo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class DeviceServiceImpl implements DeviceService {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private List<DeviceItem> deviceItems;//device 메뉴 아이템들의 리스트
    private static DeviceService mInstance;
    private DeviceNetwork mDeviceNetwork;
    private boolean wasChanged = false;

    private DeviceServiceImpl() {
        this.deviceItems = new ArrayList<>();
    }

    public static DeviceService getInstance() {
        if (mInstance == null){
            mInstance = new DeviceServiceImpl();
        }
        return mInstance;
    }

    @Override
    public void getDeviceItems(onSuccessListener listener) {
        Log.d(TAG, "getDeviceItems: wasChanged is "+wasChanged +" deviceItems.size is "+deviceItems.size());
        if (deviceItems.size() == 0 | wasChanged) {
            Log.d(TAG, "getDeviceItems: reload");
            wasChanged = false;
            reloadDeviceItems(listener);
        } else {
            Log.d(TAG, "getDeviceItems: don't reload");
            listener.onSuccessGetDeviceItems(deviceItems);
        }
    }

    @Override
    public List<DeviceItem> getDeviceItems() {
        return deviceItems;
    }

    @Override
    public void prepareDeviceItems() {
        getDeviceNetwork().getDeviceItemsFromServer(new DeviceNetwork.onSuccessListener() {
            @Override
            public void onSuccessGetDeviceItemsFromServer(List<DeviceItem> deviceItems) {
                Log.d(TAG, "onSuccessGetDeviceItemsFromServer: ");
                setDeviceItems(deviceItems);
            }
        });
    }

    private DeviceNetwork getDeviceNetwork(){
        if(mDeviceNetwork == null) {
            mDeviceNetwork = DeviceNetworkImpl.getInstance();
        }
        return mDeviceNetwork;
    }

    private void setDeviceItems(List<DeviceItem> deviceItems) {
        this.deviceItems = deviceItems;
    }

    @Override
    public void reloadDeviceItems(onSuccessListener listener) {
        Log.d(TAG, "reloadDeviceItems: ");
        final onSuccessListener callbackListener = listener;
        getDeviceNetwork().getDeviceItemsFromServer(new DeviceNetwork.onSuccessListener() {
            @Override
            public void onSuccessGetDeviceItemsFromServer(List<DeviceItem> deviceItems) {
                Log.d(TAG, "reloadDeviceItems/onSuccessGetDeviceItemsFromServer: setDeviceItems "+deviceItems.toString());
                setDeviceItems(deviceItems);
                callbackListener.onSuccessGetDeviceItems(deviceItems);
            }
        });
    }

    @Override
    public void notifyDeviceItemsChanged() {
        Log.d(TAG, "notifyDeviceItemsChanged: ");
        wasChanged = true;
    }
}
