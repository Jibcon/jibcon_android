package com.sm_arts.jibcon.ui.main.cheatkey.routine.adapter.viewholder;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.sm_arts.jibcon.data.models.api.dto.Routine;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineItemViewModel extends ViewModel {
    private static final String TAG = "RoutineItemViewModel";
    public ObservableField<Routine> mRoutine = new ObservableField<>();
    public ObservableField<String> mSensorPrefix = new ObservableField<>();

    public RoutineItemViewModel(@NonNull Routine routine) {
        mRoutine.set(routine);
    }

    public void onLayoutClicked(View view) {
        Log.d(TAG, "onLayoutClicked: ");
        final Routine routine = mRoutine.get();
//        mNavigator.goRoutineDetail(routine.id);
    }
}
