package com.sm_arts.jibcon.login.loginmanager;

import android.content.Context;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.sm_arts.jibcon.login.user.domain.User;

import io.reactivex.functions.Action;

/**
 * Created by admin on 2017-07-19.
 */

public interface JibconLoginManager  {

    CallbackManager makeFacebookCallbackManager();
    AccessTokenTracker makeFacebookAccessTokenTracker();
    FacebookCallback<LoginResult> makeFacebookLoginManager();
    void logout(Context context);

    void loginWithSampleUser(Action action);
    boolean userSignin();
    void setUser(User user);

    String getUserToken();

    String getUserProfileImageUrl();

    String getUserName();

    String getUserEmail();
}
