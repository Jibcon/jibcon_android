package com.sm_arts.jibcon.ui.splash.makecon;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.HouseInfo;
import com.sm_arts.jibcon.data.repository.helper.HouseNetworkManager;
import com.sm_arts.jibcon.ui.BaseActivity;

public class MakeconMainActivity extends BaseActivity implements HouseInfoListener {
    String mHousename;
    String mUsername;
    String mHouseintro;
    String mHousetype;
    String mHouselocation;
    int mFragmentNum;

    Fragment mMakecon1;
    Fragment mMakecon2;
    Fragment mMakecon3;
//    Fragment mMakecon4;
    HouseInfo mHouseInfo;
    @Override
    public void makeHouseInfo() {
        mHouseInfo = new HouseInfo();
//
        mHouseInfo.houseName = mHousename;
        mHouseInfo.houseAddress = mHouselocation;
        mHouseInfo.houseType = mHousetype;
        HouseNetworkManager.getInstance().addHouse(mHouseInfo);

        // TODO: 2017-04-1 서버로 집콘 정보 보내기
    }

    @Override
    public void setHouseName(String housename) {
        this.mHousename = housename;
    }

    @Override
    public void setUserName(String username) {
        this.mUsername = username;
    }

    @Override
    public void setHouseIntro(String houseintro) {
        this.mHouseintro = houseintro;
    }

    @Override
    public void setHouseType(String housetype) {
        this.mHousetype = housetype;
    }

    @Override
    public void setHouseLocation(String houselocation) {
        this.mHouselocation = houselocation;
    }

    @Override
    public void setFragmentNum(int fragmentNum) {
        this.mFragmentNum += fragmentNum;
        if (this.mFragmentNum < 0) {
            this.mFragmentNum = 0;
        }

        switch (this.mFragmentNum % 3) { // 4 -> 3 on 20180216 by zzanzu
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon1).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon2).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon3).commit();
                break;
//            case 3:
//                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon4).commit();
//                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashmakecon_makeconmain_activity);

        mMakecon1 = new MakeconHousenameFragment();
        mMakecon2 = new MakeconHousetypeFragment();
        mMakecon3 = new MakeconHouseaddressFragment();
//        mMakecon4 = new MakeconEndFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon1).commit();

    }

}
