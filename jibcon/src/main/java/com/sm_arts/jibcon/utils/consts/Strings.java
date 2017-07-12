package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;

import butterknife.BindString;

/**
 * Created by jaeyoung on 7/12/17.
 */

public class Strings {
    private static Strings sInstance;
    @BindString(R.string.app_name) public String appName;

    public static Strings getInstance() {
        if (sInstance == null) {
            sInstance = new Strings();
            new Strings_ViewBinding(sInstance, GlobalApplication.getGlobalApplicationContext());
        }
        return sInstance;
    }
}
