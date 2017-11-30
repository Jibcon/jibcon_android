package com.sm_arts.jibcon.data.models.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017-04-08.
 */

public class HouseInfo {

//    -HouseType : 컨테이너 자취방 원룸 등등
//    -HouseLocation : 집콘 만들기 할 때 생성되는 주소
//    -HouseName : 집콘 이름
//    -HouseUsers : UserInfo의 ArrayList 한 공간의 사용자 그룹
//    -Houseintro: 집콘 소개

    @SerializedName("_id")
    public String house_id;

    public String houseMaster;
    public String houseName;
    public String houseType;
    public String houseAddress;
    //public List<HashMap<String,Object>> deviceList;

}
