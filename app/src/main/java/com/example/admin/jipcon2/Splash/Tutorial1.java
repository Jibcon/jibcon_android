package com.example.admin.jipcon2.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.admin.jipcon2.Login.LoginActivity;
import com.example.admin.jipcon2.R;

/**
 * Created by admin on 2017-04-09.
 */

public class Tutorial1 extends android.support.v4.app.Fragment{

    Button TutorialSkip;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.tutorial_1,container,false);
        TutorialSkip = (Button)linearLayout.findViewById(R.id.Btn_Tutorial1_1);
        TutorialSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return linearLayout;
    }
}
