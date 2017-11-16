package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import java.util.List;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {

    private static final String TAG = "RoutineAdapter";
    private  List<RoutineItem> mRoutineItems;

    public RoutineAdapter(List<RoutineItem> routineItems) {
        this.mRoutineItems = routineItems;
    }
    public void setItems(List<RoutineItem> mRoutineItems)
    {
        this.mRoutineItems = mRoutineItems;
        notifyDataSetChanged();
    }


    public static class RoutineViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLinearLayout;
        CardView mCardView;
        TextView mTextView;
        TextView mTextViewData;
        Button mButton;

        public RoutineViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.linearlayout_pr_recycler_item);
            mCardView = (CardView) itemView.findViewById(R.id.cardview_pr_recycler_item);
            mTextView = (TextView) itemView.findViewById(R.id.tv_pr_recycler_item_datatype);
            mTextViewData = (TextView)itemView.findViewById(R.id.tv_pr_recycler_item_datacontent);
            mButton = (Button) itemView.findViewById(R.id.btn_pr_recycler_item_detail);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: ");
                }
            });
        }
    }

    @Override
    public RoutineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_recycler_item, parent, false);
        RoutineViewHolder holder = new RoutineViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RoutineViewHolder holder, int position) {
        TextView textView = holder.mTextView;
        RoutineItem item = mRoutineItems.get(position);
        TextView textViewData = holder.mTextViewData;
        LinkedTreeMap<String,Object> map = item.data;
        textView.setText(mRoutineItems.get(position).task_type);
        if(TextUtils.equals("message",item.task_type))
        {
           textViewData.setText((String)map.get("text"));
        }else if(TextUtils.equals("weather",item.task_type))
        {
            textViewData.setText((String)map.get("lat")+" "+(String)map.get("lon"));
        }

    }

    @Override
    public int getItemCount() {
        return mRoutineItems.size();
    }
}
