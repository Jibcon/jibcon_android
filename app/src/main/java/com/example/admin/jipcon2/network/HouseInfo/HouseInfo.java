package com.example.admin.jipcon2.network.HouseInfo;

import com.example.admin.jipcon2.network.userinfo.UserInfo;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-08.
 */

public class HouseInfo {

//    -HouseType : 컨테이너 자취방 원룸 등등
//    -HouseLocation : 집콘 만들기 할 때 생성되는 주소
//    -HouseName : 집콘 이름
//    -HouseUsers : UserInfo의 ArrayList 한 공간의 사용자 그룹


    String spacetype;
    String HouseType;
    String HouseName;
    ArrayList<UserInfo> HouseUsers;

    public String getSpacetype() {
        return spacetype;
    }

    public void setSpacetype(String spacetype) {
        this.spacetype = spacetype;
    }

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
