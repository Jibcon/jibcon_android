package com.sm_arts.jibcon.device.service;

import android.util.Log;

import com.sm_arts.jibcon.data.models.DeviceItem;
import com.sm_arts.jibcon.device.service.network.DeviceNetwork;
import com.sm_arts.jibcon.device.service.network.DeviceNetworkImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class DeviceServiceImpl implements DeviceService {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private List<DeviceItem> mDeviceItems;//device 메뉴 아이템들의 리스트
    private static DeviceService sInstance;
    private DeviceNetwork mDeviceNetwork;
    private boolean mWasChanged = false;

    private DeviceServiceImpl() {
        this.mDeviceItems = new ArrayList<>();
    }

    public static DeviceService getInstance() {
        if (sInstance == null) {
            synchronized(DeviceService.class) {
                if (sInstance == null) {
                    sInstance = new DeviceServiceImpl();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getDeviceItems(onSuccessListener listener) {
        Log.d(TAG, "getDeviceItems: wasChanged is "+mWasChanged +" deviceItems.size is "+mDeviceItems.size());
        if (mDeviceItems.size() == 0 | mWasChanged) {
            Log.d(TAG, "getDeviceItems: reload");
            mWasChanged = false;
            reloadDeviceItems(listener);
        } else {
            Log.d(TAG, "getDeviceItems: don't reload");
            listener.onSuccessGetDeviceItems(mDeviceItems);
        }
    }

    @Override
    public List<DeviceItem> getDeviceItems() {
        return mDeviceItems;
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
        this.mDeviceItems = deviceItems;
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
        mWasChanged = true;
    }
}
