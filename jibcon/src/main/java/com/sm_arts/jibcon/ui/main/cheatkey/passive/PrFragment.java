package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.PrMakeNew;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 11. 14..
 */

public class PrFragment extends Fragment{

    NotiData notiData = new NotiData("default","default","default","default","default","default","default");

    @OnClick(R.id.btn_pr_main_simple_makenew) void makeNewPr() {
        Intent intent = new Intent(getActivity(), PrMakeNew.class);
        intent.putExtra("come", notiData);
        startActivity(intent);
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.pr_main_simple, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }
}
