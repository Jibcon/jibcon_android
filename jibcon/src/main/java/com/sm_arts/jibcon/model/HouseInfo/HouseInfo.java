package com.sm_arts.jibcon.model.HouseInfo;

import com.sm_arts.jibcon.Login.user.domain.UserInfo;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-08.
 */

public class HouseInfo {

//    -HouseType : 컨테이너 자취방 원룸 등등
//    -HouseLocation : 집콘 만들기 할 때 생성되는 주소
//    -HouseName : 집콘 이름
//    -HouseUsers : UserInfo의 ArrayList 한 공간의 사용자 그룹
//    -Houseintro: 집콘 소개
;
    String HouseType;
    String HouseName;
    String HouseIntro;
    String HouseLocation;
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHouseLocation() {
        return HouseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        HouseLocation = houseLocation;
    }

    public String getHouseIntro() {

        return HouseIntro;
    }

    public void setHouseIntro(String houseIntro) {
        HouseIntro = houseIntro;
    }

    ArrayList<UserInfo> HouseUsers;

    public String getHouseType() {
        return HouseType;
    }

    public void setHouseType(String houseType) {
        HouseType = houseType;
    }

    public String getHouseName() {
        return HouseName;
    }

    public void setHouseName(String houseName) {
        HouseName = houseName;
    }

    public ArrayList<UserInfo> getHouseUsers() {
        return HouseUsers;
    }

    public void setHouseUsers(ArrayList<UserInfo> houseUsers) {
        HouseUsers = houseUsers;
    }
}
