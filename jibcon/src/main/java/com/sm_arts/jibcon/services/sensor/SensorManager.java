package com.sm_arts.jibcon.services.sensor;

import android.text.TextUtils;
import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.api.dto.Routine;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.RoutineNetworkHelper;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.mqtt.MqttManager;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class SensorManager {
    private static final String TAG = "SensorManager";

    private static SensorManager sInstance;
    private final Observable<MqttSurCon> mMqttSurConObservable;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    public static void init() {
        if (JibconLoginManager.getInstance().userSignin()) {
            sInstance = new SensorManager();
        } else {
            Log.e(TAG, "init: must call init() after signin");
        }
    }

    public static SensorManager getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init() first");
        }

        return sInstance;
    }

    private SensorManager() {
        MqttManager.init();
        mMqttSurConObservable = MqttManager.getInstance().asObservable();

        mDisposables.add(
                mMqttSurConObservable.subscribe(
                        (surCon) -> Log.d(TAG, "SensorManager: surCon=" + surCon.toString())
                )
        );

        initRoutines();
    }

    private void initRoutines() {
        // TODO: 8/14/17 GET Routines, Add Disposable routine by routine
        Log.d(TAG, "initRoutines: ");


        DeviceNetworkHelper.getInstance().getDevices(
                (items) -> {
                    Log.d(TAG, "initRoutines: items=" + items.toString());
                    String sensorId = null, actuatorId = null;
                    for (DeviceItem item:
                         items) {
                        if (TextUtils.equals(item.getDeviceName(), "ultra")) {
                            sensorId = item.getId();
                            break;
                        }
                    }

                    for (DeviceItem item:
                            items) {
                        if (TextUtils.equals(item.getDeviceName(), "Philips Hue 전구")) {
                            actuatorId = item.getId();
                            break;
                        }
                    }

                    if (sensorId != null && actuatorId != null) {
                        makeRoutine(sensorId, actuatorId, () -> {
                            RoutineNetworkHelper.getInstance().getRoutines(
                                    routines -> {
                                        Log.d(TAG, "initRoutines: routines.size=" + routines.size());
                                    }
                            );
                        });
                    }
                }
        );


    }

    private void makeRoutine(String sensorId, String actuatorId, Action finished) {
        Routine routine = new Routine("누구시죠",
                "출입감지센서", "",
                "smaller", "0.5", "meter",
                "", "", "");
        RoutineNetworkHelper.getInstance().postRoutine(
                routine,
                result -> {
                    Log.d(TAG, "initRoutines: result routine=" + result);
                    finished.run();
                }
        );
    }
}
