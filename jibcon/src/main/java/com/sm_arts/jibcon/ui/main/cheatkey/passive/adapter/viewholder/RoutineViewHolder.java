package com.sm_arts.jibcon.ui.main.cheatkey.passive.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sm_arts.jibcon.databinding.MaincheatkeyroutineRoutineItemBinding;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineViewHolder extends RecyclerView.ViewHolder {
    private final MaincheatkeyroutineRoutineItemBinding mBinding;

    public RoutineViewHolder(View itemView, MaincheatkeyroutineRoutineItemBinding binding) {
        super(itemView);
        mBinding = binding;
    }

    public void configureWith(RoutineItemViewModel routineItemViewModel) {
        mBinding.setRoutineitemviewmodel(routineItemViewModel);
    }
}
