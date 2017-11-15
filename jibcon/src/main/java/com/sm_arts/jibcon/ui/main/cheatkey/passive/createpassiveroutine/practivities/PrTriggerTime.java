package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;


import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrTriggerTime extends BaseActivity {

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_trigger_time_ok) void gomain() {
        Intent intent = new Intent(this, PrMakeNew.class);
        hour = spinner_pr_trigger_specific_hour.getSelectedItem().toString();
        minute = spinner_pr_trigger_specific_minute.getSelectedItem().toString();

        NotiData notidata = new NotiData(hour, minute, token, triggertype, actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        finish();
        startActivity(intent);
    }

    @BindView(R.id.spinner_pr_trigger_time_hour) Spinner spinner_pr_trigger_specific_hour;
    @BindView(R.id.spinner_pr_trigger_time_minute) Spinner spinner_pr_trigger_specific_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_trigger_time);
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
