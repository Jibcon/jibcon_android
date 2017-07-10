package com.sm_arts.jibcon.app.conshop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017-03-30.
 */

public class MarketMenuActivity extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    public MarketMenuActivity(){}

    @BindView(R.id.Txt_Market_Best) TextView mBest;
    @BindView(R.id.Txt_Market_Home) TextView mHome;
    @BindView(R.id.Txt_Market_Health) TextView mHealth;
    @BindView(R.id.Txt_Market_Outdoor) TextView mOutdoor;

    @OnClick(R.id.Txt_Market_Best) void Txt_Market_Best(){
        ToastHelper.toast(getContext(), "best");}
    @OnClick(R.id.Txt_Market_Home) void Txt_Market_Home(){
        ToastHelper.toast(getContext(), "home");}
    @OnClick(R.id.Txt_Market_Health) void Txt_Market_Health(){
        ToastHelper.toast(getContext(), "health");}
    @OnClick(R.id.Txt_Market_Outdoor) void Txt_Market_Outdoor(){
        ToastHelper.toast(getContext(), "outdoor");}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 주의 : menu_market의 최상단 레이아웃은 scrollview이기 객체 생성시에도 타입을 ScrollView로 해야됨.
        ScrollView view = (ScrollView) inflater.inflate(R.layout.menu_market, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
