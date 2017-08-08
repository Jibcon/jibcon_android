package com.sm_arts.jibcon.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sm_arts.jibcon.GlobalApplication;

/**
 * Created by admin on 2017-07-19.
 */

public class SharedPreferenceHelper {



    public static void saveSharedPreference(String preferenceName, String key, String str)
    {
        SharedPreferences pref = GlobalApplication.getGlobalApplicationContext().getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key,str);
        editor.commit();
    }
    public static String getSharedPrefrence(String preferenceName, String key)
    {

        //default return ""
        SharedPreferences pref = GlobalApplication.getGlobalApplicationContext().getSharedPreferences(preferenceName,Context.MODE_PRIVATE);

        return pref.getString(key,"");

    }
}
