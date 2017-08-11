package com.sm_arts.jibcon.ui.splash.makecon;

/**
 * Created by admin on 2017-04-13.
 */

public interface HouseInfoListener {
    void getHouseName(String housename);
    void getUserName(String username);
    void getHouseIntro(String houseintro);
    void getHouseType(String housetype);
    void getHouseLocation(String houselocation);
    void getFragmentNum(int fragmentNum);
    void makeHouseInfo();
}
