package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.model.houseinfo.HouseInfo;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MakeCon0Fragment extends AppCompatActivity implements HouseInfoListener {
    String mHousename;
    String mUsername;
    String mHouseintro;
    String mHousetype;
    String mHouselocation;
    int mFragmentNum;

    Fragment mMakecon1;
    Fragment mMakecon2;
    Fragment mMakecon3;
    Fragment mMakecon4;

    HouseInfo mHouseInfo;


    @Override
    public void makeHouseInfo() {
        mHouseInfo = new HouseInfo();

        mHouseInfo.setHouseIntro(this.mHouseintro);
        mHouseInfo.setHouseLocation(this.mHouselocation);
        mHouseInfo.setHouseName(this.mHousename);
        mHouseInfo.setHouseType(this.mHousetype);
        mHouseInfo.setUserName(this.mUsername);

        // TODO: 2017-04-1 서버로 집콘 정보 보내기
    }

    @Override
    public void getHouseName(String housename) {
        this.mHousename=housename;
    }

    @Override
    public void getUserName(String username) {
        this.mUsername=username;
    }

    @Override
    public void getHouseIntro(String houseintro) {
        this.mHouseintro= houseintro;
    }

    @Override
    public void getHouseType(String housetype) {
        this.mHousetype=housetype;
        }

    @Override
    public void getHouseLocation(String houselocation) {
        this.mHouselocation=houselocation;
    }

    @Override
    public void getFragmentNum(int fragmentNum) {
        this.mFragmentNum+=fragmentNum;
        if(this.mFragmentNum<0)
            this.mFragmentNum=0;
        switch (this.mFragmentNum%4)
        {
            case 0 :
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,mMakecon1).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,mMakecon2).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,mMakecon3).commit();
                break;

            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,mMakecon4).commit();
                break;



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_con0);

        mMakecon1=new MakeCon1Fragment();
        mMakecon2=new MakeCon2Fragment();
        mMakecon3=new MakeCon3Fragment();
        mMakecon4=new MakeCon4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,mMakecon1).commit();

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
