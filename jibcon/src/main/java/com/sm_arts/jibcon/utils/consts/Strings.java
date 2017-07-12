package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;

import butterknife.BindString;

/**
 * Created by jaeyoung on 7/12/17.
 */

public class Strings {
    private static Strings sInstance;
    @BindString(R.string.app_name)
    public String appName;
    @BindString(R.string.setting_menu_1)
    public String setting_menu_1;
    @BindString(R.string.setting_menu_2)
    public String setting_menu_2;
    @BindString(R.string.setting_menu_3)
    public String setting_menu_3;

    public static Strings getInstance() {
        if (sInstance == null) {
            sInstance = new Strings();
            new Strings_ViewBinding(sInstance, GlobalApplication.getGlobalApplicationContext());
        }
        return sInstance;
    }
}
