package com.sm_arts.jibcon.app.cheatkey;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm_arts.jibcon.ui.dialogs.RoutingDialog;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-05-20.
 */

public class PassiveCheatkeyAdapter
        extends RecyclerView.Adapter<PassiveCheatkeyAdapter.ViewHolder> {
    private ArrayList<PassiveCheatkeyData> mPassiveCheatkeyDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton cheatkeySettingBtn;
        public TextView cheatkeyInputTv;
        public TextView cheatkeyOutputTv;

        public ViewHolder(View view, final Context context) {
            super(view);

            cheatkeySettingBtn = (ImageButton) view.findViewById(R.id.btn_passive_cheatkey_setting);
            cheatkeyInputTv = (TextView) view.findViewById(R.id.passive_cheatkey_input);
            cheatkeyOutputTv = (TextView) view.findViewById(R.id.passive_cheatkey_output);

            cheatkeySettingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoutingDialog routingDialog = new RoutingDialog(context);
                    routingDialog.show();
                }
            });
        }
    }

    public PassiveCheatkeyAdapter(ArrayList<PassiveCheatkeyData> PassiveCheatkeyDataset) {
        mPassiveCheatkeyDataset = PassiveCheatkeyDataset;
    }

    @Override
    public PassiveCheatkeyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheatkey_passive_cardview, parent, false);

        ViewHolder vh = new ViewHolder(v,parent.getContext());

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PassiveCheatkeyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cheatkeySettingBtn.setId(R.id.btn_passive_cheatkey_setting);
        holder.cheatkeyInputTv.setText(mPassiveCheatkeyDataset.get(position).inputTv);
        holder.cheatkeyOutputTv.setText(mPassiveCheatkeyDataset.get(position).outputTv);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPassiveCheatkeyDataset.size();
    }
}

// input 텍스트뷰와 output 텍스트뷰 DataSet
class PassiveCheatkeyData {
    public int imgBtnID;
    public String inputTv;
    public String outputTv;

    public PassiveCheatkeyData(int imgBtnID, String inputTv, String outputTv) {
        this.imgBtnID = imgBtnID;
        this.inputTv = inputTv;
        this.outputTv = outputTv;
    }
}
