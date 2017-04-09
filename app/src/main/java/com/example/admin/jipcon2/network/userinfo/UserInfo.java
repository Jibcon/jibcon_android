package com.example.admin.jipcon2.network.userinfo;

/**
 * Created by admin on 2017-04-08.
 */

public class UserInfo {

//
//    UserInfo :
//            -UserToken : 로그인시 생성되는 토큰 저장
//    -UserImgProfile : 유저 프로필 이미지
//    -UserEmailProfile : 유저 이메일

    String userToken;
    String UserImgProfile;
    String UserEmailProfile;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserImgProfile() {
        return UserImgProfile;
    }

    public void setUserImgProfile(String userImgProfile) {
        UserImgProfile = userImgProfile;
    }

    public String getUserEmailProfile() {
        return UserEmailProfile;
    }

    public void setUserEmailProfile(String userEmailProfile) {
        UserEmailProfile = userEmailProfile;
    }
}
