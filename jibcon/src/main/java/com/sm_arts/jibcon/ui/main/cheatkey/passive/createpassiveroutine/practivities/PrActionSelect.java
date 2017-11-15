package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrActionSelect extends BaseActivity {

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_action_select_popular_message) void gotomessagefrompop() {
        Intent intent = new Intent(this, PrActionMessage.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, "message", lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }
    @OnClick(R.id.btn_pr_action_select_all_message) void gotomessagefromall() {
        Intent intent = new Intent(this, PrActionMessage.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, "message", lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @OnClick(R.id.btn_pr_action_select_popular_weather) void gotoweatherfrompop() {
        Intent intent = new Intent(this, PrActionWeather.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, "weather", lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }
    @OnClick(R.id.btn_pr_action_select_all_weather) void gotoweatherfromall() {
        Intent intent = new Intent(this, PrActionWeather.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, "weather", lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_action_select);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");

        hour = notidata.hour;
        minute = notidata.minute;
        token = notidata.token;

        triggertype = notidata.triggertype;
        actiontype = notidata.actiontype;

        lat = notidata.lat;
        lon = notidata.lon;
    }
}
