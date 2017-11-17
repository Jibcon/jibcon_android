package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;

import java.util.List;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutinePassiveAdapter extends RecyclerView.Adapter<RoutinePassiveAdapter.RoutineViewHolder> {

    private static final String TAG = "RoutineAdapter";
    private  List<RoutineItem> mRoutineItems;
    private RoutineItemlistener mDeleteButtonListner;

    public RoutinePassiveAdapter(List<RoutineItem> routineItems, RoutineItemlistener mDeleteButtonListner) {
        this.mDeleteButtonListner = mDeleteButtonListner;
        this.mRoutineItems = routineItems;
    }
    public void setItems(List<RoutineItem> mRoutineItems)
    {
        this.mRoutineItems = mRoutineItems;
        notifyDataSetChanged();
    }
    public RoutineItem getItemWithPosition(int position)
    {
        if(position<0)
            return null;
        else
            return mRoutineItems.get(position);
    }

    public static class RoutineViewHolder extends RecyclerView.ViewHolder {


        LinearLayout mLinearLayout;
        CardView mCardView;
        TextView mTextView;
        TextView mTextViewData;
        Button mButtonRoutineDelete;
        RoutineItemlistener mRoutineDeleteListener;

        public RoutineViewHolder(View itemView, final RoutineItemlistener mRoutineDeleteListener) {
            super(itemView);

            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.routine_item_linear);
            mCardView = (CardView) itemView.findViewById(R.id.routine_item_cardview);
            mTextView = (TextView) itemView.findViewById(R.id.routine_item_tv);
            mTextViewData = (TextView)itemView.findViewById(R.id.routine_item_data_tv);
            mButtonRoutineDelete = (Button) itemView.findViewById(R.id.btn_routine_delete);
            mButtonRoutineDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoutineDeleteListener.onItemClicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public RoutineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_cheatkey_passiveitem, parent, false);
        RoutineViewHolder holder = new RoutineViewHolder(v, mDeleteButtonListner);
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
