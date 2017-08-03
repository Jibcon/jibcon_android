package com.sm_arts.jibcon.utils.mqtt;

import android.os.Handler;
import android.util.Log;


import com.sm_arts.jibcon.GlobalApplication;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2017-08-02.
 */

public class MqttManager  {

    private static final String TAG = "MqttManager";
    private MqttAndroidClient mqttClient = null;
    private MyMqttCallback mainMqttCallback = new MyMqttCallback();
    private IMqttActionListener mainIMqttActionListener;

    private Handler handler = new Handler();

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
            /* Subscription Resource Create to Yellow Turtle */
            SubscribeResource subcribeResource = new SubscribeResource();
            subcribeResource.setReceiver(new IReceived() {
                public void getResponseBody(final String msg) {
                    handler.post(new Runnable() {
                        public void run() {
                            Log.d(TAG, "run: "+"**** Subscription Resource Create 요청 결과 ****\r\n\r\n" + msg);
                            //textViewData.setText("**** Subscription Resource Create 요청 결과 ****\r\n\r\n" + msg);
                        }
                    });
                }
            });
            subcribeResource.start();

            /* MQTT Subscribe */
            mqttClient = new MqttAndroidClient(GlobalApplication.getGlobalApplicationContext(), "tcp://" + Config.MQTT.HOST + ":" + Config.MQTT.PORT, MqttClient.generateClientId());
            mainIMqttActionListener = new MyIMqttActionListener(mqttClient);


            mainMqttCallback.setListener(
                    new Consumer<String>() {
                        @Override
                        public void accept(@NonNull String s) throws Exception {
                            Log.d(TAG, "accept: Data from mosquitto : "+s);

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

}
