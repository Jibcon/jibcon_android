package com.sm_arts.jibcon.ui.main.cheatkey.passive.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.Routine;
import com.sm_arts.jibcon.databinding.MaincheatkeyroutineRoutineItemBinding;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.adapter.viewholder.RoutineViewHolder;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.adapter.viewholder.RoutineItemViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class RoutineAdapter extends RecyclerView.Adapter<RoutineViewHolder> {
    private static final String TAG = "RoutineAdapter";
    private List<RoutineItemViewModel> mItemViewModels = new ArrayList<>();

    public RoutineAdapter(Observable<List<Routine>> routinesAsObservable) {
        routinesAsObservable.subscribe(
                routines -> {
                    mItemViewModels.clear();
                    for (Routine routine:
                         routines) {
                        mItemViewModels.add(new RoutineItemViewModel(routine));
                    }
                }
        );
    }

    @Override
    public RoutineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MaincheatkeyroutineRoutineItemBinding binding
                = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.maincheatkeyroutine_routine_item,
                        parent,
                        false);

        View v = binding.getRoot();


        final RoutineViewHolder viewholder =
                new RoutineViewHolder(v, binding);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(RoutineViewHolder holder, int position) {
        holder.configureWith(mItemViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemViewModels.size();
    }
}
