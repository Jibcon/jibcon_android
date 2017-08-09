package com.sm_arts.jibcon.ui.main.devicemenu.fragment;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.ui.main.devicemenu.adapter.DeviceMenuAdapter;
import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;
import com.sm_arts.jibcon.utils.mqtt.MqttManager;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 7/21/17.
 */

class DeviceMenuPresenter {
    private static final String TAG = "DeviceMenuPresenter";
    private final DeviceMenuView mView;
    private DeviceMenuAdapter mAdapter;

    public DeviceMenuPresenter(DeviceMenuView view) {
        Log.d(TAG, "DeviceMenuPresenter: ");
        mView = view;
        attachMqttListener();
    }

    private void attachMqttListener() {
        Log.d(TAG, "attachMqttListener: ");
        MqttManager.getInstance().setListener(
                this::receiveMqttNotification
        );
    }

    //region Presenter role

    private void receiveMqttNotification(MqttSurCon mqttSurCon) {
        Log.d(TAG, "receiveMqttNotification() called with: mqttSurCon = [" + mqttSurCon.toString() + "]");
        DeviceMenuAdapter adapter = mView.getAdapter();
        List<Integer> positions = adapter.findDeviceItemPositionsWithSur(mqttSurCon.getSur());
        if (positions.size() > 0) {
            for (Integer position :
                    positions) {
                mView.showContent(position, mqttSurCon.getCon());
            }
        } else {
            Log.w(TAG, "receiveMqttNotification: cannot find mqttsur, sur = [" + mqttSurCon.getSur() + "]");
        }
    }

    public void loadData(Consumer<List<DeviceItem>> finished) {
        Log.d(TAG, "loadData: ");
        DeviceNetworkHelper.getInstance()
                .getDevices(finished);
    }

    public void deviceItemIvClicked(DeviceItem item) {
        Log.d(TAG, "deviceItemIvClicked() called with: item = [" + item + "]");

    }

    public void threedotIvClicked(int position) {
        Log.d(TAG, "threedotIvClicked() called with: " +
                "position = [" + position + "]");
        mView.showDeviceDialog();
    }

    public void swipeRefreshed() {
        Log.d(TAG, "swipeRefreshed: ");
        reloadData(mView::setSwiperefreshingOff);
    }

    public void fabDeviceBehindBtnClicked() {
        mView.gotoFloatingButtonDeviceActivity();
    }

    //endregion

    //region Calling Model Layer

    private void reloadData(Action finished) {
        DeviceNetworkHelper.getInstance()
                .getDevices(
                (deviceItems) -> {
                    mView.refreshDeviceItems(deviceItems);
                    try {
                        finished.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void subscriptionActivateDevice(DeviceItem item) {
        Log.d(TAG, "subscriptionActivateDevice() called with: position = [" + item + "]");

        if(item.isSubscribeOnOffState()) {
            MqttManager.getInstance().delSubscriptionSur(item,
                    () -> setItemdeviceSubscriptionState(item, false));
        } else {
            MqttManager.getInstance().addSubscriptionSur(item,
                    () -> setItemdeviceSubscriptionState(item, true));
        }
    }

    private void setItemdeviceSubscriptionState(DeviceItem item, boolean b) {
        item.setSubscribeOnOffState(b);
        DeviceNetworkHelper.getInstance().putDevice(item,
                                                    (result) -> {
                                                        if (result != null) {
                                                            mView.updateDevicesOnOffState();
                                                        } else {
                                                            Log.w(TAG, "setItemdeviceSubscriptionState: put device failed");
                                                        }
                                                    });
    }

    public void subscribeIvClicked(DeviceItem item) {
        subscriptionActivateDevice(item);
    }

    //endregion
}
