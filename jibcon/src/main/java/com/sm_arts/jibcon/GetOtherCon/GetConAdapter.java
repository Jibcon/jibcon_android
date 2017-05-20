package com.sm_arts.jibcon.GetOtherCon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by user on 2017-05-16.
 */

public class GetConAdapter extends RecyclerView.Adapter<GetConAdapter.ViewHolder>{
    private ArrayList<getConData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView UserImg;
        public TextView HouseName;
        public TextView Email;
        public TextView HouseInfo;

        public ViewHolder(View view){
            super(view);

            UserImg = (ImageView)view.findViewById(R.id.image);
            HouseName = (TextView)view.findViewById(R.id.tv_invte_house_name);
            Email = (TextView)view.findViewById(R.id.tv_invite_email);
            HouseInfo = (TextView)view.findViewById(R.id.tv_invite_house_info);
        }
    }

    public GetConAdapter(ArrayList<getConData> getConDataset){
        mDataset = getConDataset;
    }

    // Create new views(invoked by the layout manager)
    @Override
    public GetConAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_getcon, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.UserImg.setImageResource(mDataset.get(position).user_img);
        holder.HouseName.setText(mDataset.get(position).house_name);
        holder.Email.setText(mDataset.get(position).email);
        holder.HouseInfo.setText(mDataset.get(position).house_info);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}



// 입력할 데이터 종류 커스터마이징
class getConData{
    public int user_img; // 초대한 사람 이미지
    public String house_name; // 초대한 사람의 집 이름
    public String email; // 초대한 사람의 이메일
    public String house_info; // 초대한 사람의 집 정보

    public getConData(int user_img, String house_name, String email, String house_info){
        this.user_img = user_img;
        this.house_name = house_name;
        this.email = email;
        this.house_info = house_info;
    }
}