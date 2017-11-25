package com.sm_arts.jibcon.data.repository.helper;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.routine.Routine;
import com.sm_arts.jibcon.data.repository.network.api.RoutineService;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 8/14/17.
 */

public class RoutineNetworkHelper {
    private static final String TAG = "RoutineNetworkHelper";
    private static RoutineNetworkHelper sInstance;

    private RoutineService service;

    private RoutineNetworkHelper() {
        service = RetrofitClients.getInstance().getService(RoutineService.class);
    }

    public static RoutineNetworkHelper getInstance() {
        if (sInstance == null) {
            synchronized(RoutineNetworkHelper.class) {
                if (sInstance == null) {
                    sInstance = new RoutineNetworkHelper();
                }
            }
        }
        return sInstance;
    }

    public void getRoutines(final Consumer<List<Routine>> finished) {
        Call<List<Routine>> call = service.getRoutines(
                JibconLoginManager.getInstance().getUserTokenAsHeader());
        call.enqueue(new Callback<List<Routine>>() {
            @Override
            public void onResponse(Call<List<Routine>> call, Response<List<Routine>> response) {
                List<Routine> result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: getRoutines failed");
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Routine>> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postRoutine(Routine routine, final Consumer<Routine> finished) {
        Call<Routine> call = service.postRoutine(
                JibconLoginManager.getInstance().getUserTokenAsHeader(),
                routine
        );

        call.enqueue(new Callback<Routine>() {
            @Override
            public void onResponse(Call<Routine> call, Response<Routine> response) {
                Routine result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: postRoutine failed with " + routine);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Routine> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getRoutine(String routineId, final Consumer<Routine> finished) {
        Call<Routine> call = service.getRoutine(
                JibconLoginManager.getInstance().getUserTokenAsHeader(),
                routineId
        );

        call.enqueue(new Callback<Routine>() {
            @Override
            public void onResponse(Call<Routine> call, Response<Routine> response) {
                Routine result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: getRoutine failed with routineId=" + routineId);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Routine> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void putRoutine(Routine routine, final Consumer<Routine> finished) {
        Call<Routine> call = service.putRoutine(
                JibconLoginManager.getInstance().getUserTokenAsHeader(),
                routine.id,
                routine
        );

        call.enqueue(new Callback<Routine>() {
            @Override
            public void onResponse(Call<Routine> call, Response<Routine> response) {
                Routine result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: putRoutine failed with " + routine);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Routine> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void deleteRoutine(String routineId, final Consumer<Object> finished) {
        Call<Object> call = service.deleteRoutine(
                JibconLoginManager.getInstance().getUserTokenAsHeader(),
                routineId
        );

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: deleteRoutine failed with routineId=" + routineId);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
