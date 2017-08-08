package com.sm_arts.jibcon.login.user.domain;

/**
 * Created by admin on 2017-04-10.
 */

public class User {
    String email;
    //String username;
    String token;
    UserInfo userinfo;
    String last_name;
    String first_name;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserInfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String usernaame) {
//        this.username = usernaame;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
