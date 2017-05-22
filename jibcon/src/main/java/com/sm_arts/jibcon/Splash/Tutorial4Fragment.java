package com.sm_arts.jibcon.Splash;

import android.app.Fragment;
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

public class Tutorial4Fragment extends android.support.v4.app.Fragment{
    Button toMain;
    Fragment fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.tutorial_4,container,false);


        toMain = (Button)linearLayout.findViewById((R.id.Btn_Tutorial4_1));
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return linearLayout;
    }
}
