package com.sm_arts.jibcon.utils.mqtt;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.sm_arts.jibcon.data.models.mobius.MqttCi;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.utils.network.GsonUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by jaeyoung on 8/4/17.
 */

public class MqttCallbackImpl implements MqttCallback {
    private static final String TAG = "MqttCallbackImpl";
    private final MqttManager mMqttManager;
    private Consumer<MqttSurCon> mListener;
    private List<String> mSubscribeSurs;
    private ReplaySubject<MqttSurCon> mNotifier = ReplaySubject.create();

    public MqttCallbackImpl(MqttManager mqttManager) {
        // save for connectionLost
        mMqttManager = mqttManager;
        mSubscribeSurs = new ArrayList<>();
    }

    @Override
    public void connectionLost(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        MqttSurCon surCon = parse(message);

        // TODO: 8/4/17 IMPLEMENT Application Restart할때 기존 Subscription Sur List불러오는 로직
//        if (isSubscribingSur(surCon.getSur())) {
        notifyMessageArrived(surCon);
//        }
    }

    private void notifyMessageArrived(MqttSurCon surCon) {
        if (mListener != null) {
            try {
                mListener.accept(surCon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mNotifier.onNext(surCon);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            Log.d(TAG, "deliveryComplete() called with: token message = [" + token.getMessage() + "]");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void setListener(Consumer<MqttSurCon> listener) {
        this.mListener = listener;
    }

    private boolean isSubscribingSur(String sur) {
        for (String item :
                mSubscribeSurs) {
            if (TextUtils.equals(item, sur)) {
                return true;
            }
        }

        return false;
    }

    private MqttSurCon parse(MqttMessage message) {
        String sur = null;

        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        String[] keys = {
                "pc",
                "sgn",
                "nev",
                "rep",
                "m2m:cin"
        };
        LinkedTreeMap<String, Object> map = GsonUtils.getGson().fromJson(message.toString(), type);

        for (String key :
                keys) {
            if (map != null) {
                if (TextUtils.equals(key, "nev")) {
                    sur = (String) map.get("sur");
                }
                map = (LinkedTreeMap<String, Object>) map.get(key);
            }
        }

        String json = GsonUtils.getGson().toJson(map);
        MqttCi ci = GsonUtils.getGson().fromJson(json, MqttCi.class);
        MqttSurCon surCon = new MqttSurCon(sur, ci.con);

        return surCon;
}

    public void addSubscriptionSur(String subscriptionSur) {
        mSubscribeSurs.add(subscriptionSur);
    }

    public void delSubscriptionSur(String subscriptionSur) {
        for (String item:
                mSubscribeSurs) {
            if (TextUtils.equals(item, subscriptionSur)) {
                mSubscribeSurs.remove(item);
                return;
            }
        }
    }

    public Observable<MqttSurCon> asObservable() {
        return mNotifier;
    }
}
