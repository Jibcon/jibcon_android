package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;


import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrActionMessage extends BaseActivity {

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_action_message_ok) void gotoconfirm() {
        Intent intent = new Intent(this, PrMakeNew.class);
        lat = EtPrActionMessage.getText().toString();
        NotiData notidata = new NotiData(hour, minute, token, triggertype, actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @BindView(R.id.et_pr_action_message_message) EditText EtPrActionMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_action_message);
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
