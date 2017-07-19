package com.sm_arts.jibcon.login.loginmanager;

import android.content.Context;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

/**
 * Created by admin on 2017-07-19.
 */

public interface JibconLoginManager  {

    public CallbackManager makeFacebookCallbackManager();
    public AccessTokenTracker makeFacebookAccessTokenTracker();
    public FacebookCallback<LoginResult> makeFacebookLoginManager();
    public void logout(Context context);


}
