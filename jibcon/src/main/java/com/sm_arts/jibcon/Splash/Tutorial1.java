package com.sm_arts.jibcon.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.Login.LoginActivity;
import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-09.
 */

public class Tutorial1 extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.tutorial_1,container,false);

        return linearLayout;
    }

}
