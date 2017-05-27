package com.sm_arts.jibcon.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017-05-27.
 */

public class ToastHelper {

    public static void toast(Context context, String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
