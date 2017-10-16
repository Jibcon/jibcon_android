package com.sm_arts.jibcon.utils.mqtt;

import android.util.Log;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by admin on 2017-08-02.
 */

public class MqttManager {
    private static final String TAG = "MqttManager";
    private static MqttManager sInstance;

    private MqttAndroidClient mMqttClient;
    private MqttCallbackImpl mMqttCallback;
    private ReplaySubject<MqttSurCon> mNotifier = ReplaySubject.create();

    public static MqttManager getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init() first");
        }

        return sInstance;
    }

    public static void init() {
        if (JibconLoginManager.getInstance().userSignin()) {
            sInstance = new MqttManager();
        } else {
            Log.e(TAG, "init: must call init() after signin");
        }
    }

    public MqttManager() {
        initClient();
        initSubscriptionSurs();
    }

    private void initSubscriptionSurs() {
        DeviceNetworkHelper.getInstance().getDevices(
                (deviceItems -> {
                    if (deviceItems != null) {
                        for (DeviceItem item :
                                deviceItems) {
                            if (item.isSubscribeOnOffState()) {
                                Log.d(TAG, "initSubscriptionSurs: addSubscriptionSur with Item="
                                        + item.toString());
                                addSubscriptionSur(item, () -> {});
                            }
                        }
                    } else {
                        Log.d(TAG, "initSubscriptionSurs: fail to get deviceitems");
                    }
                })
        );
    }

    private void subscribe() {
        Log.d(TAG, "subscribe: ");
        try {
            mMqttClient.subscribe(MqttTopicUtils.getSubscribeTopic(), Configs.Mqtt.QOS);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public Observable<MqttSurCon> asObservable() {
        return mNotifier;
    }

    private void initClient() {
        Log.d(TAG, "initClient: ");

        mMqttClient = new MqttAndroidClient(GlobalApplication.getGlobalApplicationContext(),
                "tcp://" + Configs.Mqtt.HOST + ":" + Configs.Mqtt.PORT, MqttClient.generateClientId());
        mMqttCallback = new MqttCallbackImpl(this);
        mMqttCallback.asObservable().subscribe(
                mNotifier::onNext
        );

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

    public void delSubscriptionSur(DeviceItem item, Action finished) {
        String subscriptionSur = item.getSubscriptionSur();
        Log.d(TAG, "delSubscriptionSur() called with: subscriptionSur = [" + subscriptionSur + "]");
        mMqttCallback.delSubscriptionSur(subscriptionSur);
        try {
            finished.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSubscriptionSur(DeviceItem item, Action finished) {
        String subscriptionSur = item.getSubscriptionSur();
        Log.d(TAG, "addSubscriptionSur() called with: subscriptionSur = [" + subscriptionSur + "]");



        createMqttSubscription(item,
                () -> {
                    mMqttCallback.addSubscriptionSur(subscriptionSur);
                    finished.run();
                }
        );
    }

    private void createMqttSubscription(DeviceItem item, Action finished) {
        createSub(item, finished);
//            MobiusNetworkHelper.getInstance().retrieveSub(
//                    item.getAeName(),
//                    item.getCntName(),
//                    responseSub -> {
//                        Log.d(TAG, "subscriptionActivateDevice: responseSub = " + responseSub);
//                        if (responseSub == null) {
//                        } else {
//                            removeSub(item,
//                                    () -> createSub(item, finished)
//                            );
//                        }
//                    }
//            );
    }

    private void removeSub(DeviceItem item, Consumer<Boolean> finished) {
        MobiusNetworkHelper.getInstance().deleteSub(
                item.getAeName(),
                MqttTopicUtils.getResponseCnt(item.getCntName()),
                finished
        );
    }

    private void createSub(DeviceItem item, Action finished) {
        MobiusNetworkHelper.getInstance().createSub(
                item.getAeName(),
                MqttTopicUtils.getResponseCnt(item.getCntName()),
                // OnSuccess
                responseSub -> {
                    Log.d(TAG, "subscriptionActivateDevice: responseSub = " + responseSub);
                    finished.run();
                },
                () -> {
                    removeSub(item,
                            (isSuccessful) -> {
                                Log.d(TAG, "removeSub: response");
                                if (isSuccessful) {
                                    createSub(item, finished);
                                } else {
                                    Log.d(TAG, "createSub: fail to create");
                                }
                            }
                    );
                }
        );
    }
}
