package com.sm_arts.jibcon.ui.main.cheatkey.routine.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.sm_arts.jibcon.data.models.inapp.ObservableArrayListField;
import com.sm_arts.jibcon.data.models.api.dto.Routine;
import com.sm_arts.jibcon.data.repository.dummy.RoutineDummy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineViewModel extends ViewModel {
    private static final String TAG = "RoutineViewModel";

    public ObservableArrayListField<Routine> mRoutines = new ObservableArrayListField<>();
    public ObservableField<Boolean> mRefreshProgress = new ObservableField<>();

    public Observable<List<Routine>> getRoutinesAsObservable() {
//        return ObservableFieldConvertUtils.toObservable(mRoutines);
        return Observable.just(RoutineDummy.getDummys());
    }

    public void onNewRoutineClicked(View v) {
        Log.d(TAG, "onNewRoutineClicked: ");
    }
}
