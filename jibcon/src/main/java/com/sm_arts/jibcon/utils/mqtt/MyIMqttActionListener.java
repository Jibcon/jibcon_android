package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by jaeyoung on 7/29/17.
 */

public class MyIMqttActionListener implements IMqttActionListener {
    private static final String TAG = "MyIMqttActionListener";
    private String MQTT_Req_Topic=Config.MQTT.REQ_TOPIC;
    private MqttAndroidClient mqttClient = null;

    public MyIMqttActionListener(MqttAndroidClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.d(TAG, "onSuccess");
        String payload = "";
        int mqttQos = 1; /* 0: NO QoS, 1: No Check , 2: Each Check */

        MqttMessage message = new MqttMessage(payload.getBytes());
        try {
            Log.d(TAG, "onSuccess: "+MQTT_Req_Topic);
            mqttClient.subscribe(MQTT_Req_Topic, mqttQos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.d(TAG, "onFailure");
    }
}
