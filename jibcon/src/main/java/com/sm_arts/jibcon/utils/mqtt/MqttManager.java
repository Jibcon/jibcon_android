package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2017-08-02.
 */

public class MqttManager {
    private static final String TAG = "MqttManager";
    private static MqttManager sInstance;

    private MqttAndroidClient mMqttClient;
    private MqttCallbackImpl mMqttCallback;

    public static MqttManager getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init() first");
        }

        return sInstance;
    }

    public static void init() {
        sInstance = new MqttManager();
    }

    public MqttManager() {
        initClient();
    }

    private void subscribe() {
        Log.d(TAG, "subscribe: ");
        try {
            mMqttClient.subscribe(MqttTopicUtils.getSubscribeTopic(), Configs.Mqtt.QOS);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void initClient() {
        Log.d(TAG, "initClient: ");

        mMqttClient = new MqttAndroidClient(GlobalApplication.getGlobalApplicationContext(),
                "tcp://" + Configs.Mqtt.HOST + ":" + Configs.Mqtt.PORT, MqttClient.generateClientId());
        mMqttCallback = new MqttCallbackImpl(this);

        startClient();
    }

    private void startClient() {
        Log.d(TAG, "startClient: ");

        mMqttClient.setCallback(mMqttCallback);

        IMqttToken token;
        try {
            token = mMqttClient.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    exception.printStackTrace();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void stopClient() {
        mMqttClient.setCallback(null);
        mMqttClient.close();
    }

    public void setListener(Consumer<MqttSurCon> listener) {
        Log.d(TAG, "setListener: ");
        if (mMqttCallback != null) {
            mMqttCallback.setListener(listener);
        }
    }

    public void addSubscriptionSur(String subscriptionSur) {
        Log.d(TAG, "addSubscriptionSur() called with: subscriptionSur = [" + subscriptionSur + "]");
        mMqttCallback.addSubscriptionSur(subscriptionSur);
    }

    public void delSubscriptionSur(String subscriptionSur) {
        Log.d(TAG, "addSubscriptionSur() called with: subscriptionSur = [" + subscriptionSur + "]");
        mMqttCallback.delSubscriptionSur(subscriptionSur);
    }
}
