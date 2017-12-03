package com.sm_arts.jibcon.ui.main.cheatkey.passive.passivehome;

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
import com.sm_arts.jibcon.data.models.api.dto.routine.RoutineItem;

import java.util.List;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutinePassiveAdapter extends RecyclerView.Adapter<RoutinePassiveAdapter.RoutineViewHolder> {

    private static final String TAG = "RoutineAdapter";
    private List<RoutineItem> mRoutineItems;
    private RoutineItemlistener mDeleteButtonListner;

    public RoutinePassiveAdapter(List<RoutineItem> routineItems, RoutineItemlistener mDeleteButtonListner) {
        this.mDeleteButtonListner = mDeleteButtonListner;
        this.mRoutineItems = routineItems;
    }

    public void setItems(List<RoutineItem> mRoutineItems) {
        this.mRoutineItems = mRoutineItems;
        notifyDataSetChanged();
    }

    public RoutineItem getItemWithPosition(int position) {
        if (position < 0)
            return null;
        else
            return mRoutineItems.get(position);
    }


    @Override
    public RoutineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_cheatkey_passiveitem, parent, false);
        RoutineViewHolder holder = new RoutineViewHolder(v, mDeleteButtonListner);
        return holder;
    }

    @Override
    public void onBindViewHolder(RoutineViewHolder holder, int position) {
        TextView routineType = holder.mRoutineTypeTextView;
        RoutineItem item = mRoutineItems.get(position);
        TextView textViewData = holder.mDataTextView;
        TextView timeTextView = holder.mTimeTextView;
        LinkedTreeMap<String, Object> map = item.data;
        routineType.setText(mRoutineItems.get(position).task_type);
        String time = (String) item.time_id.get("time");
        String[] timeArray = time.split("_");
        timeTextView.setText("매일 "+timeArray[0]+"시 "+timeArray[1]+" 분 마다");
        if (TextUtils.equals("message", item.task_type)) {

            textViewData.setText((String) map.get("text")+" 라고 알려줘");
        } else if (TextUtils.equals("weather", item.task_type)) {
            textViewData.setText("날씨를 알려줘");
            //textViewData.setText((String) map.get("lat") + " " + (String) map.get("lon"));
        }

    }

    @Override
    public int getItemCount() {
        return mRoutineItems.size();
    }


    public static class RoutineViewHolder extends RecyclerView.ViewHolder {


        LinearLayout mLinearLayout;
        CardView mCardView;
        TextView mRoutineTypeTextView;
        TextView mDataTextView;
        Button mButtonRoutineDelete;
        TextView mTimeTextView;
        RoutineItemlistener mRoutineDeleteListener;

        public RoutineViewHolder(View itemView, final RoutineItemlistener mRoutineDeleteListener) {
            super(itemView);

            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.routine_item_linear);
            mCardView = (CardView) itemView.findViewById(R.id.routine_item_cardview);
            mRoutineTypeTextView = (TextView) itemView.findViewById(R.id.routine_item_tv);
            mDataTextView = (TextView) itemView.findViewById(R.id.routine_item_data_tv);
            mButtonRoutineDelete = (Button) itemView.findViewById(R.id.btn_routine_delete);
            mTimeTextView = (TextView) itemView.findViewById(R.id.routine_item_time_tv);
            mButtonRoutineDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRoutineDeleteListener.onItemClicked(v, getAdapterPosition());
                }
            });
        }
    }
}
