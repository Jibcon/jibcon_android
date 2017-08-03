package com.sm_arts.jibcon.ui.main.fragments;

import android.util.Log;

import com.sm_arts.jibcon.data.models.DeviceItem;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusCiService;
import com.sm_arts.jibcon.ui.main.adapters.DeviceMenuAdapter;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.mqtt.MqttManager;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 7/21/17.
 */

class DeviceMenuPresenter {
    private static final String TAG = "DeviceMenuPresenter";
    private final DeviceMenuView mView;
    private MqttManager mqttManager;
    private DeviceMenuAdapter mAdapter;

    public DeviceMenuPresenter(DeviceMenuView view) {
        Log.d(TAG, "DeviceMenuPresenter: ");
        mView = view;
        mqttManager = new MqttManager(
                this::receiveMqtt
        );

        mqttManager.MQTT_Create(true);
    }

    private void receiveMqtt(String topic, String con) {
        Log.d(TAG, "receiveMqtt() called with: topic = [" + topic + "], con = [" + con + "]");
        DeviceMenuAdapter adapter = mView.getAdapter();
        int position = adapter.findDeviceItemPositionWithTopic(topic);
        if (position != -1) {
            mView.showContent(position, con);
        } else {
            Log.w(TAG, "receiveMqtt: cannot find mqtttopic, topic=[" + topic + "]");
        }
    }

    //region Presenter role

    public void loadData(Consumer<List<DeviceItem>> finished) {
        Log.d(TAG, "loadData: ");
        DeviceServiceImpl.getInstance().getDeviceItems(
                deviceItems -> {
                    Log.d(TAG, "onSuccessGetDeviceItems: deviceItems=" + deviceItems);
                    try {
                        finished.accept(deviceItems);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
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
        DeviceServiceImpl.getInstance().reloadDeviceItems(
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
            item.setDeviceOnOffState(false);
            String mqttTopic = item.getMqttTopic();
            mqttManager.removeTopic(mqttTopic);
        } else {
            item.setDeviceOnOffState(true);
            String mqttTopic = item.getAeName() + "_" + item.getCntName() + "_" + Configs.AE.Name;
            Log.d(TAG, "activateDevice() called with: mqttTopic = [" + mqttTopic + "]");
            item.setMqttTopic(mqttTopic);
            mqttManager.addTopic(mqttTopic);
            mqttManager.MQTT_Create(true);
        }
        mView.updateDevicesOnOffState();

        /*change device's onoff state--->*/


        //// TODO: 2017-08-02  if we get led on from mosquitto change itemcolor


        // TODO: 7/21/17 replace this stub
        MobiusCiService service = RetrofiClients.getInstance().getService(MobiusCiService.class);
        Call<Object> call = service.turnOnLed(
                "application/json",
                "1",
                "/0.1",
                "application/vnd.onem2m-res+json; ty=4",
                new MobiusCiService.ApiCinC(3)
        );

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], " +
                        "response.code() = [" + response.code() + "]");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }



    //endregion
}
