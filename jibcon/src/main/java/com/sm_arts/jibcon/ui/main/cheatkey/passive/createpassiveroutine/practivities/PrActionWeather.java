package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 11. 15..
 */

public class PrActionWeather extends BaseActivity {

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_action_weather_forecast) void gotoconfrim() {
        Intent intent = new Intent(this, PrMakeNew.class);
        lat = "32.5";
        lon = "32.5";
        NotiData notidata = new NotiData(hour, minute, token, triggertype, actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_action_weather);
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