package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.databinding.MaincheatkeyroutineRoutineFragmentBinding;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.adapter.RoutineAdapter;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.viewmodel.RoutineViewModel;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineFragment extends Fragment {
    private static final String TAG = "RoutineFragment";
    private RoutineViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        MaincheatkeyroutineRoutineFragmentBinding binding =
                DataBindingUtil.inflate(inflater,
                        R.layout.maincheatkeyroutine_routine_fragment,
                        container, false);

        View view = binding.getRoot();
        mViewModel = ViewModelProviders.of(this).get(RoutineViewModel.class);

        binding.setRoutineviewmodel(mViewModel);
        initView(binding);

        return view;
    }

    private void initView(MaincheatkeyroutineRoutineFragmentBinding binding) {
        binding.recyclerviewRoutines.setLayoutManager(
                new LinearLayoutManager(getContext()));
        binding.recyclerviewRoutines.setAdapter(
                new RoutineAdapter(mViewModel.getRoutinesAsObservable()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }
}
