package com.sm_arts.jibcon.ui.splash.makecon;

/**
 * Created by admin on 2017-04-13.
 */

public interface HouseInfoListener {
    void setHouseName(String housename);
    void setUserName(String username);
    void setHouseIntro(String houseintro);
    void setHouseType(String housetype);
    void setHouseLocation(String houselocation);
    void setFragmentNum(int fragmentNum);
    void makeHouseInfo();
}
