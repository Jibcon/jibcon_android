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
        int position = adapter.findDeviceItemPositionWithSur(mqttSurCon.getSur());
        if (position != -1) {
            mView.showContent(position, mqttSurCon.getCon());
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

        activateDevice(item);
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

    private void activateDevice(DeviceItem item) {
        Log.d(TAG, "activateDevice() called with: position = [" + item + "]");

        /*--->change device's onoff state*/
        if(item.isDeviceOnOffState()) {
            setItemDeviceOnoffState(item, false);
            String sur = item.getSubscriptionSur();
            MqttManager.getInstance().delSubscriptionSur(sur);
        } else {
            createMqttSubscription(item);
        }
    }

    private void setItemDeviceOnoffState(DeviceItem item, boolean b) {
        item.setDeviceOnOffState(b);
        mView.updateDevicesOnOffState();
    }

    private void createMqttSubscription(DeviceItem item) {
        MobiusNetworkHelper.getInstance().retrieveSub(
                item.getAeName(),
                item.getCntName(),
                responseSub -> {
                    Log.d(TAG, "activateDevice: responseSub = " + responseSub);
                    if (responseSub == null) {
                        createSub(item);
                    } else {
                        removeSub(item,
                                () -> createSub(item)
                        );
                    }
                }
        );
    }

    private void removeSub(DeviceItem item, Action finished) {
        MobiusNetworkHelper.getInstance().deleteSub(
                item.getAeName(),
                item.getCntName(),
                () -> {
                    Log.d(TAG, "removeSub: response");
                    finished.run();
                }
        );
    }

    private void createSub(DeviceItem item) {
        MobiusNetworkHelper.getInstance().createSub(
                item.getAeName(),
                item.getCntName(),
                responseSub -> {
                    Log.d(TAG, "activateDevice: responseSub = " + responseSub);
                    String subscriptionSur = MqttTopicUtils.makeSubscriptionSur(item);
                    setItemDeviceOnoffState(item, true);
                    MqttManager.getInstance().addSubscriptionSur(subscriptionSur);
                }
        );
    }

    //endregion
}
