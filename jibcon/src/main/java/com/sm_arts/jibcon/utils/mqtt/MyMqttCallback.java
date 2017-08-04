package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * Created by jaeyoung on 7/28/17.
 */

public class MyMqttCallback implements MqttCallback {
    private static final String TAG = "MyMqttCallback";
    private BiConsumer<String, String> mListener;

    public void setListener(BiConsumer<String, String> mListener) {
        this.mListener = mListener;
    }

    public void notify(String topic, String s) {
        if (mListener != null) {
            Log.d(TAG, "notify() called with: topic = [" + topic + "], s = [" + s + "]");
            try {
                mListener.accept(topic, s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.d(TAG, "connectionLost: ");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d(TAG, "messageArrived: topic=" + topic + " message=" + message);
//        notify(message.toString().replaceAll("\t|\n", ""));
        notify(topic, message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d(TAG, "deliveryComplete() called with: token = [" + token + "]");
    }
}
