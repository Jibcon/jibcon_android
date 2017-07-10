package com.sm_arts.jibcon.app.sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutJibconActivity extends AppCompatActivity {

    @OnClick(R.id.imageview_sidebar_aboutjibcon) void imageview_sidebar_aboutjibcon(){
        finish();}
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_about_jibcon);
        ButterKnife.bind(this);
    }
}
