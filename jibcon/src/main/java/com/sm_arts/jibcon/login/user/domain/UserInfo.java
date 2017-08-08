package com.sm_arts.jibcon.login.user.domain;

/**
 * Created by admin on 2017-04-08.
 */

public class UserInfo {

//
//    UserInfo :
//            -UserToken : 로그인시 생성되는 토큰 저장
//    -UserImgProfile : 유저 프로필 이미지
//    -UserEmailProfile : 유저 이메일
//
//    String userToken;
//    String UserImgProfile;
//    String UserEmailProfile;
//    String email;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUserToken() {
//        return userToken;
//    }
//
//    public void setUserToken(String userToken) {
//        this.userToken = userToken;
//    }
//
//    public String getUserImgProfile() {
//        return UserImgProfile;
//    }
//
//    public void setUserImgProfile(String userImgProfile) {
//        UserImgProfile = userImgProfile;
//    }
//
//    public String getUserEmailProfile() {
//        return UserEmailProfile;
//    }
//
//    public void setUserEmailProfile(String userEmailProfile) {
//        UserEmailProfile = userEmailProfile;
//    }

    String type;
    String token;
    String full_name;
    String pic_url;

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public UserInfo(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
