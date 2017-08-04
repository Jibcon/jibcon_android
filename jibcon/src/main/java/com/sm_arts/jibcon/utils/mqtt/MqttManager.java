package com.sm_arts.jibcon.utils.mqtt;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.models.mobius.dto.MqttCi;
import com.sm_arts.jibcon.utils.network.GsonUtils;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2017-08-02.
 */

public class MqttManager {
    private static final String TAG = "MqttManager";

    private final BiConsumer<String, String> mlistener;
    private MqttAndroidClient mqttClient = null;
    private MyMqttCallback mainMqttCallback = new MyMqttCallback();
    private IMqttActionListener mainIMqttActionListener;

    private Handler handler = new Handler();
    private List<String> subscribeTopics = new ArrayList<>();

    public MqttManager(BiConsumer<String, String> listener) {
        mlistener = listener;
    }

    public void makeMqttSub(boolean isChecked)
    {
        if (isChecked) {
            Log.d(TAG, "MQTT Create");
            MQTT_Create(true);
        } else {
            Log.d(TAG, "MQTT Close");
            MQTT_Create(false);
        }
    }

    /* MQTT Subscription */
    public void MQTT_Create(boolean mtqqStart) {
        if (mtqqStart && mqttClient == null) {
            mqttClient = new MqttAndroidClient(GlobalApplication.getGlobalApplicationContext(), "tcp://" + Config.MQTT.HOST + ":" + Config.MQTT.PORT, MqttClient.generateClientId());

            mainMqttCallback.setListener(
                    (topic, s) -> {
                        if (isSubscribe(topic)) {
                            Type type = new TypeToken<Map<String, Object>>() {
                            }.getType();
                            String[] keys = {
                                    "pc",
                                    "sgn",
                                    "nev",
                                    "rep",
                                    "m2m:cin"
                            };
                            LinkedTreeMap<String, Object> map = GsonUtils.getGson().fromJson(s, type);

                            for (String key :
                                    keys) {
                                if (map != null) {
                                    map = (LinkedTreeMap<String, Object>) map.get(key);
                                }
                            }

                            String json = GsonUtils.getGson().toJson(map);
                            MqttCi ci = GsonUtils.getGson().fromJson(json, MqttCi.class);
                            Log.d(TAG, "accept: Data from mosquitto : " + ci.con);

                            mlistener.accept(topic, ci.con);
                        } else {
                            Log.d(TAG, "MQTT_Create: not subscribed topic, topic=" + topic);
                        }
                    }
            );

            mqttClient.setCallback(mainMqttCallback);
            try {
                IMqttToken token = mqttClient.connect();
                token.setActionCallback(mainIMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            /* MQTT unSubscribe or Client Close */
            mqttClient.setCallback(null);
            mqttClient.close();
            mqttClient = null;
        }
    }

    private boolean isSubscribe(String topic) {
        for (String item :
                subscribeTopics) {
            if (TextUtils.equals(item, topic)) {
                return true;
            }
        }

        return false;
    }

    public void addTopic(String mqttTopic) {
        try {
            String topic = attachPrefixToTopic(mqttTopic);
            Log.d(TAG, "addTopic() called with: prefix attached topic = [" + topic + "]");
            mqttClient.subscribe(topic, 1);
            subscribeTopics.add(mqttTopic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private String attachPrefixToTopic(String mqttTopic) {
        return "/oneM2M/req/Mobius/" + mqttTopic + "/#";
    }

    public void removeTopic(String mqttTopic) {
        // TODO: 8/2/17 IMPLEMENT
        subscribeTopics.remove(mqttTopic);
    }
}
