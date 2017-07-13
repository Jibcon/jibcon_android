package com.sm_arts.jibcon.app.getothercon;

/**
 * Created by woojinkim on 2017. 7. 13..
 */

// 입력할 데이터 종류 커스터마이징
class getConData{
    public int userImg; // 초대한 사람 이미지
    public String houseName; // 초대한 사람의 집 이름
    public String email; // 초대한 사람의 이메일
    public String houseInfo; // 초대한 사람의 집 정보

    public getConData(int userImg, String houseName, String email, String houseInfo){
        this.userImg = userImg;
        this.houseName = houseName;
        this.email = email;
        this.houseInfo = houseInfo;
    }
}