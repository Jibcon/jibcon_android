package com.sm_arts.jibcon.ui.main.cheatkey.active;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.ui.additional.dialogs.RoutingDialog;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-05-23.
 */

public class ActiveCheatkeyAdapter
        extends RecyclerView.Adapter<ActiveCheatkeyAdapter.ViewHolder> {
    private ArrayList<ActiveCheatkeyData> mActiveCheatkeyDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view, final Context context) {
            super(view);

        }
    }

    public ActiveCheatkeyAdapter(ArrayList<ActiveCheatkeyData> ActiveCheatkeyDataset) {
        mActiveCheatkeyDataset = ActiveCheatkeyDataset;
    }

    @Override
    public ActiveCheatkeyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.maincheatkeyactive_activecheatkey_cardview, parent, false);

        ViewHolder vh = new ViewHolder(v,parent.getContext());

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ActiveCheatkeyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mActiveCheatkeyDataset.size();
    }
}
