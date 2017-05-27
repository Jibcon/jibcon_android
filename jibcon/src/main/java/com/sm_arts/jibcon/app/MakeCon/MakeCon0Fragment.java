package com.sm_arts.jibcon.app.MakeCon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.model.HouseInfo.HouseInfo;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MakeCon0Fragment extends AppCompatActivity implements HouseInfoListener {
    String housename;
    String username;
    String houseintro;
    String housetype;
    String houselocation;
    int fragmentNum;

    Fragment makecon1;
    Fragment makecon2;
    Fragment makecon3;
    Fragment makecon4;

    HouseInfo houseInfo;


    @Override
    public void makeHouseInfo() {
        houseInfo = new HouseInfo();

        houseInfo.setHouseIntro(this.houseintro);
        houseInfo.setHouseLocation(this.houselocation);
        houseInfo.setHouseName(this.housename);
        houseInfo.setHouseType(this.housetype);
        houseInfo.setUserName(this.username);

        // TODO: 2017-04-1 서버로 집콘 정보 보내기
    }

    @Override
    public void getHouseName(String housename) {
        this.housename=housename;
    }

    @Override
    public void getUserName(String username) {
        this.username=username;
    }

    @Override
    public void getHouseIntro(String houseintro) {
        this.houseintro= houseintro;
    }

    @Override
    public void getHouseType(String housetype) {
        this.housetype=housetype;
        }

    @Override
    public void getHouseLocation(String houselocation) {
        this.houselocation=houselocation;
    }

    @Override
    public void getFragmentNum(int fragmentNum) {
        this.fragmentNum+=fragmentNum;
        if(this.fragmentNum<0)
            this.fragmentNum=0;
        switch (this.fragmentNum%4)
        {
            case 0 :
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,makecon1).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,makecon2).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,makecon3).commit();
                break;

            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,makecon4).commit();
                break;



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_con0);

        makecon1=new MakeCon1Fragment();
        makecon2=new MakeCon2Fragment();
        makecon3=new MakeCon3Fragment();
        makecon4=new MakeCon4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0,makecon1).commit();

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
