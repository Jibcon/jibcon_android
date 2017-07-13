package com.sm_arts.jibcon.app.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.login.LoginActivity;
import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-09.
 */

public class Tutorial4Fragment extends android.support.v4.app.Fragment{
    Button mToMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.splash_tutorial4_fragment,container,false);


        mToMain = (Button)linearLayout.findViewById((R.id.makejibcon_btn));
        mToMain.setOnClickListener(new View.OnClickListener() {
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
