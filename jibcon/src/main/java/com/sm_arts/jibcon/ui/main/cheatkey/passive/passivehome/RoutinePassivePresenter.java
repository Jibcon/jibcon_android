package com.sm_arts.jibcon.ui.main.cheatkey.passive.passivehome;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;
import com.sm_arts.jibcon.data.repository.network.api.RoutineService;
import com.sm_arts.jibcon.ui.additional.floatingbuttonui.FloatingButtonPassiveActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-11-17.
 */

public class RoutinePassivePresenter {
    private static final String TAG = "RoutinePassivePresenter";
    RoutinePassiveAdapter mRoutinePassiveAdapter;
    RoutinePassiveView mView;

    public RoutinePassivePresenter(RoutinePassiveAdapter mRoutinePassiveAdapter, RoutinePassiveView mView) {
        this.mRoutinePassiveAdapter = mRoutinePassiveAdapter;
        this.mView = mView;
    }

    public void getPassiveRoutines() {

        RoutineService service = RetrofitClients.getInstance().getService(RoutineService.class);
        retrofit2.Call<List<HashMap<String, Object>>> c = service.getMyTasks(JibconLoginManager.getInstance().getUserId());
        c.enqueue(new Callback<List<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, Object>>> call, Response<List<HashMap<String, Object>>> response) {
                List<RoutineItem> routineItems = new ArrayList<RoutineItem>();
                for (int i = 0; i < response.body().size(); i++) {
                    HashMap<String, Object> result = response.body().get(i);
                    RoutineItem routineItem = new RoutineItem();
                    routineItem.data = (LinkedTreeMap<String, Object>) result.get("data");
                    routineItem._id = (String) result.get("_id");
                    routineItem.task_type = (String) result.get("task_type");
                    routineItem.time_id = (LinkedTreeMap<String, Object>) result.get("time_id");
                    routineItem.userId = (String) result.get("userId");
                    routineItems.add(routineItem);
                }

                mRoutinePassiveAdapter.setItems(routineItems);
                mView.onDataDownloadFinished();
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<HashMap<String, Object>>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
                mView.onDataDownloadFinished();
            }
        });
    }

    public void setOnFabButtonClicked(Activity activity) {

        Intent intent = new Intent(activity.getApplicationContext(), FloatingButtonPassiveActivity.class);
        activity.startActivity(intent);

    }

    public void deleteRoutine(RoutineItem routineItem) {
        RoutineService service = RetrofitClients.getInstance().getService(RoutineService.class);
        //Call<Void> deleteTask (@Header("Authorization") String _id);
        Call<Void> c = service.deleteTask(routineItem._id);
        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getPassiveRoutines();
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mView.onDataDownloadFinished();
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
