package com.sm_arts.jibcon.app.getothercon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-05-16.
 */

public class GetOtherConAdapter extends RecyclerView.Adapter<GetOtherConAdapter.ViewHolder>{
    private ArrayList<getConData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image) ImageView userImg;
        @BindView(R.id.tv_invte_house_name) TextView houseName;
        @BindView(R.id.tv_invite_email) TextView email;
        @BindView(R.id.tv_invite_house_info) TextView houseInfo;


        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public GetOtherConAdapter(ArrayList<getConData> getConDataset){
        mDataset = getConDataset;
    }

    // Create new views(invoked by the layout manager)
    @Override
    public GetOtherConAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getothercon_cardview, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.userImg.setImageResource(mDataset.get(position).userImg);
        holder.houseName.setText(mDataset.get(position).houseName);
        holder.email.setText(mDataset.get(position).email);
        holder.houseInfo.setText(mDataset.get(position).houseInfo);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}



