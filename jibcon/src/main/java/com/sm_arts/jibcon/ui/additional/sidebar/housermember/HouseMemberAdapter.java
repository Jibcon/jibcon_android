package com.sm_arts.jibcon.ui.additional.sidebar.housermember;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.User;

import java.util.List;

/**
 * Created by admin on 2017-12-03.
 */

public class HouseMemberAdapter extends RecyclerView.Adapter<HouseMemberAdapter.HouseMemberViewHolder> {

    private List<User> mUserListInHouse;

    public void setmUserListInHouse(List<User> mList) {
        mUserListInHouse = mList;
        notifyDataSetChanged();
    }

    @Override
    public HouseMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.additional_sidebar_housemember_item, parent, false);

        HouseMemberAdapter.HouseMemberViewHolder holder = new HouseMemberViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(HouseMemberViewHolder holder, int position) {

        User user = mUserListInHouse.get(position);
        ImageView profile = holder.mImageViewProfile;
        TextView name = holder.mTextViewName;

        Glide.with(GlobalApplication.getGlobalApplicationContext())
                .load(user.getUserinfo().getPic_url())
                .into(profile);
        if(TextUtils.isEmpty(user.getFirst_name()))
            name.setText(user.getUserinfo().getFull_name());
        else
            name.setText(user.getLast_name()+user.getFirst_name());
    }

    @Override
    public int getItemCount() {
        return mUserListInHouse.size();
    }


    static class HouseMemberViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewProfile;
        TextView mTextViewName;
        public HouseMemberViewHolder(View itemView) {
            super(itemView);
            mImageViewProfile = (ImageView) itemView.findViewById(R.id.imageview_additional_housemember);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_additional_housemember);

        }
    }
}
